package erel.command;

/**
 * Represents the different commands supported by the Erel bot. Each command corresponds to a specific action that the
 * user can perform, such as adding a task, marking a task as done, or listing all tasks.
 */
public enum Command {
    BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, FIND;

    /**
     * Converts a string input into the corresponding `Command` enum value.
     *
     * @param input The string input representing the command.
     * @return The corresponding `Command` enum value.
     * @throws IllegalArgumentException If the input does not match any valid command.
     */
    public static Command fromString(String input) throws IllegalArgumentException {
        return Command.valueOf(input.toUpperCase());
    }
}
