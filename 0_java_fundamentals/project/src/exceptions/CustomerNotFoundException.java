package exceptions;

/**
 * A custom exception to signal the presence of an existing entry in some container.
 *
 * @author Brian Sigurson
 * Resource:  https://www.tutorialspoint.com/how-can-we-create-a-custom-exception-in-java
 */
public class CustomerNotFoundException extends Exception{
    private String message;

    public CustomerNotFoundException() {
    }

    public CustomerNotFoundException(String msg) {
        message = msg;
    }

    @Override
    public String toString() {
        return message;
    }
}
