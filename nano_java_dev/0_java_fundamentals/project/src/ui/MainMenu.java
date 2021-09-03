package ui;

import api.HotelResource;
import exceptions.CustomerNotFoundException;
import exceptions.DuplicateEntryException;
import exceptions.RoomNotFoundException;
import model.Customer;
import model.IRoom;
import model.Reservation;
import service.ReservationService;

import javax.annotation.processing.SupportedSourceVersion;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * A class to provide a main menu and gather user input.
 *
 * @author Brian Sigurdson
 */


public class MainMenu {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Entry point to the application.
     *
     * @param args the return value to the operating system
     */
    public static void main(String[] args) {
        MainMenu.getUserSelection();
    }

    /**
     * The main menu.
     */
    private static void displayMainMenu() {
        System.out.println("--------------------------------------------------");
        System.out.println("          Hotel Reservation Application          ");
        System.out.println("------------------- MAIN MENU --------------------");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("--------------------------------------------------");
        System.out.println("Please select a number for the menu option");
    }

    /**
     * A method to let the user process various actions.
     */
    public static void getUserSelection(){

        boolean stopNow = false;
        String selection = null;
        int optionNumber = -1;

        while (!stopNow) {
            MainMenu.displayMainMenu();

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
                MainMenu.invalidInputMessage();
                continue;
            }

            // test for valid menu selection number
            switch (optionNumber) {
                case 1:
                    MainMenu.findAndReserveRoom();
                    break;
                case 2:
                    // ** NOT IMPLEMENTED **
                    System.out.println("2. See my reservations");
                    break;
                case 3:
                    MainMenu.createAccount();
                    break;
                case 4:
                    AdminMenu.getUserSelection();
                    break;
                case 5:
                    stopNow = true;
                    break;
                default:
                    MainMenu.invalidInputMessage();
                    break;

            }
        }
    }

    private static void findAndReserveRoom() {
        LocalDate checkInDate;
        LocalDate checkOutDate;
        Customer customer;
        Reservation reservation;
        Integer roomNumber;

            // 1) find and reserve a room
            // gather input
            while (true) {
                try {
                    System.out.println("Enter check-in date: yyyy-mm-dd example 2021-08-13");
                    checkInDate = MainMenu.getReservationDate(scanner.next());
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("The value you entered is not in a valid format.");
                    continue;
                }
            }

            while (true) {
                try {
                    System.out.println("Enter check-out date: yyyy-mm-dd example 2021-08-20");
                    checkOutDate = MainMenu.getReservationDate(scanner.next());
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("The value you entered is not in a valid format.");
                    continue;
                }
            }

            if (checkInDate.equals(checkOutDate) || checkInDate.isAfter(checkOutDate)) {
                System.out.println("The check-in date must be on a date that precedes the check-out date.");
                System.out.println("Please start again from the Main Menu.");
                return;
            }

            // present rooms available here
            MainMenu.viewAvailableRoomsForDates(checkInDate, checkOutDate);

            outer: while (true) {
                // do they want to reserve a room?
                System.out.println("Would you like to reserve a room?  y/yes, n/no.");
                switch (scanner.next().toLowerCase()) {
                    case "y":
                    case "yes":
                        customer = MainMenu.getUserAccount();

                        inner: while (true) {
                            System.out.println("Select a room number to reserve a room:");
                            try {
                                roomNumber = Integer.parseInt(scanner.next());
                                break inner;
                            } catch (NumberFormatException e) {
                                System.out.println("The room number is not a numeric value. Please try again.");
                                continue inner;
                            }
                        }

                        try {
                            reservation = MainMenu.reserveRoom(customer, roomNumber, checkInDate, checkOutDate);
                            // print reservation
                            System.out.println(reservation);
                            // done
                            break outer;
                        } catch (RoomNotFoundException e) {
                            System.out.println("The room number you have entered is not found in the system.");
                            System.out.println("Please try again.");
                            continue;
                        }
                    case "n":
                    case "no":
                        return;
                    default:
                        MainMenu.invalidInputMessage();
                        break;
                }
            }
    }

    private static boolean isValidRoomNumber(Integer roomNumber) {
        return ReservationService.getInstance().isValidRoomNumber(roomNumber);
    }

    private static Reservation reserveRoom(Customer customer, Integer roomNumber, LocalDate checkInDate,
                                           LocalDate checkOutDate) throws RoomNotFoundException {
        return ReservationService.getInstance().reserveRoom(customer, roomNumber, checkInDate, checkOutDate);
    }

    private static Customer getUserAccount() {
        // be sure that the user has an account, before trying attempting to create a reservation
        while (true) {
            System.out.println("An account is required to reserve a room.");
            System.out.println("Do you already have an account with us?  Please enter Y (Yes), N (No)");

            // 1) Get customer or create an account
            try {
                switch (scanner.next().toLowerCase()) {
                    case "y":
                    case "yes":
                        System.out.println("Okay.  Let's try to lookup your account.");
                        return HotelResource.getCustomer(MainMenu.getUserEmail());
                    case "n":
                    case "no":
                        System.out.println("Okay.  Let's create an account.");
                        String userEmail = MainMenu.createAccount();
                        return HotelResource.getCustomer(userEmail);
                    default:
                        MainMenu.invalidInputMessage();
                        break;
                }

            } catch (CustomerNotFoundException e){
                System.out.println();
                System.out.println("*******************************");
                System.out.println("Error: Email address not found.");
                System.out.println("*******************************");
                System.out.println();
                continue;
            }
        }
    }

    private static void viewAvailableRoomsForDates(LocalDate checkInDate, LocalDate checkOutDate) {
        boolean roomsFound = false;
        int maxIterations = 2;

        // limit the checking of available rooms to 2 iterations
        for (int i = 0; i < maxIterations; i++) {
            Collection<IRoom> availableRooms = ReservationService.getInstance().findRooms(checkInDate, checkOutDate);

            if (availableRooms.size() == 0 && i == 0) {
                // no rooms available on the first iteration
                System.out.println("There are not any rooms available for your desired dates.");
                System.out.println("We will check for room availability by shifting your date range 7 days into the future");
                checkInDate = checkInDate.plusDays(7);
                checkOutDate = checkOutDate.plusDays(7);
                continue;
            }

            if (availableRooms.size() == 0 && i == 1) {
                // no rooms available on the second iteration
                System.out.println("There are not any rooms available for your desired dates.");
                System.out.println("Please try a different date range.");
                return;
            }

            // if you've made it here, then there are rooms to display
            System.out.println("The following rooms are available for your requested dates.");
            for (IRoom room : availableRooms) {
                System.out.println(room);
            }
        }
    }

    private static LocalDate getReservationDate(String dateToVerify) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String datePattern = "^(\\d{4})-(\\d{2})-(\\d{2})$";
        Pattern pattern = Pattern.compile(datePattern);

        if (pattern.matcher(dateToVerify).matches()) {
            return LocalDate.parse(dateToVerify, dateTimeFormatter);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static String getUserEmail() {
        String email;

        while (true) {
            // gather user input to create an account
            System.out.println("Enter Email format: name@domain.com");
            email = scanner.next();

            if (email == null) {
                System.out.println("Email address cannot be null.");
                continue;
            } else {
                return email;
            }
        }
    }

    private static String getUserFirstName() {
        String firstName;

        while (true) {
            System.out.println("Enter first name:");
            firstName = scanner.next();

            if (firstName == null) {
                System.out.println("First name cannot be null.");
                continue;
            } else {
                return firstName;
            }
        }
    }

    private static String getUserLastName() {
        String lastName;

        while (true) {
            System.out.println("Enter last name:");
            lastName = scanner.next();

            if (lastName == null) {
                System.out.println("Last name cannot be null.");
                continue;
            } else {
                return lastName;
            }
        }
    }

    private static String createAccount() {
        String email, firstName, lastName;

        while (true) {
            email = MainMenu.getUserEmail();
            firstName = MainMenu.getUserFirstName();
            lastName = MainMenu.getUserLastName();

            // create new account or throw exception
            try {
                HotelResource.createACustomer(email, firstName, lastName);
            } catch (IllegalArgumentException e) {
                System.out.println();
                System.out.println("*****************************");
                System.out.println("Error creating new account.");
                System.out.println("Invalid email format: " + email);
                System.out.println("*****************************");
                System.out.println();
                continue;
            } catch (DuplicateEntryException duplicateEntryException) {
                System.out.println();
                System.out.println("*****************************");
                System.out.println("Error creating new account.");
                System.out.println("Email address already in use.");
                System.out.println("*****************************");
                System.out.println();
                continue;
            }

            // if we are here, then break
            break;
        }
        return email;
    }

    // maybe unnecessary, but I'd like to keep the messaging in one place for now.
    private static void invalidInputMessage() {
        System.out.println("Warning: Invalid input.");
    }
}
