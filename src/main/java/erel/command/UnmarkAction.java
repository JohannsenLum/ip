package erel.command;

import erel.storage.Storage;
import erel.task.TaskList;
import erel.ui.Ui;

public class UnmarkAction implements Action {
    private int taskNumber;

    public UnmarkAction(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        if(!tasks.getTask(taskNumber).isDone()){
            ui.printUnMarkError(tasks, taskNumber);
            return;

        }
        tasks.getTask(taskNumber).setDone(false);
        ui.printUnmark(tasks, taskNumber);
        storage.saveTasksToFile(tasks);
    }

}
