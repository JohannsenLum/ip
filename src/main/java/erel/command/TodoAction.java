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
    private final String description;

    /**
     * Constructs a TodoAction with the specified description.
     *
     * @param description The description of the todo task. Cannot be null or empty.
     * @throws AssertionError If description is null or empty.
     */
    public TodoAction(String description) {
        assert description != null && !description.trim().isEmpty() : "Description must not be null or empty";
        this.description = description;
    }

    /**
     * Executes the action to add a todo task. Creates a new `todo` task, adds it to the task list, displays a
     * confirmation message, and saves the updated task list to storage.
     *
     * @param tasks   The task list to which the event task will be added.
     * @param ui      The user interface for displaying messages to the user.
     * @param storage The storage for saving the updated task list.
     * @throws AssertionError If any parameter is null.
     * @throws Exception If an error occurs during the execution of the action.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        Todo todo = new Todo(description);
        tasks.addTask(todo);
        storage.saveTasksToFile(tasks);
        return ui.printInsert(todo, tasks);

    }
}
