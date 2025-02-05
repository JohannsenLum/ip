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

    public DeleteAction(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the action to delete a task. Retrieves the task to be deleted, removes it from the task list, displays a
     * confirmation message, and saves the updated task list to storage.
     *
     * @param tasks   The task list from which the task will be deleted.
     * @param ui      The user interface for displaying messages to the user.
     * @param storage The storage for saving the updated task list.
     * @throws Exception If an error occurs during the execution of the action (e.g., invalid task number).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        Task t = tasks.getTask(taskNumber);
        tasks.deleteTask(taskNumber);

        storage.saveTasksToFile(tasks);

        return ui.printDelete(t, tasks);
    }
}
