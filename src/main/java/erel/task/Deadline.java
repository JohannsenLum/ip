package erel.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a specific due date and time. Extends the `Task` class and adds functionality for
 * handling deadlines.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a `Deadline` task with the specified name and due date.
     *
     * @param name The name or description of the deadline task.
     * @param by   The due date and time of the deadline task.
     */
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    /**
     * Creates a `Deadline` task from a file-friendly format string. Parses the string to extract the task's status,
     * name, and due date.
     *
     * @param fileFormat The string in file format representing the deadline task.
     * @return A `Deadline` task object.
     */
    public static Deadline fromFileFormat(String fileFormat) {
        String[] lines = fileFormat.split(" \\| ");
        boolean isDone = lines[1].equals("1");
        String descrption = lines[2];
        LocalDateTime by = LocalDateTime.parse(lines[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        Deadline deadline = new Deadline(descrption, by);
        if (isDone) {
            deadline.setDone(true);
        }
        return deadline;
    }

    /**
     * Returns a string representation of the deadline task. Includes the task type, status, name, and due date.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy, HH:mm a");
        String formattedDate = by.format(formatter);

        // Convert only "AM"/"PM" to "am"/"pm"
        String modifiedDate = formattedDate.replace("AM", "am").replace("PM", "pm");

        return "[D]" + super.toString() + " (by: " + modifiedDate + ")";
    }


    /**
     * Converts the deadline task to a file-friendly format for storage. Includes the task type, status, name, and due
     * date in a standardized format.
     *
     * @return A string in file format representing the deadline task.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "D | " + (isDone() ? "1" : "0") + " | " + super.getName() + " | " + by.format(formatter);
    }

    public LocalDateTime getBy() {
        return by;
    }
}
