import java.util.ArrayList;
import java.util.Scanner;

public class Erel {
    private static final ArrayList<Task> arrList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        greet(); // Prints greeting

        while(true) {
            try {
                String input = sc.nextLine();
                String action = input.split(" ")[0];
                String[] inputArr = input.split(" /");

                Command command = Command.fromString(action); // Parse action into enum Commands

                switch (command) {
                    case BYE: {
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
                        break;
                    }
                    case UNMARK: {
                        checkValidMarkUnmark(input);
                        updateUnmark(input);
                        break;
                    }
                    case DELETE: {
                        int taskNumber = Integer.parseInt(input.split(" ")[1]);
                        checkValidDelete(taskNumber);
                        deleteTask(taskNumber-1);
                        break;
                    }
                    case TODO: {
                        checkValidDescription(input.split(" ",2));
                        insertTodo(inputArr[0].substring(5));
                        break;
                    }
                    case DEADLINE: {
                        checkValidDescription(input.split(" ",2));
                        insertDeadline(inputArr);
                        break;
                    }
                    case EVENT: {
                        checkValidDescription(input.split(" ",2));
                        insertEvent(inputArr);
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

    private static void insertEvent(String[] inputArr) throws ErelException {
        if (inputArr.length < 3 || inputArr[1].length() <= 5 || inputArr[2].length() <= 3) {
            throw new ErelException(" An event must include '/from' and '/to'.");
        }
        arrList.add(new Event(inputArr[0].substring(6), inputArr[1].substring(5), inputArr[2].substring(3)));
        printInsert();
    }

    private static void insertDeadline(String[] inputArr) throws ErelException {
        if (inputArr.length < 2 || inputArr[1].length() <= 3) {
            throw new ErelException(" A deadline must include '/by' followed by a time.");
        }
        arrList.add(new Deadline(inputArr[0].substring(9), inputArr[1].substring(3)));
        printInsert();
    }

    private static void insertTodo(String substring) {
        arrList.add(new Todo(substring));
        printInsert();
    }

    private static void printInsert() {
        printLine();
        System.out.println(" Got it. I've added this task:\n" + "    " + arrList.get(arrList.size() - 1));
        System.out.println( " Now you have " + arrList.size() + " tasks in the list.");
        printLine();
        System.out.println();
    }

    private static void deleteTask(int taskNumber) {
        Task t = arrList.get(taskNumber);
        arrList.remove(taskNumber);
        printLine();
        System.out.println(" Noted. I've removed this task:\n" + "    " + t.toString());
        System.out.println(" Now you have " + arrList.size() + " tasks in the list.");
        printLine();
        System.out.println();
    }

    private static void checkValidDelete(int taskNumber) throws IndexOutOfBoundsListException, EmptyListException {
        if(arrList.isEmpty()) {
            throw new EmptyListException();
        }
        if(taskNumber <= 0 || taskNumber > arrList.size()) {
            throw new IndexOutOfBoundsListException(Integer.toString(taskNumber));
        }
    }

    private static void checkValidDescription(String[] inputArr) throws InvalidDescriptionException {
        if (inputArr.length <= 1 || inputArr[1].trim().isEmpty()) {
            throw new InvalidDescriptionException(inputArr[0]);
        }
    }
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

    private static void updateMark(String input) {
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
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

    private static void printLine() {
        String lines = "___________________________________________";
        System.out.println(lines);
    }

    private static void greet() {
        printLine();
        System.out.println(" Hello! I'm Erel.\n What can I do for you?");
        printLine();
        System.out.println();
    }

    private static void exit() {
        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
    }

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

}

