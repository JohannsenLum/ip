import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DeadlineAction implements Action {
    String description;
    LocalDateTime by;

    public DeadlineAction(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        ui.printInsert(deadline, tasks);
        storage.saveTasksToFile(tasks);
    }
}
