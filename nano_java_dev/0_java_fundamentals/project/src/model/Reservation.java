package model;

import java.util.Date;

public class Reservation {
    private Customer customer;
    private IRoom room;
    // TODO: consider replacing with java.time.* classes
    private Date checkInDate;
    private Date checkOutDate;


    // TODO: Double check that this is in line with the example output
    @Override
    public String toString() {
        return "RESERVATION DETAILS: " +
                "Customer [" + customer + "] Room [" + room + "] Checkin [" + checkInDate + "] Checkout [" + checkOutDate + "]";
    }

    // TODO: override equals and hascode
}
