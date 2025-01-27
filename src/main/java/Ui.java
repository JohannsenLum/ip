import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
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
     */
    public void greet() {
        printLine();
        System.out.println(" Hello! I'm Erel.\n What can I do for you?");
        printLine();
        System.out.println();
    }

    /**
     * Prints a customised exit when the bot shuts down
     */
    public void exit() {
        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
    }


    public void printList(TaskList tasks) {
        printLine();
        System.out.println(" Here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++){
            System.out.println((i+1) + "." + tasks.getTask(i).toString());
        }
        printLine();
        System.out.println();
    }

    public void printInsert(Task task, TaskList tasks) {
        printLine();
        System.out.println(" Got it. I've added this task:\n" + "    " + task);
        System.out.println( " Now you have " + tasks.size() + " tasks in the list.");
        printLine();
        System.out.println();
    }

    public void printDelete(Task task, TaskList tasks) {
        printLine();
        System.out.println(" Noted. I've removed this task:\n" + "    " + task.toString());
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        printLine();
        System.out.println();
    }

    public void printMark(TaskList tasks, int index) {
        printLine();
        System.out.println(" Nice! I've marked this task as done:\n" + "    " + tasks.getTask(index).toString());
        printLine();
        System.out.println();
    }

    public void printUnmark(TaskList tasks, int index) {
        printLine();
        System.out.println(" Ok, I've marked this task as not done yet:\n" + "    " + tasks.getTask(index).toString());
        printLine();
        System.out.println();
    }

    public void printLoadingError() {
        System.out.println("Error loading tasks from file");
    }

    public void printMarkError(TaskList tasks, int taskNumber) {
        printLine();
        System.out.println(" Task is already marked:\n" + "    " + tasks.getTask(taskNumber).toString());
        printLine();
        System.out.println();
    }

    public void printUnMarkError(TaskList tasks, int taskNumber) {
        printLine();
        System.out.println(" Task is already unmarked:\n" + "    " + tasks.getTask(taskNumber).toString());
        printLine();
        System.out.println();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printErelError(String message) {
        printLine();
        System.out.println(" OOPS!!! " + message);
        printLine();
    }

    public void printExceptionError(String message) {
        printLine();
        System.out.println(" An error occurred: " + message);
        printLine();
    }
}
