package erel.command;

import erel.storage.Storage;
import erel.task.TaskList;
import erel.ui.Ui;
import javafx.application.Platform;

/**
 * Represents an action to save the latest task list into storage to get ready
 * and exit.
 */
public class ExitAction implements Action {

    /**
     * Displays a goodbye message, and saves the updated task list to storage.
     *
     * @param tasks   The task list to which will be saved
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

        storage.saveTasksToFile(tasks);
        return ui.exit();
    }
}
