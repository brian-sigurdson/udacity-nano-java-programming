package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to model a room in the hotel.
 */
public class Room implements IRoom {
    private Integer roomNumber;
    private Double price;
    private RoomType enumeration;
    private List<Reservation> myReservations = new ArrayList<>();

    /**
     * A Room constructor.
     */
    public Room() {    }

    /**
     * A parameterized room constructor.
     * @param roomNumber
     * @param price
     * @param roomType
     * @throws IllegalArgumentException
     */
    public Room(Integer roomNumber, Double price, Integer roomType) throws IllegalArgumentException {
        // test and set price
        if (price < 0) {
            throw new IllegalArgumentException("Room price cannot be a negative value.");
        } else {
            this.price = price;
        }

        // test and set RoomType
        switch (roomType.intValue()) {
            case 1:
                // single room
                enumeration = RoomType.SINGLE;
                break;
            case 2:
                // double room
                enumeration = RoomType.DOUBLE;
                break;
            default:
                // technically, we should never get here, but add default for completeness
                throw new IllegalArgumentException("Invalid room of type: " + roomType.intValue());
        }

        // test and set room number
        if (roomNumber == null) {
            throw new IllegalArgumentException("Invalid room number.");
        } else {
            this.roomNumber = roomNumber;
        }
    }

    /**
     * A method to add a reservation.
     * @param reservation
     */
    public void addReservation(Reservation reservation) {
        myReservations.add(reservation);
    }

    /**
     * A method to check if a room is available.
     * @param checkInDate
     * @param checkOutDate
     * @return
     */
    public boolean isAvailable(LocalDate checkInDate, LocalDate checkOutDate) {
        // iterate over this room's reservations and determine if the room is available for the given dates.
        for (Reservation reservation : myReservations) {
            LocalDate reservationCheckIn = reservation.getCheckInDate();
            LocalDate reservationCheckOut = reservation.getCheckOutDate();

            if (checkInDate.isAfter(reservationCheckOut) || checkOutDate.isBefore(reservationCheckIn)) {
                continue;
            }

            if (
                    checkInDate.equals(reservationCheckIn) ||
                    checkInDate.equals(reservationCheckOut) ||
                    checkOutDate.equals(reservationCheckIn) ||
                    checkOutDate.equals(reservationCheckOut)
            ) {
                // there is a scheduling conflict
                return false;
            }

            if (
                    checkInDate.isBefore(reservationCheckIn) &&
                    checkOutDate.isAfter(reservationCheckIn) &&
                    checkOutDate.isBefore(reservationCheckOut)
            ) {
                // there is a scheduling conflict
                return false;
            }

            if (
                    checkInDate.isAfter(reservationCheckIn) &&
                    checkInDate.isBefore(reservationCheckOut) &&
                    checkOutDate.isAfter(reservationCheckOut)
            ) {
                // there is a scheduling conflict
                return false;
            }
        }

        // if you made it to here, then there should not be any scheduling conflict
        return true;
    }

    /**
     * A method to get a room number.
     * @return room number
     */
    @Override
    public Integer getRoomNumber() {
        return roomNumber;
    }

    /**
     * A method to get a room price.
     * @return room price
     */
    @Override
    public Double getRoomPrice() {
        return price;
    }

    /**
     * A method to get a room type.
     * @return room type
     */
    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    /**
     * A method to to determine if a room is a free type room.
     * @return
     */
    @Override
    public Boolean isFree() {
        return false;
    }

    /**
     * A method for the default printing of an object.
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "[Room number: " + roomNumber + ", Price: " + price + ", Room type: " + enumeration + "]";
    }

    /**
     * A method to test for object equality
     * @param object
     * @return true or false
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (object == this){
            return true;
        }

        if (!(object instanceof Room)) {
            return false;
        } else {
            Room room = (Room) object;
            return room.roomNumber.equals(this.roomNumber) &&
                    room.price == this.price && room.enumeration == this.enumeration;
        }
    }

    /**
     * A method to determine an object's hashcode
     * @return the object's hashcode
     */
    @Override
    public int hashCode () {
        // The recipe for a proper hash code, from Effective Java 3rd ed.
        int result = roomNumber.hashCode();
        result += 31 + price.hashCode();
        result += 31 + enumeration.hashCode();
        return result;
    }
}
