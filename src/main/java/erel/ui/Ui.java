package erel.ui;

import erel.task.Task;
import erel.task.TaskList;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

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
     * Prints a customised greeting when the bot first comes online
     *
     * @return
     */
    public String greet() {
        printLine();
        System.out.println(" Hello! I'm Erel.\n What can I do for you?");
        printLine();
        System.out.println();
        return " Hello! I'm Erel.\n What can I do for you?";
    }

    /**
     * Prints a customised exit when the bot shuts down
     */
    public String exit() {
        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
        return " Bye. Hope to see you again soon!";
    }


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

    public String printInsert(Task task, TaskList tasks) {
        printLine();
        System.out.println(" Got it. I've added this task:\n" + "    " + task);
        System.out.println( " Now you have " + tasks.size() + " tasks in the list.");
        printLine();
        System.out.println();

        return " Got it. I've added this task:\n" + "    " + task +
                " Now you have " + tasks.size() + " tasks in the list.";
    }

    public String printDelete(Task task, TaskList tasks) {
        printLine();
        System.out.println(" Noted. I've removed this task:\n" + "    " + task.toString());
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        printLine();
        System.out.println();

        return " Noted. I've removed this task:\n" + "    " + task.toString() +
                "now" + " I've removed this task:\n" + "    " + task;
    }

    public String printMark(TaskList tasks, int index) {
        printLine();
        System.out.println(" Nice! I've marked this task as done:\n" + "    " + tasks.getTask(index).toString());
        printLine();
        System.out.println();

        return " Nice! I've marked this task as done:\n" + "    " + tasks.getTask(index).toString();
    }

    public String printUnmark(TaskList tasks, int index) {
        printLine();
        System.out.println(" Ok, I've marked this task as not done yet:\n" + "    " + tasks.getTask(index).toString());
        printLine();
        System.out.println();

        return " Ok, I've marked this task as not done yet:\n" + "    " + tasks.getTask(index).toString();
    }

    public String printLoadingError() {
        System.out.println("Error loading tasks from file");
        return "Error loading tasks from file";
    }

    public String printMarkError(TaskList tasks, int taskNumber) {
        printLine();
        System.out.println(" Task is already marked:\n" + "    " + tasks.getTask(taskNumber).toString());
        printLine();
        System.out.println();

        return " Task is already marked:\n" + "    " + tasks.getTask(taskNumber).toString();
    }

    public String printUnMarkError(TaskList tasks, int taskNumber) {
        printLine();
        System.out.println(" Task is already unmarked:\n" + "    " + tasks.getTask(taskNumber).toString());
        printLine();
        System.out.println();
        return " Task is already unmarked:\n" + "    " + tasks.getTask(taskNumber).toString();
    }

    public String printErelError(String message) {
        printLine();
        System.out.println(" OOPS!!! " + message);
        printLine();
        return " OOPS!!! " + message;
    }

    public String printExceptionError(String message) {
        printLine();
        System.out.println(" An error occurred: " + message);
        printLine();
        return " An error occurred: " + message;
    }

    /**
     * Displays the list of tasks that match the search keyword.
     *
     * @param tasks The list of tasks that match the keyword.
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
