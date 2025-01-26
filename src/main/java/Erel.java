import java.util.ArrayList;
import java.util.Scanner;

public class Erel {
    private static final ArrayList<Task> arrList = new ArrayList<>();
    private static final Storage storage = new Storage("./data/erel.txt");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //System.out.println(storage.loadTasksFromFile());
        arrList.addAll(storage.loadTasksFromFile());

        greet(); // Prints greeting

        while(true) {
            try {
                String input = sc.nextLine();
                String action = input.split(" ")[0];
                String[] inputArr = input.split(" /");

                Command command = Command.fromString(action); // Parse action into enum Commands

                switch (command) {
                case BYE: {
                    storage.saveTasksToFile(arrList);
                    exit();
                    return;
                }
                case LIST: {
                    printList();
                    break;
                }
                case MARK: {
                    checkValidMarkUnmark(input);
                    updateMark(input);
                    storage.saveTasksToFile(arrList);
                    break;
                }
                case UNMARK: {
                    checkValidMarkUnmark(input);
                    updateUnmark(input);
                    storage.saveTasksToFile(arrList);
                    break;
                }
                case DELETE: {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]);
                    checkValidDelete(taskNumber);
                    deleteTask(taskNumber-1);
                    storage.saveTasksToFile(arrList);
                    break;
                }
                case TODO: {
                    checkValidDescription(input.split(" ",2));
                    insertTodo(inputArr[0].substring(5));
                    storage.saveTasksToFile(arrList);
                    break;
                }
                case DEADLINE: {
                    checkValidDescription(input.split(" ",2));
                    insertDeadline(inputArr);
                    storage.saveTasksToFile(arrList);
                    break;
                }
                case EVENT: {
                    checkValidDescription(input.split(" ",2));
                    insertEvent(inputArr);
                    storage.saveTasksToFile(arrList);
                    break;
                }
                default: throw new ErelException(" Something went wrong. Please try again.");
                }
            } catch (ErelException e) {
                printLine();
                System.out.println(" OOPS!!! " + e.getMessage());
                printLine();
            } catch (Exception e) {
                printLine();
                System.out.println(" An error occurred: " + e.getMessage());
                printLine();
            }
        }
    }


    /**
     * Prints a line to format the chat better
     */
    private static void printLine() {
        String lines = "___________________________________________";
        System.out.println(lines);
    }

    /**
     * Prints a customised greeting when the bot first comes online
     */
    private static void greet() {
        printLine();
        System.out.println(" Hello! I'm Erel.\n What can I do for you?");
        printLine();
        System.out.println();
    }

    /**
     * Prints a customised exit when the bot shuts down
     */
    private static void exit() {
        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Prints the list of task that is in the Task list.
     */
    private static void printList() {
        printLine();
        System.out.println(" Here are the tasks in your list:");
        int counter = 1;
        for (Task s : arrList) {
            System.out.println(" " + counter++ + "." + s.toString());
        }
        printLine();
        System.out.println();
    }

    /**
     * Inserts an Event task into the task list.
     *
     * @param inputArr Array of strings containing task details, including description,
     *                 '/from' time, and '/to' time.
     * @throws ErelException if the input is invalid or missing necessary components.
     */
    private static void insertEvent(String[] inputArr) throws ErelException {
        if (inputArr.length < 3 || inputArr[1].length() <= 5 || inputArr[2].length() <= 3) {
            throw new ErelException(" An event must include '/from' and '/to'.");
        }
        arrList.add(new Event(inputArr[0].substring(6), inputArr[1].substring(5), inputArr[2].substring(3)));
        printInsert();
    }

    /**
     * Inserts a Deadline task into the task list.
     *
     * @param inputArr Array of strings containing task details, including description,
     *                 '/by'.
     * @throws ErelException if the input is invalid or missing necessary components.
     */
    private static void insertDeadline(String[] inputArr) throws ErelException {
        if (inputArr.length < 2 || inputArr[1].length() <= 3) {
            throw new ErelException(" A deadline must include '/by' followed by a time.");
        }
        arrList.add(new Deadline(inputArr[0].substring(9), inputArr[1].substring(3)));
        printInsert();
    }

    /**
     * Inserts a Todo task into the task list.
     *
     * @param substring A strings containing task details.
     */
    private static void insertTodo(String substring) {
        arrList.add(new Todo(substring));
        printInsert();
    }
    /**
     * Prints the successfully inserted item into the TaskList with
     * the necessary indentations and lines
     */
    private static void printInsert() {
        printLine();
        System.out.println(" Got it. I've added this task:\n" + "    " + arrList.get(arrList.size() - 1));
        System.out.println( " Now you have " + arrList.size() + " tasks in the list.");
        printLine();
        System.out.println();
    }

    /**
     * Deletes a task from the task list based on the given task number.
     * Prints a confirmation and the updated number of task in the Task list
     * after deletion.
     * @param taskNumber The index of the task to be removed from the task list (0-based index).
     */
    private static void deleteTask(int taskNumber) {
        Task t = arrList.get(taskNumber);
        arrList.remove(taskNumber);
        printLine();
        System.out.println(" Noted. I've removed this task:\n" + "    " + t.toString());
        System.out.println(" Now you have " + arrList.size() + " tasks in the list.");
        printLine();
        System.out.println();
    }

    /**
     * Checks whether a task can be deleted from the task list.
     * Throws an exception if the task list is empty or if the task number is invalid.
     *
     * @param taskNumber The index of the task to be validated for deletion (1-based index).
     * @throws EmptyListException If the task list is empty.
     * @throws IndexOutOfBoundsListException If the task number is less than 1 or greater than the size of the task list.
     */
    private static void checkValidDelete(int taskNumber) throws IndexOutOfBoundsListException, EmptyListException {
        if(arrList.isEmpty()) {
            throw new EmptyListException();
        }
        if(taskNumber <= 0 || taskNumber > arrList.size()) {
            throw new IndexOutOfBoundsListException(Integer.toString(taskNumber));
        }
    }

    /**
     * Checks the description of a task to ensure it is not empty.
     * Throws an exception if the description is missing or only contains whitespace.
     *
     * @param inputArr An array of strings where the first element is the command and
     *                 the second element is the task description.
     * @throws InvalidDescriptionException If the description is missing or blank.
     */
    private static void checkValidDescription(String[] inputArr) throws InvalidDescriptionException {
        if (inputArr.length <= 1 || inputArr[1].trim().isEmpty()) {
            throw new InvalidDescriptionException(inputArr[0]);
        }
    }

    /**
     * Checks whether a task can be marked or unmarked.
     * Ensures the task list is not empty, the input contains a valid task number,
     * and the task number is within the bounds of the task list.
     *
     * @param input The user input string containing the mark/unmark command and the task number.
     * @throws EmptyListException If the task list is empty.
     * @throws InvalidDescriptionException If the task number is missing or blank in the input.
     * @throws IndexOutOfBoundsListException If the task number is less than 1 or greater than the size of the task list.
     */
    private static void checkValidMarkUnmark(String input) throws InvalidDescriptionException, IndexOutOfBoundsListException, EmptyListException {
        String[] inputArr = input.split(" ");
        if(arrList.isEmpty()) {
            throw new EmptyListException();
        }
        if(inputArr.length < 2 || inputArr[1].trim().isEmpty()) {
            throw new InvalidDescriptionException(inputArr[0]);
        }
        int taskNumber = Integer.parseInt(inputArr[1]);
        if(taskNumber <= 0 || taskNumber > arrList.size()) {
            throw new IndexOutOfBoundsListException(inputArr[1]);
        }
    }

    /**
     * Marks a task from the task list based on the given task number and
     * if the task is unmarked.
     * Prints a confirmation and the updated marked task
     * Prints an error confirmation if the task is already marked
     * @param input The String index of the task to be marked in the task list.
     */
    private static void updateMark(String input) {
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1; //(0-based index)
        if(arrList.get(taskNumber).isDone()){
            printLine();
            System.out.println(" Task is already marked:\n" + "    " + arrList.get(taskNumber).toString());
            printLine();
            System.out.println();
            return;
        }
        arrList.get(taskNumber).setDone(true);
        printLine();
        System.out.println(" Nice! I've marked this task as done:\n" + "    " + arrList.get(taskNumber).toString());
        printLine();
        System.out.println();
    }

    /**
     * Unmarks a task from the task list based on the given task number and
     * if the task is marked.
     * Prints a confirmation and the updated unmarked task
     * Prints an error confirmation if the task is already unmarked
     * @param input The String index of the task to be marked in the task list.
     */
    private static void updateUnmark(String input) {
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        if(!arrList.get(taskNumber).isDone()){
            printLine();
            System.out.println(" Task is already unmarked:\n" + "    " + arrList.get(taskNumber).toString());
            printLine();
            System.out.println();
            return;
        }
        arrList.get(taskNumber).setDone(false);
        printLine();
        System.out.println(" Ok, I've marked this task as not done yet:\n" + "    " + arrList.get(taskNumber).toString());
        printLine();
        System.out.println();
    }


}

