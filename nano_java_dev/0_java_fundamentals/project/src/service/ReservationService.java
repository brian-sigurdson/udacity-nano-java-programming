package service;


import exceptions.DuplicateEntryException;

import exceptions.RoomNotFoundException;
import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.time.LocalDate;
import java.util.*;

// TODO:  per rubrick.  need at least one use of public, private, and default methods.

/**
 * A class to provide access to the application's model classes.
 *
 * @author Brian Sigurdson
 */
public class ReservationService {

    private Map<Integer, IRoom> theRooms = new HashMap<>();
    private Set<Reservation> theReservations = new HashSet<>();
    private static ReservationService reservationService = new ReservationService();


    private ReservationService() {
        // control the ability to create instances
    }

    /**
     * A method to return a reference to the only class instance.
     *
     * @return the only class instance
     */
    public static ReservationService getInstance() {
        return reservationService;
    }

    public void addRoom(Integer roomNumber, Double price, Integer roomType) throws IllegalArgumentException,
            DuplicateEntryException {

        if (theRooms.containsKey(roomNumber)) {
            throw new DuplicateEntryException("Attempt to add a duplicate room number: " + roomNumber);
        }

        theRooms.put(roomNumber, new Room(roomNumber, price, roomType ));
    }

    public Collection<IRoom> getAllRooms() {
        return theRooms.values();
    }

    private IRoom getARoom(Integer roomNumber) throws RoomNotFoundException{
        if (isValidRoomNumber(roomNumber)) {
            return theRooms.get(roomNumber);
        } else {
            throw new RoomNotFoundException();
        }
    }

    public Reservation reserveRoom(Customer customer, Integer roomNumber, LocalDate checkInDate, LocalDate checkOutDate)
            throws RoomNotFoundException {

        IRoom room;
        try {
            room = getARoom(roomNumber);
        } catch (RoomNotFoundException e) {
            throw new RoomNotFoundException("Room number is not found in the system.");
        }

        return new Reservation(customer, room, checkInDate, checkOutDate);
    }

    public Collection<IRoom> findRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        // a list of available rooms
        Collection<IRoom> availableRooms = new ArrayList<>();

        // for each room
        for (IRoom room : theRooms.values()) {
            // give it the checkIn and checkOut dates and let it tell you if it is available
            if (room.isAvailable(checkInDate, checkOutDate)) {
                availableRooms.add(room);
            }
        }

        // return a list of available rooms
        return availableRooms;
    }

//    public Collection<Reservation> getCustomerReservation(Customer customer) {
//
//    }

    // an unnecessary method to satisfy the unnecessary requirement of having a method with default access.
    void sayHello() {
        System.out.println("Hello from the ReservationService.sayHello() method.");

    }

    public boolean isValidRoomNumber(Integer roomNumber) {
        return theRooms.containsKey(roomNumber);
    }


}
