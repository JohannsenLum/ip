package erel.ui;

import erel.task.Task;
import erel.task.TaskList;

import java.util.List;
import java.util.Scanner;


/**
 * The `Ui` class is responsible for handling interactions with the user. It takes input from the user and outputs information
 * in a formatted and user-friendly way. It includes methods for greeting, displaying task lists, adding or removing tasks,
 * and handling errors.
 */
public class Ui {
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The line of input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a line to format the chat better
     */
    public void printLine() {
        String lines = "___________________________________________";
        System.out.println(lines);
    }

    /**
     * Prints a greeting when the bot first comes online.
     *
     * @return A greeting message.
     */
    public String greet() {
        printLine();
        System.out.println(" Hello! I'm Erel.\n What can I do for you?");
        printLine();
        System.out.println();
        return " Hello! I'm Erel.\n What can I do for you?";
    }

    /**
     * Prints a farewell message when the bot shuts down.
     *
     * @return A farewell message.
     */
    public String exit() {
        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
        return " Bye. Hope to see you again soon!";
    }

    /**
     * Prints the list of tasks currently stored in the task list.
     *
     * @param tasks The list of tasks to be displayed.
     * @return A string representation of the task list.
     */
    public String printList(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "Your task list is empty!";
        }

        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list:\n");

        printLine();
        System.out.println(" Here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++){
            System.out.println(" " + (i+1) + "." + tasks.getTask(i).toString());
            output.append(i + 1).append(". ").append(tasks.getTask(i).toString()).append("\n");
        }
        printLine();
        System.out.println();

        return output.toString();
    }

    /**
     * Prints a message confirming that a task has been added to the list.
     *
     * @param task  The task that was added.
     * @param tasks The task list after the task has been added.
     * @return A string confirming the task addition.
     */
    public String printInsert(Task task, TaskList tasks) {
        printLine();
        System.out.println(" Got it. I've added this task:\n" + "    " + task);
        System.out.println( " Now you have " + tasks.size() + " tasks in the list.");
        printLine();
        System.out.println();

        return " Got it. I've added this task:\n" + "    " + task +
                " Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Prints a message confirming that a task has been removed from the list.
     *
     * @param task  The task that was removed.
     * @param tasks The task list after the task has been removed.
     * @return A string confirming the task removal.
     */
    public String printDelete(Task task, TaskList tasks) {
        printLine();
        System.out.println(" Noted. I've removed this task:\n" + "    " + task.toString());
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        printLine();
        System.out.println();

        return " Noted. I've removed this task:\n" + "    " + task.toString() +
                "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Prints a message confirming that a task has been marked as done.
     *
     * @param tasks The task list containing the marked task.
     * @param index The index of the task that was marked.
     * @return A string confirming the task has been marked as done.
     */
    public String printMark(TaskList tasks, int index) {
        printLine();
        System.out.println(" Nice! I've marked this task as done:\n" + "    " + tasks.getTask(index).toString());
        printLine();
        System.out.println();

        return " Nice! I've marked this task as done:\n" + "    " + tasks.getTask(index).toString();
    }

    /**
     * Prints a message confirming that a task has been marked as not done yet.
     *
     * @param tasks The task list containing the unmarked task.
     * @param index The index of the task that was unmarked.
     * @return A string confirming the task has been marked as not done.
     */
    public String printUnmark(TaskList tasks, int index) {
        printLine();
        System.out.println(" Ok, I've marked this task as not done yet:\n" + "    " + tasks.getTask(index).toString());
        printLine();
        System.out.println();

        return " Ok, I've marked this task as not done yet:\n" + "    " + tasks.getTask(index).toString();
    }

    /**
     * Prints an error message when there is an issue loading tasks from a file.
     *
     * @return A string representing the loading error message.
     */
    public String printLoadingError() {
        System.out.println("Error loading tasks from file");
        return "Error loading tasks from file";
    }

    /**
     * Prints an error message when trying to mark an already marked task.
     *
     * @param tasks     The task list containing the task.
     * @param taskNumber The index of the task to mark.
     * @return A string representing the mark error message.
     */
    public String printMarkError(TaskList tasks, int taskNumber) {
        printLine();
        System.out.println(" Task is already marked:\n" + "    " + tasks.getTask(taskNumber).toString());
        printLine();
        System.out.println();

        return " Task is already marked:\n" + "    " + tasks.getTask(taskNumber).toString();
    }

    /**
     * Prints an error message when trying to unmark an already unmarked task.
     *
     * @param tasks     The task list containing the task.
     * @param taskNumber The index of the task to unmark.
     * @return A string representing the unmark error message.
     */
    public String printUnMarkError(TaskList tasks, int taskNumber) {
        printLine();
        System.out.println(" Task is already unmarked:\n" + "    " + tasks.getTask(taskNumber).toString());
        printLine();
        System.out.println();
        return " Task is already unmarked:\n" + "    " + tasks.getTask(taskNumber).toString();
    }

    /**
     * Prints a general error message.
     *
     * @param message The error message to be displayed.
     * @return A string representing the error message.
     */
    public String printErelError(String message) {
        printLine();
        System.out.println(" OOPS!!! " + message);
        printLine();
        return " OOPS!!! " + message;
    }

    /**
     * Prints a generic exception error message.
     *
     * @param message The exception message to be displayed.
     * @return A string representing the exception error message.
     */
    public String printExceptionError(String message) {
        printLine();
        System.out.println(" An error occurred: " + message);
        printLine();
        return " An error occurred: " + message;
    }

    /**
     * Displays a list of tasks that match a given search keyword.
     *
     * @param tasks The list of tasks that match the search keyword.
     * @return A string representing the list of matching tasks.
     */
    public String printMatchingTasks(List<Task> tasks) {
        printLine();
        if(tasks.isEmpty()) {
            System.out.println(" No tasks found.");
            return "No tasks found.";
        }
        StringBuilder output = new StringBuilder();
        output.append("Here are the matching tasks in your list:\n");

        System.out.println(" Here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++){
            System.out.println(" " + (i+1) + "." + tasks.get(i).toString());
            output.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }

        printLine();

        return output.toString();
    }
}
