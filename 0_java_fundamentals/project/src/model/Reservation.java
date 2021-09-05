package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reservation {
    private Customer customer;
    private IRoom room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Reservation(Customer customer, IRoom room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;

        // let the room know which reservation it belongs to
        this.room.addReservation(this);
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

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

    @Override
    public int hashCode () {
        // The recipe for a proper hash code, from Effective Java 3rd ed.
        int result = room.hashCode();
        result += 31 + checkInDate.hashCode();
        result += 31 + checkOutDate.hashCode();
        return result;
    }
}
