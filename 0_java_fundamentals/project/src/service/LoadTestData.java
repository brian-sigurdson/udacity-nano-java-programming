package service;

import api.AdminResource;
import api.HotelResource;
import exceptions.DuplicateEntryException;
import exceptions.RoomNotFoundException;
import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A class to load test data for the hotel application.
 * @author Brian Sigurdson
 */
public class LoadTestData {
    private static final int NUMBER_CUSTOMERS = 10;
    private static boolean dataLoaded = false;
//    private static ArrayList<Customer> customers = new ArrayList<>();
//    private static ArrayList<IRoom> rooms = new ArrayList<>();
//    private static ArrayList<Reservation> reservations = new ArrayList<>();

    /**
     * A method to create customers.
     * @return number of customers created.
     */
    public static void loadAllData() {
        if (dataLoaded == false) {
            int numCustomers = loadCustomers();
            int numRooms = loadRooms();
            int numReservations = loadReservations();
            System.out.println("The number of customers loaded: " + numCustomers);
            System.out.println("The number of rooms loaded: " + numRooms);
            System.out.println("The number of reservations loaded: " + numReservations);
            dataLoaded = true;
        } else {
            System.out.println("The data has already been loaded.");
        }
    }

    /**
     * A method to create customers.
     * @return number of customers created.
     */
    private static int loadCustomers() {
        // hard-coding values to keep it simple
        String firstName = "first_name_";
        String lastName = "last_name_";
        String email = "@gmail.com";

        for (int i = 0; i < NUMBER_CUSTOMERS; i++ ){
            try {
                CustomerService.getInstance().addCustomer(firstName + i, lastName + i, firstName + email);
            } catch (DuplicateEntryException e) {
                // should not be an issue here
                System.out.println("Duplicate entry in LoadTestData.loadCustomers()");
            }
        }

        return NUMBER_CUSTOMERS;
    }

    /**
     * A method to create rooms.
     * @return number of rooms created.
     */
    private static int loadRooms() {
        // hard-coding values to keep it simple
        for (int i = 0; i < NUMBER_CUSTOMERS; i++ ){
            try {
                AdminResource.addRoom(i + 100, i + 100.00, i % 2);
            } catch (DuplicateEntryException e) {
                // should not be an issue here
                System.out.println("Duplicate entry in LoadTestData.loadRooms()");
            }
        }

        return NUMBER_CUSTOMERS;
    }

    /**
     * A method to create reservations.
     * @return number of reservations created.
     */
    private static int loadReservations() {
        // hard-coding values to keep it simple
        String firstName = "first_name_";
        String lastName = "last_name_";
        String email = "@gmail.com";

        Object[] customers = CustomerService.getInstance().getAllCustomers().toArray();
        Object[] rooms = ReservationService.getInstance().getAllRooms().toArray();
        String [] checkIn = {"2021-09-01","2021-09-03","2021-09-10","2021-09-11","2021-09-11","2021-09-01","2021-09-05","2021-09-09","2021-09-01","2021-09-01"};
        String [] checkOut = {"2021-09-02", "2021-09-04", "2021-09-12", "2021-09-12", "2021-09-12", "2021-09-22", "2021-09-22", "2021-09-23", "2021-09-24", "2021-09-28"};

        // customers and rooms arrays should be of length NUMBER_CUSTOMERS
        for (int i = 0; i < NUMBER_CUSTOMERS; i++ ){
            try {
                ReservationService.getInstance().reserveRoom(
                        (Customer) customers[i],
                        ((IRoom) rooms[i]).getRoomNumber(),
                        LocalDate.parse(checkIn[i]),
                        LocalDate.parse(checkOut[i]));
            } catch (RoomNotFoundException r){
                // should not be an issue here
                System.out.println("Room not found exception LoadTestData.loadReservations()");
            }
        }

        return NUMBER_CUSTOMERS;
    }

}
