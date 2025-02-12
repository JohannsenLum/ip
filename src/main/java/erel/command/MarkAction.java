package erel.command;

import erel.storage.Storage;
import erel.task.TaskList;
import erel.ui.Ui;

/**
 * Represents an action to mark a task in the task list. This action marks an existing task with an x,
 * and updates it to the task list, and saves the updated list to storage.
 */
public class MarkAction implements Action {
    private int taskNumber;

    /**
     * Constructs a MarkAction with the specified task number.
     *
     * @param taskNumber The index of the task to be marked as done.
     * @throws AssertionError If taskNumber is negative.
     */
    public MarkAction(int taskNumber) {
        assert taskNumber >= 0 : "Task number must be non-negative";
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the action to mark a task in the task list.
     * displays a confirmation message, and saves the updated task list to storage.
     *
     * @param tasks   The task list to which a task will be marked
     * @param ui      The user interface for displaying messages to the user.
     * @param storage The storage for saving the updated task list.
     * @throws AssertionError if any parameter is null.
     * @throws Exception If an error occurs during the execution of the action.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        if (tasks.getTask(taskNumber).isDone()) {
            return ui.printMarkError(tasks, taskNumber);

        }
        tasks.getTask(taskNumber).setDone(true);

        storage.saveTasksToFile(tasks);

        return ui.printMark(tasks, taskNumber);
    }
}
