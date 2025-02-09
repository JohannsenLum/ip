package erel.task;

/**
 * Represents a todo task, which is a simple task with no specific deadline or event time.
 * Extends the `Task` class and provides functionality specific to todo tasks.
 */
public class Todo extends Task {

    /**
     * Constructs a `Todo` task with the specified name.
     *
     * @param name The name or description of the todo task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns a string representation of the todo task.
     * Includes the task type and the formatted string from the parent class.
     *
     * @return A formatted string representing the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the todo task to a file-friendly format for storage.
     * Includes the task type, status, and name in a standardized format.
     *
     * @return A string in file format representing the todo task.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone() ? "1" : "0") + " | " + super.getName();
    }

    /**
     * Creates a `Todo` task from a file-friendly format string.
     * Parses the string to extract the task's status and name.
     *
     * @param line The string in file format representing the todo task.
     * @return A `Todo` task object.
     */
    public static Todo fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Todo todo = new Todo(description);
        if (isDone) {
            todo.setDone(true);
        }
        return todo;
    }
}
