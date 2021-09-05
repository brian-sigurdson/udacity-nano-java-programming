package api;

import exceptions.CustomerNotFoundException;
import exceptions.DuplicateEntryException;
import exceptions.RoomNotFoundException;
import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;
import java.time.LocalDate;
import java.util.Collection;

// TODO: vague description of this class.
// leave and come back once I have finished the other setup tasks

/*
From the Create Resource Classes lesson:
Next you'll need to create the HotelResource class. The HotelResource should have little to no behavior contained
inside the class and should make use of the Service classes to implement its methods.
 */

/**
 * This class provides hotel resources api for the application.
 */
public class HotelResource {
    // This static reference is listed in the resource classes page, but doesn't explicitly say what they should do.
    // The service classes are intended to be be stateful and we are instructed to use a static reference to do it.
    // The instructions for this class say that it should have little to no behavior and should make use of the
    // service classes, so I plan to not use any static references in this class.
//    private static Collection<Reservation> reservations;


    public static Customer getCustomer(String email) throws CustomerNotFoundException {
        return CustomerService.getInstance().getCustomer(email);
    }

    public static void createACustomer(String email, String firstName, String lastName) throws IllegalArgumentException,
            DuplicateEntryException {

        CustomerService.getInstance().addCustomer(email, firstName, lastName);
    }

    public static Collection<Reservation> getCustomerReservations(Customer customer) {
        return ReservationService.getInstance().getCustomerReservations(customer);
    }

    public static boolean isValidRoomNumber(Integer roomNumber) {
        return ReservationService.getInstance().isValidRoomNumber(roomNumber);
    }

    public static Collection<IRoom> findRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        return ReservationService.getInstance().findRooms(checkInDate, checkOutDate);
    }

    public static Reservation reserveRoom(Customer customer, Integer roomNumber, LocalDate checkInDate, LocalDate checkOutDate)
            throws RoomNotFoundException {

        return ReservationService.getInstance().reserveRoom(customer, roomNumber, checkInDate, checkOutDate);
    }

//    public IRoom getRoom(String roomNumber) {
//
//    }
//
//    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
//
//    }
//
//    public Collection<Reservation> getCustomerReservations(String customerEmail) {
//
//    }
//
//    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
//
//    }

}
