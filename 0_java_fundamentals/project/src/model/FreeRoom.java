package model;

/**
 * A class to model a free room.
 */
public class FreeRoom extends Room {

    /**
     * The FreeRoom constructor
     * @param roomNumber
     * @param price
     * @param roomType
     */
    public FreeRoom(Integer roomNumber, Double price, Integer roomType) {
        super(roomNumber, 0.0, roomType);
    }

    /**
     * A method for the default printing of an object.
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "FreeRoom: " + super.toString();
    }
}
