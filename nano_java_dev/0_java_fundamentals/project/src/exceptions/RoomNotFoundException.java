package exceptions;

/**
 * A custom exception to signal that a room was not found due to invalid room identifer.
 *
 * @author Brian Sigurson
 * Resource:  https://www.tutorialspoint.com/how-can-we-create-a-custom-exception-in-java
 */
public class RoomNotFoundException extends Exception{
    private String message;

    public RoomNotFoundException() {
    }

    public RoomNotFoundException(String msg) {
        message = msg;
    }

    @Override
    public String toString() {
        return message;
    }
}
