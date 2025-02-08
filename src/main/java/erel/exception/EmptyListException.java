package erel.exception;

/**
 * Thrown when an operation is attempted on an empty task list.
 * This exception is used to indicate that the list must contain items
 * before performing certain actions that require tasks to be present.
 */
public class EmptyListException extends ErelException {
    public EmptyListException() {
        super("List is empty, please insert items before\ndoing anything else!");
    }
}
