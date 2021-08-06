package model;

import ui.MainMenu;

/**
 * A class to model a room in the hotel.
 */

public class Room implements IRoom {
    private String roomNumber;
    protected Double price;
    private RoomType enumeration;

    public Room() {

    }

    public Room(String roomNumber, Double price, Integer roomType) throws IllegalArgumentException {

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
                throw new IllegalArgumentException("Invalid room type.");
        }

        // test and set room number
        // Although we say it is a number, we'll leave it a string, because often room identifiers could have numeric values
        if (roomNumber == null) {
            throw new IllegalArgumentException("Invalid room number.");
        } else {
            this.roomNumber = roomNumber;
        }
    }

    @Override
    public String getRoomNumber() {
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
        if (object == this){
            return true;
        } else if (!(object instanceof Room)) {
            return false;
        } else {
            Room room = (Room) object;
            return room.roomNumber.equalsIgnoreCase(this.roomNumber) &&
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
