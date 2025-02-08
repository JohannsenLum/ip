package erel.exception;

/**
 * A general exception class for errors in the Erel application.
 * This class serves as the base class for all exceptions specific to the Erel bot,
 * allowing for centralized exception handling.
 */
public class ErelException extends Exception {
    public ErelException(String message) {
        super(message);
    }
}
