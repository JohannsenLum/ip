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

    /**
     * Constructs a FindAction with the specified keyword.
     *
     * @param keyword The keyword used to search for tasks. Cannot be null or empty.
     * @throws AssertionError if the keyword is null or empty.
     */
    public FindAction(String keyword) {
        assert keyword != null && !keyword.isBlank() : "Keyword cannot be null or empty";
        this.keyword = keyword;
    }

    /**
     * Executes the action to find matching tasks in the task list displays a list of tasks or a task not found
     * message.
     *
     * @param tasks The task list that to find the tasks from
     * @param ui    The user interface for displaying messages to the user.
     * @throws AssertionError if any parameter is null.
     * @throws Exception If an error occurs during the execution of the action.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        List<Task> matchingTasks = tasks.findTasks(keyword);
        return ui.printMatchingTasks(matchingTasks);
    }
}
