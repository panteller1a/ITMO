package commands;
import managers.ManagerCollections;
import util.*;

public class Clear extends Command{

    private final Console console;
    private final ManagerCollections Managercollection;

    public Clear(Console console, ManagerCollections collectionManager) {
        super("clear", "очистить коллекцию");
        this.console = console;
        this.Managercollection = collectionManager;
    }

    @Override
    public boolean apply(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            console.println("Использование: '" + getName() + "'");
            return false;
        }

        Managercollection.clearCollection();
        console.println("Коллекция очищена!");
        return true;
    }
}
