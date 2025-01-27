package erel.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import erel.command.Action;
import erel.command.Command;
import erel.command.DeadlineAction;
import erel.command.DeleteAction;
import erel.command.EventAction;
import erel.command.ExitAction;
import erel.command.MarkAction;
import erel.command.PrintListAction;
import erel.command.TodoAction;
import erel.command.UnmarkAction;
import erel.exception.EmptyListException;
import erel.exception.ErelException;
import erel.exception.IndexOutOfBoundsListException;
import erel.exception.InvalidDescriptionException;
import erel.task.TaskList;

/**
 * The `Parser` class is responsible for parsing user input into executable commands. It handles the validation of input
 * and the creation of corresponding `Action` objects.
 */
public class Parser {
    /**
     * Parses a date-time string into a `LocalDateTime` object. The expected format is "yyyy-MM-dd HH:mm".
     *
     * @param input The date-time string to parse.
     * @return The parsed `LocalDateTime` object.
     * @throws DateTimeParseException If the input string does not match the expected format.
     */
    public static LocalDateTime parseDateTime(String input) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(input, formatter);
    }

    /**
     * Parses a user input string into an `Action` object. Validates the input and creates the appropriate command based
     * on the input.
     *
     * @param input The user input string.
     * @param tasks The task list to validate task numbers against.
     * @return An `Action` object corresponding to the user's command.
     * @throws ErelException If the input is invalid or cannot be parsed.
     */
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

    /**
     * Validates the input for mark/unmark commands. Ensures the task number is valid and the task list is not empty.
     *
     * @param input The user input string.
     * @param tasks The task list to validate against.
     * @throws InvalidDescriptionException   If the input is missing a task number.
     * @throws IndexOutOfBoundsListException If the task number is out of bounds.
     * @throws EmptyListException            If the task list is empty.
     */
    private static void checkValidMarkUnmark(String input, TaskList tasks) throws InvalidDescriptionException,
            IndexOutOfBoundsListException, EmptyListException {
        String[] inputArr = input.split(" ");
        if (tasks.isEmpty()) {
            throw new EmptyListException();
        }
        if (inputArr.length < 2 || inputArr[1].trim().isEmpty()) {
            throw new InvalidDescriptionException(inputArr[0]);
        }
        int taskNumber = Integer.parseInt(inputArr[1]);
        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            throw new IndexOutOfBoundsListException(inputArr[1]);
        }
    }

    /**
     * Validates the input for the delete command. Ensures the task number is valid and the task list is not empty.
     *
     * @param taskNumber The task number to delete.
     * @param tasks      The task list to validate against.
     * @throws IndexOutOfBoundsListException If the task number is out of bounds.
     * @throws EmptyListException            If the task list is empty.
     */
    private static void checkValidDelete(int taskNumber, TaskList tasks) throws IndexOutOfBoundsListException,
            EmptyListException {
        if (tasks.isEmpty()) {
            throw new EmptyListException();
        }
        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            throw new IndexOutOfBoundsListException(Integer.toString(taskNumber));
        }
    }

    /**
     * Validates the input for commands requiring a description (e.g., todo, deadline, event). Ensures the description
     * is not empty.
     *
     * @param inputArr The input string split into parts.
     * @throws InvalidDescriptionException If the description is missing or empty.
     */
    private static void checkValidDescription(String[] inputArr) throws InvalidDescriptionException {
        if (inputArr.length <= 1 || inputArr[1].trim().isEmpty()) {
            throw new InvalidDescriptionException(inputArr[0]);
        }
    }

    /**
     * Validates the input for the deadline command. Ensures the input includes a description and a valid date-time
     * format.
     *
     * @param input The user input string.
     * @throws ErelException If the input is missing a description, "/by", or has an invalid date-time format.
     */
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

    /**
     * Validates the input for the event command. Ensures the input includes a description, "/from", and "/to" with
     * valid date-time formats.
     *
     * @param input The user input string.
     * @throws ErelException If the input is missing a description, "/from", "/to", or has an invalid date-time format.
     */
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
