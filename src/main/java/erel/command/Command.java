package erel.command;

public enum Command {
    BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT;

    public static Command fromString(String input) throws IllegalArgumentException {
        return Command.valueOf(input.toUpperCase());
    }
}