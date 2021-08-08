package model;

import java.time.LocalDate;

public class Reservation {
    private Customer customer;
    private IRoom room;
    
    /*
    I know you can probably convert a Date object to milliseconds from time zero, but Date objects are basically depricated.
    Use java.time.* classes, which is preferred as of Java 8.
    This page has a good example of some convenience methods associated with java.time.* that will make implementing
    searches easier:  https://howtodoinjava.com/java/date-time/compare-localdates/
     */
//    private Date checkInDate;
//    private Date checkOutDate;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;


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
