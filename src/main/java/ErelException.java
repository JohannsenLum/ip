public class ErelException extends Exception {
    public ErelException(String message) {
        super("OPPS!!! " + message);
    }
}
