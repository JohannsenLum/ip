package erel.command;

import erel.storage.Storage;
import erel.task.TaskList;
import erel.ui.Ui;

public interface Action {
    void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;
}
