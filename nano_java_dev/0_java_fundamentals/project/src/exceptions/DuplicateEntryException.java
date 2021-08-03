package exceptions;

/**
 * A custom exception to signal the presence of an existing entry in some container.
 *
 * @author Brian Sigurson
 * Resource:  https://www.tutorialspoint.com/how-can-we-create-a-custom-exception-in-java
 */
public class DuplicateEntryException extends Exception{

    public DuplicateEntryException() {
    }

    @Override
    public String toString() {
        return "Duplicate entry is present.";
    }
}
