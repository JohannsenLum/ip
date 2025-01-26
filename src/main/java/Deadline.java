public class Deadline extends Task {
    protected String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone() ? "1" : "0") + " | " + super.getName() + " | " + by;
    }

    public static Deadline fromFileFormat(String fileFormat) {
        String[] lines = fileFormat.split(" \\| ");
        boolean isDone = lines[1].equals("1");
        String descrption = lines[2];
        String by = lines[3];

        Deadline deadline = new Deadline(descrption, by);
        if (isDone) {
            deadline.setDone(true);
        }
        return deadline;
    }
}
