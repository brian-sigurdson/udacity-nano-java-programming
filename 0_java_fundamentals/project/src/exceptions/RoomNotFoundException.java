package exceptions;

/**
 * A custom exception to signal that a room was not found due to invalid room identifer.
 *
 * @author Brian Sigurson
 * Resource:  https://www.tutorialspoint.com/how-can-we-create-a-custom-exception-in-java
 */
public class RoomNotFoundException extends Exception{
    private String message;

    /**
     * A RoomNotFoundException constructor
     */
    public RoomNotFoundException() {
    }

    /**
     * RoomNotFoundException
     * @param msg
     */
    public RoomNotFoundException(String msg) {
        message = msg;
    }

    /**
     * A method for the default printing of an object.
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return message;
    }
}
