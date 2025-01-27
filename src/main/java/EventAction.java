import java.time.LocalDateTime;

public class EventAction implements Action {
    String description;
    LocalDateTime from;
    LocalDateTime to;

    public EventAction(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        Event event = new Event(description, from, to);
        tasks.addTask(event);
        ui.printInsert(event, tasks);
        storage.saveTasksToFile(tasks);
    }
}
