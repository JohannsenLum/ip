package erel.command;

import erel.storage.Storage;
import erel.task.TaskList;
import erel.ui.Ui;

public class ExitAction implements Action {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        storage.saveTasksToFile(tasks);
        ui.exit();
    }
}
