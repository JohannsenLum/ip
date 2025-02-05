package erel.command;

import erel.storage.Storage;
import erel.task.TaskList;
import erel.task.Todo;
import erel.ui.Ui;

/**
 * Represents an action to add an todo task to the task list. This action creates a new `todo` task with a
 * description and adds it to the task list, and saves the updated list to storage.
 */
public class TodoAction implements Action {
    String description;

    public TodoAction(String description){
        this.description = description;
    }

    /**
     * Executes the action to add a todo task. Creates a new `todo` task, adds it to the task list, displays a
     * confirmation message, and saves the updated task list to storage.
     *
     * @param tasks   The task list to which the event task will be added.
     * @param ui      The user interface for displaying messages to the user.
     * @param storage The storage for saving the updated task list.
     * @throws Exception If an error occurs during the execution of the action.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        Todo todo = new Todo(description);
        tasks.addTask(todo);
        storage.saveTasksToFile(tasks);
        return ui.printInsert(todo, tasks);

    }
}
