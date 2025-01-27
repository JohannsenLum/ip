import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy, HH:mm a");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "E | " + (isDone() ? "1" : "0") + " | " + super.getName() + " | " + from.format(formatter) + " | " + to.format(formatter);
    }

    public static Event fromFileFormat(String fileFormat) {
        String[] lines = fileFormat.split(" \\| ");
        boolean isDone = lines[1].equals("1");
        String description = lines[2];
        LocalDateTime from = LocalDateTime.parse(lines[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime to = LocalDateTime.parse(lines[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        Event event = new Event(description, from, to);
        if (isDone) {
            event.setDone(true);
        }
        return event;
    }
}
