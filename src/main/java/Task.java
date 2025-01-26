public class Task {
    private String name;
    private Boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

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
        return (isDone? "[X] ": "[ ] ") + this.name;
    }

    public String getName() {
        return this.name;
    }

    public String toFileFormat() {
        return "";
    }
}
