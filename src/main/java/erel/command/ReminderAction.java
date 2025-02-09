package erel.command;

import java.util.List;

import erel.storage.Storage;
import erel.task.Task;
import erel.task.TaskList;
import erel.ui.Ui;

/**
 * The ReminderAction class handles the action of retrieving and displaying
 * upcoming reminders based on the specified reminder type (e.g., deadlines, events).
 */
public class ReminderAction implements Action {
    private final String type;

    /**
     * Constructs a ReminderAction with the specified reminder type.
     *
     * @param type The type of reminder (e.g., "deadline" or "event").
     */
    public ReminderAction(String type) {
        this.type = type;
    }

    /**
     * Executes the action to retrieve the upcoming reminders of the specified type
     * and display them using the provided Ui object.
     *
     * @param tasks   The list of tasks to search for upcoming reminders.
     * @param ui      The `Ui` object used for outputting the reminders.
     * @param storage The storage used for saving or loading tasks (not used in this method).
     * @return A string containing the list of upcoming reminders, or a message if there are none.
     * @throws Exception If there is an error during the execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        List<Task> reminders = tasks.getUpcomingReminders(type);
        return ui.printReminderList(type, reminders);
    }
}
