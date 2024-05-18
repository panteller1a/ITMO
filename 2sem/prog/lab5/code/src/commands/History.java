package commands;

import managers.CommandManager;
import util.Console;
import java.util.List;

public class History extends Command {

    private static final int HISTORY_LIMIT = 12;
    private final Console console;
    private final CommandManager commandManager;

    public History(Console console, CommandManager commandManager) {
        super("history", "вывести последние 12 команд");
        this.console = console;
        this.commandManager = commandManager;
    }

    @Override
    public boolean apply(String[] arguments) {
        List<String> history = commandManager.getCommandHistory();

        if (history.isEmpty()) {
            console.println("История команд пуста.");
        } else {
            console.println("Последние " + HISTORY_LIMIT + " команд:");
            int start = Math.max(0, history.size() - HISTORY_LIMIT);
            for (int i = start; i < history.size(); i++) {
                console.println(history.get(i));
            }
        }

        return true;
    }
}
