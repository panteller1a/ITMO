package commands;
import managers.CommandManager;
import util.*;

public class Help extends Command{

    private final Console console;
    private final CommandManager Managercollection;

    public Help(Console console, CommandManager commandManager) {
        super("help", "вывести справку по доступным командам");
        this.console = console;
        this.Managercollection = commandManager;
    }

    @Override
    public boolean apply(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            console.println("Использование: '" + getName() + "'");
            return false;
        }

        for (Command command : Managercollection.getCommands().values()) {
            console.printTable(command.getName(), command.getDescription());
        }
        return true;
    }

}
