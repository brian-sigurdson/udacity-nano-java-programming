package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A class to load test data for the hotel application.
 * @author Brian Sigurdson
 */
public class LoadTestData {
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<IRoom> rooms = new ArrayList<>();
    private static ArrayList<Reservation> reservations = new ArrayList<>();

    /**
     * A method to create customers.
     * @return number of customers created.
     */
    public static void loadAllData() {
        int numCustomers = loadCustomers();
        int numRooms = loadRooms();
        int numReservations = loadReservations();
        System.out.println("The number of customers loaded: " + numCustomers);
        System.out.println("The number of rooms loaded: " + numRooms);
        System.out.println("The number of reservations loaded: " + numReservations);
    }

    /**
     * A method to create customers.
     * @return number of customers created.
     */
    private static int loadCustomers() {
        // hard-coding values to keep it simple
        customers.add(new Customer("first_name_0", "last_name_0", "fn0@gmail.com"));
        customers.add(new Customer("first_name_1","last_name_1","fn1@gmail.com"));
        customers.add(new Customer("first_name_2","last_name_2","fn2@gmail.com"));
        customers.add(new Customer("first_name_3","last_name_3","fn3@gmail.com"));
        customers.add(new Customer("first_name_4","last_name_4","fn4@gmail.com"));
        customers.add(new Customer("first_name_5","last_name_5","fn5@gmail.com"));
        customers.add(new Customer("first_name_5","last_name_5","fn5@gmail.com"));
        customers.add(new Customer("first_name_6","last_name_6","fn6@gmail.com"));
        customers.add(new Customer("first_name_7","last_name_7","fn7@gmail.com"));
        customers.add(new Customer("first_name_8","last_name_8","fn8@gmail.com"));
        customers.add(new Customer("first_name_9","last_name_9","fn9@gmail.com"));

        return customers.size();
    }

    /**
     * A method to create rooms.
     * @return number of rooms created.
     */
    private static int loadRooms() {
        // hard-coding values to keep it simple
        rooms.add(new Room(100,100.00,1));
        rooms.add(new Room(101,101.00,2));
        rooms.add(new Room(102,102.00,1));
        rooms.add(new Room(103,103.00,2));
        rooms.add(new Room(104,104.00,1));
        rooms.add(new Room(105,105.00,2));
        rooms.add(new Room(106,106.00,1));
        rooms.add(new Room(107,107.00,2));
        rooms.add(new Room(108,108.00,1));
        rooms.add(new Room(109,109.00,2));

        return rooms.size();
    }

    /**
     * A method to create reservations.
     * @return number of reservations created.
     */
    private static int loadReservations() {
        // hard-coding values to keep it simple
        String [] checkIn = {"2021-09-01","2021-09-03","2021-09-10","2021-09-11","2021-09-11","2021-09-01","2021-09-05","2021-09-09","2021-09-01","2021-09-01"};
        String [] checkOut = {"2021-09-02", "2021-09-04", "2021-09-12", "2021-09-12", "2021-09-12", "2021-09-22", "2021-09-22", "2021-09-23", "2021-09-24", "2021-09-28"};

        for(int i = 0; i < customers.size(); i++) {
            reservations.add(new Reservation(customers.get(i),rooms.get(i), LocalDate.parse(checkIn[i]),LocalDate.parse(checkOut[i])));
        }

        return reservations.size();
    }

}
