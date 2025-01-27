public class ExitAction implements Action {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        storage.saveTasksToFile(tasks);
        ui.exit();
    }
}
