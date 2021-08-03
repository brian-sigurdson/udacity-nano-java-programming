package ui;

import api.AdminResource;
import api.HotelResource;
import exceptions.DuplicateEntryException;
import model.Customer;
import service.CustomerService;

import java.util.Collection;
import java.util.Scanner;

/**
 * A class to provide an administrative menu and gather user input.
 *
 * @author Brian Sigurdson
 */



public class AdminMenu {

    private static void displayAdminMenu() {
        System.out.println("--------------------------------------------------");
        System.out.println("          Hotel Reservation Application          ");
        System.out.println("------------------ ADMIN MENU --------------------");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        System.out.println("--------------------------------------------------");
        System.out.println("Please select a number for the menu option");
    }

    /**
     * A method to let the user process various actions.
     */
    public static void getUserSelection(Scanner scanner){

        boolean stopNow = false;
        String selection = null;
        int optionNumber = -1;

        while (!stopNow) {
            AdminMenu.displayAdminMenu();
            selection = scanner.next();

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
                    // ** NOT IMPLEMENTED **
                    System.out.println("2. See all Rooms");
                    break;
                case 3:
                    // ** NOT IMPLEMENTED **
                    System.out.println("3. See all Reservations");
                    break;
                case 4:
                    // ** NOT IMPLEMENTED **
                    System.out.println("4. Add a Room");
                    break;
                case 5:
                    return;
                default:
                    AdminMenu.invalidInputMessage();
                    break;

            }
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
}
