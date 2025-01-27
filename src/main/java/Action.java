public interface Action {
    void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;
}
