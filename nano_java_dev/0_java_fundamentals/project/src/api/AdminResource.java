package api;

import model.Customer;
import service.CustomerService;

import java.util.Collection;

// TODO: vague description of this class.
// leave and come back once I have finished the other setup tasks

/*
From the Create Resource Classes lesson:
Next you'll need to create the AdminResource class. The AdminResource should have little to no behavior contained
inside the class and should make use of the Service classes to implement its methods.
*/

/**
 * This class provides administrative resources api for the application.
 */
public class AdminResource {
    // I'm suppose to provide a static reference, but not sure to what just yet
    // ** double check, but I don't think that the directions indicated what the static reference was suppose to be,
    //      so i'll have to think about it.
    // private static Collection<>

//    public Customer getCustomer(String email) {
//
//    }
//
//    public void addRoom(List<IRoom> rooms) {
//
//    }
//
//    public Collection<IRoom> getAllRooms() {
//
//    }
//
    public static Collection<Customer> getAllCustomers() {
        return CustomerService.getInstance().getAllCustomers();
    }
//
//    public void displayAllReservations() {
//
//    }

}
