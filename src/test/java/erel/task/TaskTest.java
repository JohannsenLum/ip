package erel.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testToString() {
        Task task = new Task("Read book");
        assertEquals("Read book", task.getName());
    }
}
