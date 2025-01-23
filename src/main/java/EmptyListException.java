public class EmptyListException extends ErelException {
    public EmptyListException() {
        super("List is empty, please insert items before\ndoing anything else!");
    }
}
