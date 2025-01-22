import java.util.ArrayList;
import java.util.Scanner;

public class Erel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String lines = "___________________________________________";
        String spaces = "    ";

        ArrayList<Task> arrList = new ArrayList<>();

        System.out.println(spaces+ lines + "\n " + spaces + "Hello! I'm Erel\n " + spaces + "What can I do for you?\n" + spaces + lines + "\n");


        while(true) {
            String input = sc.nextLine();
            String action = input.split(" ")[0];

            if (action.equals("bye")) {break;}
            if (action.equals("list")) {
                System.out.println(spaces + lines);
                System.out.println(spaces + " Here are tahe tasks in your list:");
                int counter = 1;
                for (Task s : arrList) {
                    System.out.println(spaces + " " + counter++ + "." + s.toString());
                }
                System.out.println(spaces + lines + "\n");
            } else if (action.equals("mark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                arrList.get(taskNumber).setDone(true);
                System.out.println(spaces + lines + "\n " + spaces + "Nice! I've marked this task as done: ");
                System.out.println(spaces + spaces + arrList.get(taskNumber).toString());
                System.out.println(spaces + lines + "\n");

            } else if (action.equals("unmark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                arrList.get(taskNumber).setDone(false);
                System.out.println(spaces + lines + "\n " + spaces + "Ok, I've marked this task as not done yet: ");
                System.out.println(spaces + spaces + arrList.get(taskNumber).toString());
                System.out.println(spaces + lines + "\n");

            } else {
                // Adding the data from user into arrList of Tasks
                String[] inputArr = input.split(" /");
                if(action.equals("todo")) {
                    arrList.add(new Todo(inputArr[0].substring(5)));
                }
                if(action.equals("deadline")) {
                    arrList.add(new Deadline(inputArr[0].substring(9), inputArr[1].substring(3)));
                }
                if(action.equals("event")) {
                    arrList.add(new Event(inputArr[0].substring(6), inputArr[1].substring(5), inputArr[2].substring(3)));
                }

                System.out.println(spaces + lines + "\n " + spaces + "Got it. I've added this task:");
                System.out.println(spaces + spaces + arrList.get(arrList.size()-1));
                System.out.println(spaces + " Now you have " + arrList.size() + " tasks in the list." + "\n" + spaces + lines + "\n");
            }

        }

        System.out.println(spaces + lines + "\n " + spaces + "Bye. Hope to see you again soon!\n" + spaces + lines);
    }
}

