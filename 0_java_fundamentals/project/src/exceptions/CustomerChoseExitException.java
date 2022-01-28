package exceptions;

/**
 * A custom exception to signal that a customer chose to exit the current interaction.
 *
 * @author Brian Sigurson
 * Resource:  https://www.tutorialspoint.com/how-can-we-create-a-custom-exception-in-java
 */
public class CustomerChoseExitException extends Exception{
    private String message;

    /**
     * A CustomerChoseExitException constructor
     */
    public CustomerChoseExitException() {
    }

    /**
     * A CustomerChoseExitException constructor
     */
    public CustomerChoseExitException(String msg) {
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