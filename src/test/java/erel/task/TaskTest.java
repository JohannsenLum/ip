package erel.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testToString() {
        Task task = new Todo("Read book");
        assertEquals("Read book", task.getName());
    }
}
