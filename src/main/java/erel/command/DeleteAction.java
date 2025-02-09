package erel.command;

import erel.storage.Storage;
import erel.task.Task;
import erel.task.TaskList;
import erel.ui.Ui;

/**
 * Represents an action to delete a task from the task list. This action removes the specified task from the task list,
 * displays a confirmation message, and saves the updated list to storage.
 */
public class DeleteAction implements Action {
    private final int taskNumber;

    /**
     * Constructs a DeleteAction with the specified task number.
     *
     * @param taskNumber The index of the task to be deleted.
     * @throws AssertionError If taskNumber is negative.
     */
    public DeleteAction(int taskNumber) {
        assert taskNumber >= 0 : "Task number must be non-negative";
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the action to delete a task.
     * Retrieves the task to be deleted, removes it from the task list, displays a confirmation message,
     * and saves the updated task list to storage.
     *
     * @param tasks   The task list from which the task will be deleted. Cannot be null.
     * @param ui      The user interface for displaying messages to the user. Cannot be null.
     * @param storage The storage for saving the updated task list. Cannot be null.
     * @return A confirmation message after successfully deleting the task.
     * @throws AssertionError if any parameter is null
     * @throws Exception If an error occurs during the execution of the action (e.g., invalid task number).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        Task t = tasks.getTask(taskNumber);
        tasks.deleteTask(taskNumber);

        storage.saveTasksToFile(tasks);

        return ui.printDelete(t, tasks);
    }
}
