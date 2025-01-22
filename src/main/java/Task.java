public class Task {
    private String name;
    private Boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
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
}