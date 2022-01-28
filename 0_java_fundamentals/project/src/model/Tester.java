package model;

/**
 * A class for initial testing.
 */
public class Tester {
    public static void main(String[] args) {
        Customer customer = new Customer("firstname", "lastname", "j@domain.com");
        System.out.println(customer);

        customer = new Customer("firstname", "lastname", "email-address");
        System.out.println(customer);
    }
}
