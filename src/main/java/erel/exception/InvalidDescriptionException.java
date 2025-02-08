package erel.exception;

/**
 * Exception thrown when the description of a task or command is invalid, such as when it is empty.
 */
public class InvalidDescriptionException extends ErelException {
    public InvalidDescriptionException(String command) {
        super(String.format("The description of a %s cannot be empty.", command));
    }

}
