package api;

import exceptions.DuplicateEntryException;
import model.Customer;
import model.Reservation;
import model.RoomType;
import service.CustomerService;
import service.ReservationService;

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
    // part of the original method lists give to us.
    // maybe use it for bulk loading?
    //
    // yes.  I saw another student's comment that the only thing they could come up with was to use it with the
    // load test data on the admin menu.  I'll use it for that as well, since I could envision reading data from a
    // file and creating objects, which are stored in a list, then passing that list on to create rooms.
//    public void addRoom(List<IRoom> rooms) {
//
//    }

    public static void addRoom(String roomNumber, Double price, Integer roomType) throws IllegalArgumentException,
            DuplicateEntryException {

        ReservationService.getInstance().addRoom(roomNumber, price, roomType);
    }
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
