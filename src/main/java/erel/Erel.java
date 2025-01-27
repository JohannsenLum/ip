package erel;

import erel.command.Action;
import erel.command.ExitAction;
import erel.exception.ErelException;
import erel.parser.Parser;
import erel.storage.Storage;
import erel.task.TaskList;
import erel.ui.Ui;

import java.io.IOException;

/**
 * The main class for the Erel Bot.
 * Erel is a task management bot that allows users to manage
 * tasks through a command-line interface
 */
public class Erel {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    /**
     * Constructs an Erel object with the specified file path for storage.
     * Initializes the user interface, storage, and task list.
     *
     * @param filePath The file path where tasks are stored.
     */
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

    /**
     * The entry point of the Erel bot.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Erel("./data/erel.txt").run();
    }

    /**
     * Runs the Erel bot.
     * Displays a greeting message and continuously reads user input,
     * processes commands, and executes actions until the user exits.
     */
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
    }
}
