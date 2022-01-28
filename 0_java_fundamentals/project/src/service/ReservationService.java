package service;


import exceptions.DuplicateEntryException;

import exceptions.RoomNotFoundException;
import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.time.LocalDate;
import java.util.*;

/**
 * A class to provide access to the application's model classes.
 *
 * @author Brian Sigurdson
 */
public class ReservationService {

    private Map<Integer, IRoom> theRooms = new HashMap<>();
    private Set<Reservation> theReservations = new HashSet<>();
    private static ReservationService reservationService;


    private ReservationService() {
        // control the ability to create instances
    }

    /**
     * A method to return a reference to the only class instance.
     *
     * @return the only class instance
     */
    public static ReservationService getInstance() {

        if (reservationService == null) {
            reservationService = new ReservationService();
        }

        return reservationService;
    }

    /**
     * A method to add a room.
     * @param roomNumber
     * @param price
     * @param roomType
     * @throws IllegalArgumentException
     * @throws DuplicateEntryException
     */
    public void addRoom(Integer roomNumber, Double price, Integer roomType) throws IllegalArgumentException,
            DuplicateEntryException {

        if (theRooms.containsKey(roomNumber)) {
            throw new DuplicateEntryException("Attempt to add a duplicate room number: " + roomNumber);
        }

        theRooms.put(roomNumber, new Room(roomNumber, price, roomType ));
    }

    /**
     * A method to get all rooms.
     * @return a collection of rooms
     */
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

    /**
     * A method to reserve a room
     * @param customer
     * @param roomNumber
     * @param checkInDate
     * @param checkOutDate
     * @return a reservation object
     * @throws RoomNotFoundException
     */
    public Reservation reserveRoom(Customer customer, Integer roomNumber, LocalDate checkInDate, LocalDate checkOutDate)
            throws RoomNotFoundException {

        IRoom room;
        try {
            room = getARoom(roomNumber);
        } catch (RoomNotFoundException e) {
            throw new RoomNotFoundException("Room number is not found in the system.");
        }

        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        theReservations.add(reservation);
        return reservation;
    }

    /**
     * A method to find all rooms available for a checkin and checkout dates.
     * @param checkInDate
     * @param checkOutDate
     * @return A collection of available rooms.
     */
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

    /**
     * A method to get all reservations.
     * @return a collection of all reservations
     */
    public Collection<Reservation> getAllReservations() {
        return theReservations;
    }

    /**
     * A method to get all customer reservations
     * @param customer
     * @return a collection of customer reservations
     */
    public Collection<Reservation> getCustomerReservations(Customer customer) {
        Collection<Reservation> customerReservations = new ArrayList<>();

        for (Reservation reservation: theReservations) {
            if (customer.equals(reservation.getCustomer())) {
                customerReservations.add(reservation);
            }
        }

        return customerReservations;
    }

    // an unnecessary method to satisfy the requirement of having a method with default access.
    void sayHello() {
        System.out.println("Hello from the ReservationService.sayHello() method.");

    }

    /**
     * A method to test for room number validity
     * @param roomNumber
     * @return true or false
     */
    public boolean isValidRoomNumber(Integer roomNumber) {
        return theRooms.containsKey(roomNumber);
    }


}
