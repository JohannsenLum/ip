import java.util.ArrayList;
import java.util.Scanner;

public class Erel {
    public enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT;

        public static Command fromString(String input) throws IllegalArgumentException {
            return Command.valueOf(input.toUpperCase());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String lines = "___________________________________________";
        String spaces = "    ";

        ArrayList<Task> arrList = new ArrayList<>();

        System.out.println(spaces+ lines + "\n " + spaces + "Hello! I'm Erel\n " + spaces + "What can I do for you?\n" + spaces + lines + "\n");



        while(true) {
            try {
                String input = sc.nextLine();
                String action = input.split(" ")[0];
                String[] inputArr = input.split(" /");

                // Parse action into enum Commands
                Command command = Command.fromString(action);

                switch (command) {
                    case BYE: {
                        System.out.println(spaces + lines + "\n " + spaces + "Bye. Hope to see you again soon!\n" + spaces + lines);
                        return;
                    }
                    case LIST: {
                        System.out.println(spaces + lines);
                        System.out.println(spaces + " Here are the tasks in your list:");
                        int counter = 1;
                        for (Task s : arrList) {
                            System.out.println(spaces + " " + counter++ + "." + s.toString());
                        }
                        System.out.println(spaces + lines + "\n");
                        break;
                    }
                    case MARK: {
                        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                        arrList.get(taskNumber).setDone(true);
                        System.out.println(spaces + lines + "\n " + spaces + "Nice! I've marked this task as done:");
                        System.out.println(spaces + spaces + arrList.get(taskNumber).toString());
                        System.out.println(spaces + lines + "\n");

                        break;
                    }
                    case UNMARK: {
                        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                        arrList.get(taskNumber).setDone(false);
                        System.out.println(spaces + lines + "\n " + spaces + "Ok, I've marked this task as not done yet:");
                        System.out.println(spaces + spaces + arrList.get(taskNumber).toString());
                        System.out.println(spaces + lines + "\n");

                        break;
                    }
                    case DELETE: {
                        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                        Task t = arrList.get(taskNumber);
                        arrList.remove(taskNumber);
                        System.out.println(spaces + lines + "\n " + spaces + "Noted. I've removed this task:");
                        System.out.println(spaces + spaces + t.toString());
                        System.out.println(spaces + " Now you have " + arrList.size() + " tasks in the list." + "\n" + spaces + lines + "\n");

                        break;
                    }
                    case TODO: {
                        if (input.length() <= 5) {
                            throw new ErelException("OOPS!!! The description of a todo cannot be empty.");
                        }

                        arrList.add(new Todo(inputArr[0].substring(5)));
                        System.out.println(spaces + lines + "\n " + spaces + "Got it. I've added this task:");
                        System.out.println(spaces + spaces + arrList.get(arrList.size() - 1));
                        System.out.println(spaces + " Now you have " + arrList.size() + " tasks in the list." + "\n" + spaces + lines + "\n");
                        break;
                    }
                    case DEADLINE: {
                        if (inputArr.length < 2 || inputArr[1].length() <= 3) {
                            throw new ErelException("OOPS!!! A deadline must include '/by' followed by a time.");
                        }

                        arrList.add(new Deadline(inputArr[0].substring(9), inputArr[1].substring(3)));
                        System.out.println(spaces + lines + "\n " + spaces + "Got it. I've added this task:");
                        System.out.println(spaces + spaces + arrList.get(arrList.size() - 1));
                        System.out.println(spaces + " Now you have " + arrList.size() + " tasks in the list." + "\n" + spaces + lines + "\n");
                        break;
                    }
                    case EVENT: {
                        if (inputArr.length < 3 || inputArr[1].length() <= 5 || inputArr[2].length() <= 3) {
                            throw new ErelException("OOPS!!! An event must include '/from' and '/to'.");
                        }

                        arrList.add(new Event(inputArr[0].substring(6), inputArr[1].substring(5), inputArr[2].substring(3)));
                        System.out.println(spaces + lines + "\n " + spaces + "Got it. I've added this task:");
                        System.out.println(spaces + spaces + arrList.get(arrList.size() - 1));
                        System.out.println(spaces + " Now you have " + arrList.size() + " tasks in the list." + "\n" + spaces + lines + "\n");
                        break;
                    }
                    default: throw new Exception(" OOPS!!! Something went wrong. Please try again.");
                    }

            } catch (ErelException e) {
                System.out.println(spaces + lines + "\n " + spaces + e.getMessage());
                System.out.println(spaces + lines + "\n");
            } catch (IllegalArgumentException e) {
                System.out.println(spaces + lines + "\n " + spaces + "Invalid command. Please try again.");
                System.out.println(spaces + lines + "\n");
            } catch (Exception e) {
                System.out.println(spaces + lines + "\n " + spaces + "An error occurred: " + e.getMessage());
                System.out.println(spaces + lines + "\n");
            }
        }

    }
}

