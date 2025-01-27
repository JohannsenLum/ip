package erel.command;

import java.util.List;

import erel.storage.Storage;
import erel.task.Task;
import erel.task.TaskList;
import erel.ui.Ui;

/**
 * Represents an action to find a task in the task list. This action finds a task in the task list with the given
 * keyword
 */
public class FindAction implements Action {
    private final String keyword;

    public FindAction(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the action to find matching tasks in the task list displays a list of tasks or a task not found
     * message.
     *
     * @param tasks The task list that to find the tasks from
     * @param ui    The user interface for displaying messages to the user.
     * @throws Exception If an error occurs during the execution of the action.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        List<Task> matchingTasks = tasks.findTasks(keyword);
        ui.printMatchingTasks(matchingTasks);
    }
}
