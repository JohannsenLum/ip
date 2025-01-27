package erel.command;

import erel.storage.Storage;
import erel.task.Task;
import erel.task.TaskList;
import erel.ui.Ui;

public class DeleteAction implements Action {
    private int taskNumber;

    public DeleteAction(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        Task t = tasks.getTask(taskNumber);
        tasks.deleteTask(taskNumber);
        ui.printDelete(t, tasks);
        storage.saveTasksToFile(tasks);
    }
}
