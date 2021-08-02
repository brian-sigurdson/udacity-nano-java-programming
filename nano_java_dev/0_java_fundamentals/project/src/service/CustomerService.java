package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

// TODO: vague description of this class.
// leave and come back once I have finished the other setup tasks
public class CustomerService {
    private static Collection<Customer> theCustomers = new ArrayList<>();

    public static void addCustomer(String email, String firstName, String lastName) throws IllegalArgumentException {
        theCustomers.add(new Customer(firstName, lastName, email));
    }

//    public Customer getCustomer(String customerEmail) {
//
//    }
//
//    public Collection<Customer> getAllCustomers() {
//
//    }
}
