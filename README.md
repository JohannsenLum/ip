# Erel User Guide

![Ui](https://github.com/user-attachments/assets/b49c8f6d-7919-4bab-b1c4-427c2911ba54)

Erel is a Personal Assistant Chatbot that allows you to manage tasks and events efficiently. This user guide will walk you through the available features and commands.

## Table of Contents
- [Running the JAR File](#running-jar-file)
- [Features](#features)
- [Usage](#usage)
- [Command Summary](#command-summary)
- [Data Saving](#data-saving)
- [Acknowledgements](#acknowledgements)

## Running Jar File
To run the Erel chatbot JAR file, follow these steps:

### Prerequisites:
Ensure you have **Java 17** or later installed. You can download and install Java from [here](https://adoptopenjdk.net/).

### Running the JAR File:
1. Open a terminal or command prompt window.
2. Navigate to the directory where the JAR file is located. For example:
   ```bash
   cd /path/to/your/jar/file
   ```
3. Run the JAR file using the following command
   ```bash
   java -jar erel.jar
   ```

## Features
### **Adding Todo**: Add tasks that does not contain any date and time.
  - Format: `todo DESCRIPTION`
  - Example: `todo Buy groceries`

### **Adding Deadline**: Add tasks with a specific deadline.
  - Format: `deadline DESCRIPTION /by DATE`
  - Example: `deadline Finish homework /by 2025-02-20 12:00`

### **Adding Event**: Add tasks with start and end dates.
  - Format: `event DESCRIPTION /from DATE to /DATE`
  - Example: `event Meeting /from 2025-02-15 12:00 /to 2025-02-16 14:00`

### **List**: View the list of all tasks.
  - Format: `list`
  - Example: `list`

### **Mark**: Mark a task as completed.
  - Format: `mark INDEX`
  - Example: `mark 1`

### **Unmark**: Unmark a task as incomplete.
  - Format: `unmark INDEX`
  - Example: `unmark 1`

### **Delete**: Delete a task from the list specified by the index.
  - Format: `delete INDEX`
  - Example: `delete 1` deletes the 1st task in the list

### **Find**: Search for tasks by keyword.
  - Format: `find KEYWORD`
  - Example: `find interview` will show a list of tasks that matches the keyword 'interview'

![image](https://github.com/user-attachments/assets/da2dcf60-d9ec-4340-b283-18ba8b9741f0)

### **Remind**: Produce a list of deadline/events that have not been completed
  - Format: `remind DEADLINE/EVENT`
  - Example: `remind deadline` will show a list of deadlines that have not been completed

![image](https://github.com/user-attachments/assets/76c03cad-65df-42c2-8de6-4d8e6a82d29d)
![image](https://github.com/user-attachments/assets/343498af-0fed-4b74-9861-158a791f120b)

### **Exit**: Exit Erel Chatbot.
  - Format: `exit`
  - Example: `exit`

## Usage

To use the chatbot, simply type any of the commands listed in the **Features** section. For example:

- **Adding a task**: `todo Buy groceries`
- **Setting a deadline**: `deadline Finish homework /by 2025-02-20 12:00`
  
You can also view your list, mark tasks, or delete events.

### Important Notes:
- Ensure that dates are provided in the format: `YYYY-MM-DD HH:MM`.
- Commands are case-insensitive.

## Command Summary
Here is a quick reference of all available commands in this chatbot:

| Command   | Description                                 |
|-----------|---------------------------------------------|
| `todo`    | Add tasks                                   |
| `deadline`| Add tasks with a specific deadline          |
| `event`   | Add tasks with start and end date           |
| `list`    | View all tasks                              |
| `mark`    | Mark a task as completed                    |
| `unmark`  | Unmark a task                               |
| `delete`  | Delete a task                               |
| `find`    | Search tasks by keyword                     |
| `exit`    | Exit Erel Chatbot                           |


## Data Saving
The task list is automatically saved as a .txt file in the following location: `[JAR file location]/data/erel.txt.` Users with advanced knowledge can directly modify this file.

⚠️ Important: If the file's format is changed incorrectly, Erel bot may encounter errors when trying to parse the data. It is strongly advised to back up the file before making any edits. Only modify the file if you're confident in your ability to update it correctly.

## Acknowledgements
Some of the JavaDocs comments were created with the guidance and advice from ChatGPT and DeepSeek.
