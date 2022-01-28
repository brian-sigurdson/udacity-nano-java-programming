package exceptions;

/**
 * A custom exception to signal the presence of an existing entry in some container.
 *
 * @author Brian Sigurson
 * Resource:  https://www.tutorialspoint.com/how-can-we-create-a-custom-exception-in-java
 */
public class CustomerNotFoundException extends Exception{
    private String message;

    /**
     * A CustomerNotFoundException constructor
     */
    public CustomerNotFoundException() {
    }

    /**
     * A CustomerNotFoundException constructor
     */
    public CustomerNotFoundException(String msg) {
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
