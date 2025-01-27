package erel.command;

import erel.storage.Storage;
import erel.task.Deadline;
import erel.task.TaskList;
import erel.ui.Ui;

import java.time.LocalDateTime;

public class DeadlineAction implements Action {
    String description;
    LocalDateTime by;

    public DeadlineAction(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        ui.printInsert(deadline, tasks);
        storage.saveTasksToFile(tasks);
    }
}
