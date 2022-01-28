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


/**
 * This class provides hotel resources api for the application.
 */
public class HotelResource {

    /**
     * A method to get a customer object.
     * @param email of customer
     * @return customer
     * @throws CustomerNotFoundException
     */
    public static Customer getCustomer(String email) throws CustomerNotFoundException {
        return CustomerService.getInstance().getCustomer(email);
    }

    /**
     * A method to create a new customer.
     * @param email
     * @param firstName
     * @param lastName
     * @throws IllegalArgumentException
     * @throws DuplicateEntryException
     */
    public static void createACustomer(String email, String firstName, String lastName) throws IllegalArgumentException,
            DuplicateEntryException {

        CustomerService.getInstance().addCustomer(email, firstName, lastName);
    }

    /**
     * A method to get all reservations for a customer.
     * @param customer reservations
     * @return
     */
    public static Collection<Reservation> getCustomerReservations(Customer customer) {
        return ReservationService.getInstance().getCustomerReservations(customer);
    }

    /**
     * A method to validate a room number.
     * @param roomNumber
     * @return
     */
    public static boolean isValidRoomNumber(Integer roomNumber) {
        return ReservationService.getInstance().isValidRoomNumber(roomNumber);
    }

    /**
     * A method to find all rooms.
     * @param checkInDate
     * @param checkOutDate
     * @return
     */
    public static Collection<IRoom> findRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        return ReservationService.getInstance().findRooms(checkInDate, checkOutDate);
    }

    /**
     * A method to reserve a room.
     * @param customer
     * @param roomNumber
     * @param checkInDate
     * @param checkOutDate
     * @return
     * @throws RoomNotFoundException
     */
    public static Reservation reserveRoom(Customer customer, Integer roomNumber, LocalDate checkInDate, LocalDate checkOutDate)
            throws RoomNotFoundException {

        return ReservationService.getInstance().reserveRoom(customer, roomNumber, checkInDate, checkOutDate);
    }
}
