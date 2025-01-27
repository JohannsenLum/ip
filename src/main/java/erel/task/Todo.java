package erel.task;

public class Todo extends Task {

    public Todo(String name){
        super(name);
    }

    @Override
    public String toString(){
        return  "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone() ? "1" : "0") + " | " + super.getName();
    }

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
