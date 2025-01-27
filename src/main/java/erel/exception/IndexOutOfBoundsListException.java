package erel.exception;

public class IndexOutOfBoundsListException extends ErelException {
    public IndexOutOfBoundsListException(String message) {
        super(String.format("%s is an invalid number! Please try again",message));
    }
}
