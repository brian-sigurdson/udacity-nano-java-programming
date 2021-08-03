package service;

import exceptions.DuplicateEntryException;
import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.DuplicateFormatFlagsException;
import java.util.HashMap;

/**
 * A class to provide access to the application's model classes.
 *
 * @author Brian Sigurdson
 */
public class CustomerService {
    private HashMap<String, Customer> theCustomers = new HashMap<>();

    private static CustomerService customerService = new CustomerService();

    private CustomerService() {
        // control the ability to create instances
    }

    /**
     * A method to return a reference to the only class instance.
     *
     * @return the only class instance
     */
    public static CustomerService getInstance() {
        return customerService;
    }

    public void addCustomer(String email, String firstName, String lastName) throws IllegalArgumentException,
            DuplicateEntryException {

        if (theCustomers.containsKey(email)) {
            throw new DuplicateEntryException();
        }

        theCustomers.put(email, new Customer(firstName, lastName, email));
    }

//    public Customer getCustomer(String customerEmail) {
//
//    }

    public Collection<Customer> getAllCustomers() {
        return theCustomers.values();
    }
}
