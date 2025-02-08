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
        assert description != null && !description.trim().isEmpty() : "Description cannot be null or empty";
        assert by != null : "Deadline date (by) cannot be null";
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the action to add a deadline task.
     * This method creates a new {@code Deadline} task, adds it to the task list, displays a confirmation message,
     * and saves the updated task list to storage.
     *
     * @param tasks   The task list to which the deadline task will be added. Cannot be null.
     * @param ui      The user interface for displaying messages. Cannot be null.
     * @param storage The storage system for saving the updated task list. Cannot be null.
     * @return A confirmation message after successfully adding the deadline task.
     * @throws AssertionError if any parameter is null.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        Deadline deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        storage.saveTasksToFile(tasks);

        return ui.printInsert(deadline, tasks);
    }
}
