import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static LocalDateTime parseDateTime(String input) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(input, formatter);
    }

    public static Action parseCommand(String input, TaskList tasks) throws ErelException {
        String[] parts = input.split(" ");
        String action = parts[0];
        String details = parts.length > 1 ? parts[1] : "";

        switch (Command.fromString(action)) {
            case MARK:
                checkValidMarkUnmark(input, tasks);
                return new MarkAction(Integer.parseInt(details) - 1);
            case UNMARK:
                checkValidMarkUnmark(input, tasks);
                return new UnmarkAction(Integer.parseInt(details) - 1);
            case DELETE:
                checkValidDelete(Integer.parseInt(details), tasks);
                return new DeleteAction(Integer.parseInt(details) - 1);
            case TODO:
                checkValidDescription(input.split(" ", 2));
                return new TodoAction(input.substring(5));
            case DEADLINE:
                checkValidDeadline(input);
                String[] deadlineParts = input.split(" /by ");
                LocalDateTime by = parseDateTime(deadlineParts[1]);
                return new DeadlineAction(deadlineParts[0].substring(9), by);

            case EVENT:
                checkValidEvent(input);
                String[] eventParts = input.split(" /from | /to ");
                LocalDateTime from = parseDateTime(eventParts[1]);
                LocalDateTime to = parseDateTime(eventParts[2]);
                return new EventAction(eventParts[0].substring(6), from, to);
            case LIST:
                return new PrintListAction();
            case BYE:
                return new ExitAction();
            default:
                throw new ErelException("Unknown command: " + action);
        }
    }

    private static void checkValidMarkUnmark(String input, TaskList tasks) throws InvalidDescriptionException, IndexOutOfBoundsListException, EmptyListException {
        String[] inputArr = input.split(" ");
        if(tasks.isEmpty()) {
            throw new EmptyListException();
        }
        if(inputArr.length < 2 || inputArr[1].trim().isEmpty()) {
            throw new InvalidDescriptionException(inputArr[0]);
        }
        int taskNumber = Integer.parseInt(inputArr[1]);
        if(taskNumber <= 0 || taskNumber > tasks.size()) {
            throw new IndexOutOfBoundsListException(inputArr[1]);
        }
    }
    private static void checkValidDelete(int taskNumber, TaskList tasks) throws IndexOutOfBoundsListException, EmptyListException {
        if(tasks.isEmpty()) {
            throw new EmptyListException();
        }
        if(taskNumber <= 0 || taskNumber > tasks.size()) {
            throw new IndexOutOfBoundsListException(Integer.toString(taskNumber));
        }
    }
    private static void checkValidDescription(String[] inputArr) throws InvalidDescriptionException {
        if (inputArr.length <= 1 || inputArr[1].trim().isEmpty()) {
            throw new InvalidDescriptionException(inputArr[0]);
        }
    }
    private static void checkValidDeadline(String input) throws ErelException {
        String[] inputArr = input.split(" ", 2);
        String[] deadlineParts = input.split(" /by ");
        if (inputArr.length <= 1 || inputArr[1].trim().isEmpty()) {
            throw new InvalidDescriptionException(inputArr[0]);
        }
        if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
            throw new ErelException(" A deadline must include '/by' followed by a time.");
        }
        try {
            LocalDateTime by = parseDateTime(deadlineParts[1]);
        } catch (DateTimeParseException e) {
            throw new ErelException("Invalid date format. Please use \n'yyyy-MM-dd HH:mm' (e.g. 2019-12-02 18:00).");
        }
    }

    private static void checkValidEvent(String input) throws ErelException {
        String[] inputArr = input.split(" ", 2);
        String[] eventParts = input.split(" /from | /to ");

        if (inputArr.length <= 1 || inputArr[1].trim().isEmpty()) {
            throw new InvalidDescriptionException(inputArr[0]);
        }
        if (eventParts.length < 3 || eventParts[1].trim().isEmpty() || eventParts[2].trim().isEmpty()) {
            throw new ErelException(" An event must include '/from' and '/to'.");
        }
        try {
            LocalDateTime to = parseDateTime(eventParts[1]);
            LocalDateTime from = parseDateTime(eventParts[2]);
        } catch (DateTimeParseException e) {
            throw new ErelException("Invalid date format. Please use \n'yyyy-MM-dd HH:mm' (e.g. 2019-12-02 18:00).");
        }
    }
}
