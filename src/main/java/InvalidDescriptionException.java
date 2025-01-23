public class InvalidDescriptionException extends ErelException {
    public InvalidDescriptionException(String command) {
        super(String.format("The description of a %s cannot be empty.", command));
    }

}
