package erel.command;

import erel.storage.Storage;
import erel.task.TaskList;
import erel.ui.Ui;

public class MarkAction implements Action {
    private int taskNumber;

    public MarkAction(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        if(tasks.getTask(taskNumber).isDone()){
            ui.printMarkError(tasks, taskNumber);
            return;
        }
        tasks.getTask(taskNumber).setDone(true);
        ui.printMark(tasks, taskNumber);
        storage.saveTasksToFile(tasks);
    }
}
