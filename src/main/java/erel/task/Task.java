package erel.task;

/**
 * Represents a generic task with a name and a completion status.
 * This class serves as the base class for specific task types like `Todo`, `Deadline`, and `Event`.
 */
public class Task {
    private final String name;
    private Boolean isDone;

    /**
     * Constructs a `Task` with the specified name.
     * The task is initially marked as not done.
     *
     * @param name The name or description of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Creates a `Task` object from a file-friendly format string.
     * Determines the task type and delegates the creation to the appropriate subclass.
     *
     * @param line The string in file format representing the task.
     * @return A `Task` object of the appropriate type (`Todo`, `Deadline`, or `Event`).
     * @throws IllegalArgumentException If the task type in the file format is invalid.
     */
    public static Task fromFileFormat(String line) {
        if (line == null || line.isEmpty()) {
            return null;
        }

        char taskType = line.charAt(0);

        return switch (taskType) {
            case 'T' -> Todo.fromFileFormat(line);
            case 'D' -> Deadline.fromFileFormat(line);
            case 'E' -> Event.fromFileFormat(line);
            default -> throw new IllegalArgumentException("Invalid task type: " + taskType);
        };
    }

    public Boolean isDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setDone(Boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + this.name;
    }

    public String getName() {
        return this.name;
    }

    public String toFileFormat() {
        return "";
    }
}
