package erel.command;

import erel.storage.Storage;
import erel.task.TaskList;
import erel.task.Todo;
import erel.ui.Ui;

public class TodoAction implements Action {
    String description;

    public TodoAction(String description){
        this.description = description;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        Todo todo = new Todo(description);
        tasks.addTask(todo);
        storage.saveTasksToFile(tasks);
        ui.printInsert(todo, tasks);

    }
}
