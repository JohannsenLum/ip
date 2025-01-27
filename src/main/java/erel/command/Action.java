package erel.command;

import erel.storage.Storage;
import erel.task.TaskList;
import erel.ui.Ui;

/**
 * Represents an action that can be executed in the Erel bot. Actions are commands that perform specific operations on
 * the task list, such as adding, deleting, or marking tasks as done.
 */
public interface Action {
    /**
     * Executes the action using the provided task list, user interface, and storage.
     *
     * @param tasks   The task list on which the action will be performed.
     * @param ui      The user interface for displaying messages to the user.
     * @param storage The storage for saving or loading tasks.
     * @throws Exception If an error occurs during the execution of the action.
     */
    void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;
}
