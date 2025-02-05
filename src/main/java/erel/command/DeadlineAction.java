package erel.command;

import java.time.LocalDateTime;

import erel.storage.Storage;
import erel.task.Deadline;
import erel.task.TaskList;
import erel.ui.Ui;

/**
 * Represents an action to add a deadline task to the task list. This action creates a new `Deadline` task with a
 * description and a due date, adds it to the task list, and saves the updated list to storage.
 */
public class DeadlineAction implements Action {
    private final String description;
    private final LocalDateTime by;

    public DeadlineAction(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the action to add a deadline task. Creates a new `Deadline` task, adds it to the task list, displays a
     * confirmation message, and saves the updated task list to storage.
     *
     * @param tasks   The task list to which the deadline task will be added.
     * @param ui      The user interface for displaying messages to the user.
     * @param storage The storage for saving the updated task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        storage.saveTasksToFile(tasks);

        return ui.printInsert(deadline, tasks);
    }
}
