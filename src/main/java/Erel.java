import java.util.Scanner;

public class Erel {
    public static void main(String[] args) {
        String lines = "________________________________";

        System.out.println(lines + "\nHello! I'm Erel\nWhat can I do for you?\n" + lines);

        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {break;}
            System.out.println(lines + "\n" + input + "\n" + lines);
        }

        System.out.println(lines + "\nBye. Hope to see you again soon!\n" + lines);
    }
}
