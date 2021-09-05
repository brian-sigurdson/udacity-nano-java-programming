package exceptions;

/**
 * A custom exception to signal the presence of an existing entry in some container.
 *
 * @author Brian Sigurson
 * Resource:  https://www.tutorialspoint.com/how-can-we-create-a-custom-exception-in-java
 */
public class DuplicateEntryException extends Exception{
    private String message;

    public DuplicateEntryException() {
    }

    public DuplicateEntryException(String msg) {
        message = msg;
    }

    @Override
    public String toString() {
        return message;
    }
}
