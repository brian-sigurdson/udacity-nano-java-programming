package service;

import exceptions.CustomerNotFoundException;
import exceptions.DuplicateEntryException;
import model.Customer;

import java.util.Collection;
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

    /**
     * A method to add a customer
     * @param firstName
     * @param lastName
     * @param email
     * @throws DuplicateEntryException
     * @throws DuplicateEntryException
     */
    public void addCustomer(String firstName, String lastName, String email) throws DuplicateEntryException,
            DuplicateEntryException {

        if (theCustomers.containsKey(email)) {
            throw new DuplicateEntryException("Attempt to add a duplicate user: " + email);
        }

        theCustomers.put(email, new Customer(firstName, lastName, email));
    }

    /**
     * A method to get a customer
     * @param customerEmail
     * @return
     * @throws CustomerNotFoundException
     */
    public Customer getCustomer(String customerEmail) throws CustomerNotFoundException {
        if (theCustomers.containsKey(customerEmail)) {
            return theCustomers.get(customerEmail);
        } else {
            throw new CustomerNotFoundException("Email not found: " + customerEmail);
        }
    }

    /**
     * A method to get all customers.
     * @return a collection of all customers
     */
    public Collection<Customer> getAllCustomers() {
        return theCustomers.values();
    }
}
