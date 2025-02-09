package erel.exception;

/**
 * Exception thrown when an index provided for a task list operation is out of bounds.
 */
public class IndexOutOfBoundsListException extends ErelException {
    public IndexOutOfBoundsListException(String message) {
        super(String.format("%s is an invalid number! Please try again", message));
    }
}
