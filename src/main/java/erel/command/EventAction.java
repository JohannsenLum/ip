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
    String description;
    LocalDateTime from;
    LocalDateTime to;

    public EventAction(String description, LocalDateTime from, LocalDateTime to) {
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
     * @throws Exception If an error occurs during the execution of the action.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        Event event = new Event(description, from, to);
        tasks.addTask(event);
        ui.printInsert(event, tasks);
        storage.saveTasksToFile(tasks);
    }
}
