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
                "Customer: " + customer + "; Room: " + room + "; Checkin " + checkInDate + "; Checkout " + checkOutDate;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this){
            return true;
        } else if (!(object instanceof Reservation)) {
            return false;
        } else {
            Reservation reservation = (Reservation) object;
            return reservation.customer.equals(this.customer) && reservation.room.equals(this.room) &&
                    reservation.checkInDate.equals(this.checkInDate) && reservation.checkOutDate.equals(this.checkOutDate);
        }
    }

    @Override
    public int hashCode () {
        // The recipe for a proper hash code, from Effective Java 3rd ed.
        int result = customer.hashCode();
        result += 31 + room.hashCode();
        result += 31 + checkInDate.hashCode();
        result += 31 + checkOutDate.hashCode();
        return result;
    }
}
