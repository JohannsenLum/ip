package erel.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import erel.task.Task;
import erel.task.TaskList;

/**
 * The `Storage` class handles reading from and writing to a file to persist task data. It ensures that tasks are saved
 * to a file and can be loaded back into the application.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the llist of tasks to the file
     *
     * @param tasks The list of tasks to save
     */
    public void saveTasksToFile(TaskList tasks) {
        File folder = new File("./data");
        if (!folder.exists()) {
            folder.mkdir();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks.getAllTasks()) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file" + e.getMessage());
        }
    }

    /**
     * Loads the list of tasks from the file. If the file does not exist, returns an empty list.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an error occurs while reading the file.
     */
    public List<Task> loadTasksFromFile() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromFileFormat(line);

                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new IOException(e);
        }
        return tasks;
    }
}
