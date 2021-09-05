package exceptions;

/**
 * A custom exception to signal that a customer chose to exit the current interaction.
 *
 * @author Brian Sigurson
 * Resource:  https://www.tutorialspoint.com/how-can-we-create-a-custom-exception-in-java
 */
public class CustomerChoseExitException extends Exception{
    private String message;

    public CustomerChoseExitException() {
    }

    public CustomerChoseExitException(String msg) {
        message = msg;
    }

    @Override
    public String toString() {
        return message;
    }
}