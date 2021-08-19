package service;


import exceptions.DuplicateEntryException;

import model.IRoom;
import model.Reservation;
import model.Room;

import java.util.*;

// TODO:  per rubrick.  need at least one use of public, private, and default methods.

/**
 * A class to provide access to the application's model classes.
 *
 * @author Brian Sigurdson
 */
public class ReservationService {

    private Map<String, IRoom> theRooms = new HashMap<>();
//    private HashMap<String, Reservation> theReservations = new HashMap<>();
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

//    public void addRoon(IRoom room) {
//
//    }

    public void addRoom(String roomNumber, Double price, Integer roomType) throws IllegalArgumentException,
            DuplicateEntryException {

        if (theRooms.containsKey(roomNumber)) {
            throw new DuplicateEntryException("Attempt to add a duplicate room number: " + roomNumber);
        }

        theRooms.put(roomNumber, new Room(roomNumber, price, roomType ));
    }

    public Collection<IRoom> getAllRooms() {
        return theRooms.values();
    }
//
//    public IRoom getARoom(String roomId) {
//
//    }
//
//    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
//
//    }
//
//    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
//
//    }
//
//    public Collection<Reservation> getCustomerReservation(Customer customer) {
//
//    }

    // an unnecessary method to satisfy the unnecessary requirement of having a method with default access.
    void sayHello() {
        System.out.println("Hello from the ReservationService.sayHello() method.");

    }


}
