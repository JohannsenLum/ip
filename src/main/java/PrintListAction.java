public class PrintListAction implements Action {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        ui.printList(tasks);
    }
}
