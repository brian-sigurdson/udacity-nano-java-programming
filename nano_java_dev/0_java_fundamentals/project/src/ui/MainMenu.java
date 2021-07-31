package ui;

import java.util.Scanner;

/**
 * A class to provide a main menu and gather user input.
 *
 * @author Brian Sigurdson
 */


public class MainMenu {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * The main menu.
     */
    public static void displayMainMenu() {
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
     * A method to get the user's desired menu selection.
     *
     * @return An int representing the selected menu item.
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
                    stopNow = true;
                    break;
                case 2:
                    System.out.println("2. See my reservations");
                    stopNow = true;
                    break;
                case 3:
                    System.out.println("3. Create an account");
                    stopNow = true;
                    break;
                case 4:
                    System.out.println("4. Admin");
                    stopNow = true;
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

    // maybe unnecessary, but I'd like to keep the messaging in one place for now.
    private static void invalidInputMessage() {
        System.out.println("Warning: Invalid input.");
    }
}
