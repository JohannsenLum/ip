package erel.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class UiTest {
    @Test
    public void testPrintLine() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ui.printLine();

        // Normalize newlines by replacing both \r\n and \n with just \n
        String expectedOutput = "___________________________________________\n";
        assertEquals(expectedOutput.replace("\r\n", "\n"), outContent.toString().replace("\r\n", "\n"));
    }

    @Test
    public void testGreet() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ui.greet();

        String expectedOutput = """
        ___________________________________________
         Hello! I'm Erel.
         What can I do for you?
        ___________________________________________\n\n""";

        // Normalize newlines by replacing both \r\n and \n with just \n
        assertEquals(expectedOutput.replace("\r\n", "\n"), outContent.toString().replace("\r\n", "\n"));
    }
}
