import java.util.ArrayList;
import java.util.Scanner;

public class Erel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String lines = "________________________________";
        String spaces = "    ";
        int counter = 1;
        ArrayList<String> arrList = new ArrayList<>();

        System.out.println(spaces+ lines + "\n " + spaces + "Hello! I'm Erel\n " + spaces + "What can I do for you?\n" + spaces + lines + "\n");


        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {break;}
            if (input.equals("list")) {
                System.out.println(spaces + lines);
                for (String s : arrList) {
                    System.out.println(spaces + " " + counter++ + ". " + s);
                }
                System.out.println(spaces + lines);
            }else {
                arrList.add(input);
                System.out.println(spaces + lines + "\n " + spaces + "added: " + input + "\n" + spaces + lines + "\n");
            }

        }

        System.out.println(spaces + lines + "\n " + spaces + "Bye. Hope to see you again soon!\n" + spaces + lines);
    }
}

