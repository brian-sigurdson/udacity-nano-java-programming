package service;


import model.Customer;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

/**
 * A class to provide access to the application's model classes.
 *
 * @author Brian Sigurdson
 */
public class ReservationService {

    // I haven't decided yet on the container for reservation yet.
    // I need to think about what makes sense and try to keep it simple
    //    private static Collections reservations;
    //    private HashMap<String, Customer> theCustomers = new HashMap<>();

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

    // TODO:  per rubrick.  need at least one use of public, private, and default methods.
}
