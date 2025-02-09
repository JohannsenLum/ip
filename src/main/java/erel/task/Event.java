package erel.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a specific start and end time. Extends the `Task` class and adds functionality for
 * handling events.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an `Event` task with the specified name, start time, and end time.
     *
     * @param name The name or description of the event task.
     * @param from The start date and time of the event.
     * @param to   The end date and time of the event.
     */
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an `Event` task from a file-friendly format string. Parses the string to extract the task's status, name,
     * start time, and end time.
     *
     * @param fileFormat The string in file format representing the event task.
     * @return An `Event` task object.
     */
    public static Event fromFileFormat(String fileFormat) {
        String[] lines = fileFormat.split(" \\| ");
        boolean isDone = lines[1].equals("1");
        String description = lines[2];
        LocalDateTime from = LocalDateTime.parse(lines[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime to = LocalDateTime.parse(lines[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        Event event = new Event(description, from, to);
        if (isDone) {
            event.setDone(true);
        }
        return event;
    }

    /**
     * Returns a string representation of the event task. Includes the task type, status, name, start time, and end
     * time.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy, HH:mm a");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    /**
     * Converts the event task to a file-friendly format for storage. Includes the task type, status, name, start time,
     * and end time in a standardized format.
     *
     * @return A string in file format representing the event task.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "E | " + (isDone() ? "1" : "0") + " | " + super.getName() + " | " + from.format(
                formatter) + " | " + to.format(formatter);
    }

    public LocalDateTime getTo() {
        return to;
    }
}
