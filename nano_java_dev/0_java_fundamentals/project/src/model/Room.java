package model;

/**
 * A class to model a room in the hotel.
 */

public class Room implements IRoom {
    private String roomNumber;
    protected Double price;
    private RoomType enumeration;

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
        return "Room number: " + roomNumber + ", Price: " + price + ", Room type: " + enumeration;
    }

    // TODO: override equals and hascode
}
