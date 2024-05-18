package managers;

import java.util.*;

import commands.Command;
/** управление коммандами */
public class CommandManager {

    private final Map<String, Command> commands = new HashMap<>();
    private final List<String> commandHistory = new ArrayList<>();

    /** добавление команды: ее название и тело */
    public void register(String commandName, Command command) {
        commands.put(commandName, command);
    }

    /** список команд */
    public Map<String, Command> getCommands() {
        return commands;
    }

    /** история команд */
    public List<String> getCommandHistory() {
        return commandHistory;
    }

    /** добавляет команду в историю */
    public void addToHistory(String command) {
        commandHistory.add(command);
    }
}
