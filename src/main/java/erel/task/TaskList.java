package erel.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks. Provides methods to add, remove, retrieve,
 * and search for tasks within the list.
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Finds tasks whose descriptions contain the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks containing the keyword.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Filters tasks to get only upcoming reminders of the given type (deadline/event).
     */
    public List<Task> getUpcomingReminders(String type) {
        List<Task> upcomingTasks = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (Task task : tasks) {
            if (task instanceof Deadline deadlineTask && type.equals("deadline")) {
                if (deadlineTask.getBy().isAfter(now) && !deadlineTask.isDone()) {
                    upcomingTasks.add(task);
                }
            } else if (task instanceof Event eventTask && type.equals("event")) {
                if (eventTask.getTo().isAfter(now) && !eventTask.isDone()) {
                    upcomingTasks.add(task);
                }
            }
        }

        return upcomingTasks;
    }

}
