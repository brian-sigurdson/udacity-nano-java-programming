package ui;

import api.AdminResource;
import exceptions.DuplicateEntryException;
import model.Customer;
import model.IRoom;
import service.LoadTestData;

import java.util.Collection;
import java.util.Scanner;

/**
 * A class to provide an administrative menu and gather user input.
 *
 * @author Brian Sigurdson
 */
public class AdminMenu {

    private static Scanner scanner = new Scanner(System.in);

    private static void displayAdminMenu() {
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("          Hotel Reservation Application          ");
        System.out.println("------------------ ADMIN MENU --------------------");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Load test data");
        System.out.println("6. Back to Main Menu");
        System.out.println("--------------------------------------------------");
        System.out.println("Please select a number for the menu option");
    }

    /**
     * A method to let the user process various actions.
     */
    public static void getUserSelection(){
        String selection = null;
        int optionNumber = -1;

        while (true) {
            AdminMenu.displayAdminMenu();

            // if the scanner is ever null, then fail now
            if (scanner != null) {
                selection = scanner.next();
            } else {
                throw new NullPointerException("scanner is null");
            }

            // test for valid numeric value
            try {
                optionNumber = Integer.parseInt(selection);
            } catch (NumberFormatException e){
                AdminMenu.invalidInputMessage();
                continue;
            }

            // test for valid menu selection number
            switch (optionNumber) {
                case 1:
                    AdminMenu.getAllCustomers();
                    break;
                case 2:
                    AdminMenu.getAllRooms();
                    break;
                case 3:
                    AdminMenu.printAllReservations();
                    break;
                case 4:
                    AdminMenu.createRoom();
                    break;
                case 5:
                    AdminMenu.loadTestData();
                    break;
                case 6:
                    return;
                default:
                    AdminMenu.invalidInputMessage();
                    break;

            }
        }
    }

    private static void printAllReservations() {
        AdminResource.displayAllReservations();
    }

    private static void loadTestData() {
        LoadTestData.loadAllData();
    }

    private static void getAllRooms() {
        Collection<IRoom> theRooms = AdminResource.getAllRooms();
        for (IRoom room: theRooms) {
            System.out.println(room);
        }
    }

    private static void getAllCustomers() {
        Collection<Customer> customers = AdminResource.getAllCustomers();
        for (Customer customer: customers) {
            System.out.println(customer);
        }
    }

    // maybe unnecessary, but I'd like to keep the messaging in one place for now.
    private static void invalidInputMessage() {
        System.out.println("Warning: Invalid input.");
    }

    private static void createRoom() {
        Integer roomNumber;
        Double price;
        Integer roomType;
        boolean stopNow = false;

        while (!stopNow) {

            // get information to create a room
            roomNumber = AdminMenu.getRoomNumber();
            price = AdminMenu.getPricePerNight();
            roomType = AdminMenu.getRoomType();

            // create new room or throw exception
            try {
                AdminResource.addRoom(roomNumber, price, roomType);
            } catch (IllegalArgumentException e) {
                System.out.println();
                System.out.println("*****************************");
                System.out.println("Error creating new account.");
                System.out.println(e.getMessage());
                System.out.println("*****************************");
                System.out.println();
                continue;
            } catch (DuplicateEntryException duplicateEntryException) {
                System.out.println();
                System.out.println("*****************************");
                System.out.println("Error creating new room.");
                System.out.println("Room already exists.");
                System.out.println("*****************************");
                System.out.println();
                continue;
            }

            // if here, then the account has been created
            stopNow = AdminMenu.stopNow();
        }
    }

    private static Boolean stopNow() {
        String userChoice;

        while (true) {
            System.out.println("Would you like to add another room? y/n");
            userChoice = scanner.next();

            if (userChoice.equalsIgnoreCase("n")) {
                // stopNow is true, do not add another room
                return true;
            } else if (userChoice.equalsIgnoreCase("y")) {
                // stopNow is false, add another room
                return false;
            } else {
                System.out.println("Please enter Y (Yes) or N (No).");
            }
        }
    }

    private static Integer getRoomType() {
        Integer roomType;

        while (true) {
            // gather user input to create a room
            System.out.println("Enter room type: 1 for single bed, 2 for double bed:");
            try {
                roomType = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid value for room type.");
                continue;
            }

            if (roomType == null) {
                System.out.println("Room type cannot be null.");
                continue;
            } else {
                return roomType;
            }
        }
    }

    private static Double getPricePerNight() {
        Double price;

        while (true) {
            // gather user input to create a room
            System.out.println("Enter price per night:");
            try {
                price = Double.parseDouble(scanner.next());
            } catch (NumberFormatException e){
                System.out.println("Invalid format for price.");
                continue;
            }

            if (price == null) {
                System.out.println("Price cannot be null.");
            } else if (price < 0) {
                System.out.println("Price cannot be less than zero.");
            } else {
                return price;
            }
        }
    }

    private static Integer getRoomNumber() {
        Integer roomNumber;

        while (true) {
            // gather user input to create a room
            System.out.println("Enter a numeric room number:  (eg. 100, 101, ...)");
            try {
                roomNumber = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format.");
                continue;
            }

            if (roomNumber == null) {
                System.out.println("Room number cannot be null.");
                continue;
            } else {
                return roomNumber;
            }
        }
    }
}
