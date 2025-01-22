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
            if (input.equals("bye")) {break;}
            if (input.equals("list")) {
                System.out.println(spaces + lines);
                int counter = 1;
                for (Task s : arrList) {
                    System.out.println(spaces + " " + counter++ + ".[" + s.getStatusIcon() + "] " + s.getName());
                }
                System.out.println(spaces + lines);
            } else if (input.startsWith("mark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                arrList.get(taskNumber).setDone(true);
                System.out.println(spaces + lines + "\n " + spaces + "Nice! I've marked this task as done: ");
                System.out.println(spaces + spaces + "[X] " + arrList.get(taskNumber).getName());
                System.out.println(spaces + lines + "\n");

            } else if (input.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                arrList.get(taskNumber).setDone(false);
                System.out.println(spaces + lines + "\n " + spaces + "Ok, I've marked this task as not done yet: ");
                System.out.println(spaces + spaces + "[ ] " + arrList.get(taskNumber).getName());
                System.out.println(spaces + lines + "\n");

            } else {
                arrList.add(new Task(input));
                System.out.println(spaces + lines + "\n " + spaces + "added: " + input + "\n" + spaces + lines + "\n");
            }

        }

        System.out.println(spaces + lines + "\n " + spaces + "Bye. Hope to see you again soon!\n" + spaces + lines);
    }
}

