package erel.command;

import erel.storage.Storage;
import erel.task.TaskList;
import erel.ui.Ui;

/**
 * Represents an action to unmark a task in the task list. This action unmarks an existing task
 * and updates it to the task list, and saves the updated list to storage.
 */
public class UnmarkAction implements Action {
    private final int taskNumber;

    /**
     * Constructs an UnmarkAction for the specified task number.
     *
     * @param taskNumber The index of the task to unmark. Must be a valid task number.
     * @throws AssertionError If taskNumber is invalid (negative or out of bounds).
     */
    public UnmarkAction(int taskNumber) {
        assert taskNumber >= 0 : "Task number must be non-negative";
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the action to unmark a task in the task list.
     * displays a confirmation message, and saves the updated task list to storage.
     *
     * @param tasks   The task list to which a task will be unmarked
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

        if (!tasks.getTask(taskNumber).isDone()) {
            return ui.printUnMarkError(tasks, taskNumber);


        }
        tasks.getTask(taskNumber).setDone(false);

        storage.saveTasksToFile(tasks);

        return ui.printUnmark(tasks, taskNumber);
    }

}
