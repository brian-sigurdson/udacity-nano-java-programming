package ui;

import api.HotelResource;
import exceptions.CustomerNotFoundException;
import exceptions.DuplicateEntryException;
import model.Customer;

import javax.annotation.processing.SupportedSourceVersion;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
        String userChoice;
        Customer customer;
        String checkInDate;
        String checkOutDate;

        // be sure that the user has an account, before trying attempting to create a reservation
        while (true) {
            System.out.println("An account is required to reserve a room.");
            System.out.println("Do you already have an account with us?  Please enter Y (Yes), N (No), E (Exit).");
            userChoice = scanner.next().toLowerCase();

            // 1) Get customer or create an account
            try {
                switch (userChoice) {
                    case "y":
                    case "yes":
                        System.out.println("Okay.  Let's try to lookup your account.");
                        customer = HotelResource.getCustomer(MainMenu.getUserEmail());
                        break;
                    case "n":
                    case "no":
                        System.out.println("Okay.  Let's create an account.");
                        String userEmail = MainMenu.createAccount();
                        customer = HotelResource.getCustomer(userEmail);
                        break;
                    case "e":
                    case "exit":
                            return;
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

            // 2) find and reserve a room
            // gather input
            System.out.println("Enter check-in date: yyyy-mm-dd example 2021-8-13 or 2021-08-13");
            checkInDate = scanner.next();


            System.out.println("Enter check-in date: yyyy-mm-dd example 2021-8-15 or 2021-08-15");
            checkInDate = scanner.next();

            System.out.println("Would you like to reserve a room?  y/n");
            System.out.println("Select a room number to reserve a room");

            // if we've made it to here, then you should be done
            break;
        }
    }

    private static LocalDate getReservationDate() {
        String datePattern = "^(.+)(.+).$";
        Pattern pattern = Pattern.compile(datePattern);

        String dateToVerify = scanner.next();

        if (!pattern.matcher(dateToVerify).matches()) {
//            throw new IllegalArgumentException();
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
