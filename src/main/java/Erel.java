import java.io.IOException;

public class Erel {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    public Erel(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (IOException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();

        while (true) {
            try {
                String input = ui.readCommand();
                Action action = Parser.parseCommand(input, tasks);
                action.execute(tasks, ui, storage);

                if (action instanceof ExitAction) {
                    return;
                }
            } catch (ErelException e) {

                ui.printErelError(e.getMessage());
            } catch (Exception e) {
                ui.printExceptionError(e.getMessage());
            }
        }
    };

    public static void main(String[] args) {
        new Erel("./data/erel.txt").run();
    }
}
