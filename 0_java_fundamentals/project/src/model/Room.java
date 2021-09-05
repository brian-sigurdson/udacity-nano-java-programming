package model;

import ui.MainMenu;

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

    public Room() {    }

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
        // Although we say it is a number, we'll leave it a string, because often room identifiers could have numeric values
        if (roomNumber == null) {
            throw new IllegalArgumentException("Invalid room number.");
        } else {
            this.roomNumber = roomNumber;
        }
    }

    public void addReservation(Reservation reservation) {
        myReservations.add(reservation);
    }

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

    @Override
    public Integer getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    // TODO: isFree() is not implemented
    @Override
    public Boolean isFree() {
        return false;
    }

    // TODO: Double check that this is in line with the example output
    @Override
    public String toString() {
        return "[Room number: " + roomNumber + ", Price: " + price + ", Room type: " + enumeration + "]";
    }

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

    @Override
    public int hashCode () {
        // The recipe for a proper hash code, from Effective Java 3rd ed.
        int result = roomNumber.hashCode();
        result += 31 + price.hashCode();
        result += 31 + enumeration.hashCode();
        return result;
    }
}
