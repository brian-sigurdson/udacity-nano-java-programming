package api;

import exceptions.DuplicateEntryException;
import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;

/**
 * This class provides administrative resources api for the application.
 */
public class AdminResource {

    /**
     * A method to add a room.
     * @param roomNumber
     * @param price
     * @param roomType
     * @throws IllegalArgumentException
     * @throws DuplicateEntryException
     */
    public static void addRoom(Integer roomNumber, Double price, Integer roomType) throws IllegalArgumentException,
            DuplicateEntryException {

        ReservationService.getInstance().addRoom(roomNumber, price, roomType);
    }

    /**
     * A method to get all rooms.
     * @return A collection of all rooms.
     */
    public static Collection<IRoom> getAllRooms() {
        return ReservationService.getInstance().getAllRooms();
    }

    /**
     * A method to get all customers.
     * @return a collection of rooms for a specific customer
     */
    public static Collection<Customer> getAllCustomers() {
        return CustomerService.getInstance().getAllCustomers();
    }

    /**
     * A method to display all reservations.
     */
    public static void displayAllReservations() {
        Collection<Reservation> reservations = ReservationService.getInstance().getAllReservations();

        for (Reservation reservation : reservations) {
            System.out.println();
            System.out.println(reservation);
        }
    }
}
