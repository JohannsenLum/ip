public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone() ? "1" : "0") + " | " + super.getName() + " | " + from + " | " + to;
    }

    public static Event fromFileFormat(String fileFormat) {
        String[] lines = fileFormat.split(" \\| ");
        boolean isDone = lines[1].equals("1");
        String description = lines[2];
        String from = lines[3];
        String to = lines[4];

        Event event = new Event(description, from, to);
        if (isDone) {
            event.setDone(true);
        }
        return event;
    }
}
