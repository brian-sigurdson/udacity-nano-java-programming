package model;

import java.time.LocalDate;

/**
 * A class to represent reservations
 */
public class Reservation {
    private Customer customer;
    private IRoom room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    /**
     * A Reservation constructor
     * @param customer
     * @param room
     * @param checkInDate
     * @param checkOutDate
     */
    public Reservation(Customer customer, IRoom room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;

        // let the room know which reservation it belongs to
        this.room.addReservation(this);
    }

    /**
     * A method to get this customer
     * @return this customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * A method to get the checkin date
     * @return checkin date
     */
    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    /**
     * A method to get the checkout date
     * @return checkout date
     */
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * A method for the default printing of an object.
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        String reservationHeader = "RESERVATION DETAILS:";
        String customerInfo = "Customer: " + customer.getFirstName() + " " + customer.getLastName();
        String customerEmail = "Customer email: " + customer.getEmail();
        String roomInfo = "Room: " + room.getRoomNumber() + " - " + room.getRoomType();
        String roomPrice = "Price: $" + room.getRoomPrice();
        String checkIn = "Checkin " + checkInDate;
        String checkOut = "Checkout " + checkOutDate;

        return reservationHeader +
                "\n" +
                customerInfo +
                "\n" +
                customerEmail +
                "\n" +
                roomInfo +
                "\n" +
                roomPrice +
                "\n" +
                checkIn +
                "\n" +
                checkOut;
    }

    /**
     * A method to test for object equality
     * @param object
     * @return true or false
     */
    @Override
    public boolean equals(Object object) {
        if (object == this){
            return true;
        } else if (!(object instanceof Reservation)) {
            return false;
        } else {
            Reservation reservation = (Reservation) object;

            // reservations are equal, if they're for the same room, for the same checkIn data, and same checkOut date
            return reservation.room.equals(this.room) &&
                    reservation.checkInDate.equals(this.checkInDate) &&
                    reservation.checkOutDate.equals(this.checkOutDate);
        }
    }

    /**
     * A method to determine an object's hashcode
     * @return the object's hashcode
     */
    @Override
    public int hashCode () {
        // The recipe for a proper hash code, from Effective Java 3rd ed.
        int result = room.hashCode();
        result += 31 + checkInDate.hashCode();
        result += 31 + checkOutDate.hashCode();
        return result;
    }
}
