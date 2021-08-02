package ui;

import api.HotelResource;

import java.util.Scanner;

/**
 * A class to provide a main menu and gather user input.
 *
 * @author Brian Sigurdson
 */


public class MainMenu {

    private static Scanner scanner = new Scanner(System.in);

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
        System.out.println("Welcome to the Hotel Reservation Application");
        System.out.println("--------------------------------------------------");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("--------------------------------------------------");
        System.out.println("Please select a number for the menu option");

    }

    // TODO: incorporate scanner

    /**
     * A method to let the user process various actions.
     */
    public static void getUserSelection(){

        boolean stopNow = false;
        String selection = null;
        int optionNumber = -1;

        while (!stopNow) {
            MainMenu.displayMainMenu();
            selection = scanner.next();

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
                    System.out.println("1. Find and reserve a room");
                    break;
                case 2:
                    System.out.println("2. See my reservations");
                    break;
                case 3:
//                    System.out.println("3. Create an account");
                    MainMenu.createAccount();
                    break;
                case 4:
                    System.out.println("4. Admin");
                    break;
                case 5:
                    System.out.println("5. Exit");
                    stopNow = true;
                    break;
                default:
                    MainMenu.invalidInputMessage();
                    break;

            }
        }
    }

    private static void createAccount() {
        String email, firstName, lastName;
        boolean stopNow = false;

        while (!stopNow) {

            // gather user input to create an account
            System.out.println("Enter Email format: name@domain.com");
            email = scanner.next();

            if (email == null) {
                System.out.println("Email address cannot be null.");
                continue;
            }

            System.out.println("Enter first name:");
            firstName = scanner.next();

            if (firstName == null) {
                System.out.println("First name cannot be null.");
                continue;
            }

            System.out.println("Enter last name:");
            lastName = scanner.next();

            if (lastName == null) {
                System.out.println("Last name cannot be null.");
                continue;
            }

            // create new account or throw execption
            try {
                HotelResource.createACustomer(email, firstName, lastName);
            } catch (IllegalArgumentException e) {
                System.out.println("Error creating new account.");
                System.out.println("Invalid email format: " + email);
                continue;
            }

            // if here, then the account has been created
            stopNow = true;

        }

    }

    // maybe unnecessary, but I'd like to keep the messaging in one place for now.
    private static void invalidInputMessage() {
        System.out.println("Warning: Invalid input.");
    }
}
