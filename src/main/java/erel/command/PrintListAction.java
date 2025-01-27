package erel.command;

import erel.storage.Storage;
import erel.task.TaskList;
import erel.ui.Ui;

/**
 * Represents an action that prints the entire task list.
 */
public class PrintListAction implements Action {


    /**
     * Prints the entire task lists
     *
     * @param tasks   The task list that will be printed
     * @param ui      The user interface for displaying messages to the user.
     * @param storage The storage for saving the updated task list.
     * @throws Exception If an error occurs during the execution of the action.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        ui.printList(tasks);
    }
}
