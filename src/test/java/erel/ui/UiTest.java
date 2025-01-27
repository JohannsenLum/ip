package erel.ui;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void testPrintLine() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ui.printLine();

        assertEquals("___________________________________________\n", outContent.toString());
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
        ___________________________________________

        """;
        assertEquals(expectedOutput, outContent.toString());
    }
}
