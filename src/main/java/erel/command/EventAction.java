package erel.command;

import java.time.LocalDateTime;

import erel.storage.Storage;
import erel.task.Event;
import erel.task.TaskList;
import erel.ui.Ui;

/**
 * Represents an action to add an event task to the task list. This action creates a new `Event` task with a
 * description, start time, and end time, adds it to the task list, and saves the updated list to storage.
 */
public class EventAction implements Action {
    private final String description;
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an EventAction with the given description, start time, and end time.
     *
     * @param description The description of the event. Cannot be null or empty.
     * @param from        The start time of the event. Cannot be null.
     * @param to          The end time of the event. Cannot be null.
     * @throws AssertionError if any parameter is invalid.
     */

    public EventAction(String description, LocalDateTime from, LocalDateTime to) {
        assert description != null && !description.isBlank() : "Description cannot be null or empty";
        assert from != null : "Start time (from) cannot be null";
        assert to != null : "End time (to) cannot be null";

        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the action to add an event task. Creates a new `Event` task, adds it to the task list, displays a
     * confirmation message, and saves the updated task list to storage.
     *
     * @param tasks   The task list to which the event task will be added.
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
        Event event = new Event(description, from, to);
        tasks.addTask(event);

        storage.saveTasksToFile(tasks);

        return ui.printInsert(event, tasks);
    }
}
