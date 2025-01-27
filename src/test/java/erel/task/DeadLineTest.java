package erel.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadLineTest {
    @Test
    public void testToString() {
        LocalDateTime by = LocalDateTime.of(2023, 10, 31, 23, 59);
        Deadline deadline = new Deadline("Submit report", by);
        assertEquals("[D][ ] Submit report (by: Oct 31, 2023, 23:59 pm)", deadline.toString());
    }

    @Test
    public void testToFileFormat() {
        LocalDateTime by = LocalDateTime.of(2023, 10, 31, 23, 59);
        Deadline deadline = new Deadline("Submit report", by);
        deadline.setDone(true);
        assertEquals("D | 1 | Submit report | 2023-10-31 23:59", deadline.toFileFormat());
    }
}
