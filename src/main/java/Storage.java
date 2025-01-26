import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the llist of tasks to the file
     *
     * @param tasks The list of tasks to save
     */
    public void saveTasksToFile(List<Task> tasks) {
        File folder = new File("./data");
        if (!folder.exists()) {
            folder.mkdir();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file" + e.getMessage());
        }
    }

    public List<Task> loadTasksFromFile() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null){
                Task task = Task.fromFileFormat(line);

                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file" + e.getMessage());
        }

        return tasks;
    }
}
