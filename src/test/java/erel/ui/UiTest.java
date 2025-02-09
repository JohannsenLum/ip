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

        // Trim both expected and actual to ignore trailing newlines or spaces
        assertEquals("___________________________________________", outContent.toString().trim());
    }

    @Test
    public void testGreet() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ui.greet();

        // Adjust expected output and trim both expected and actual to ignore extra newlines
        String expectedOutput = """
        ___________________________________________
         Hello! I'm Erel.
         What can I do for you?
        ___________________________________________""";

        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }
}
