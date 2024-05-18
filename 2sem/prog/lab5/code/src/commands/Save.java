package commands;
import util.*;
import managers.ManagerCollections;

public class Save extends Command{

    private final Console console;
    private final ManagerCollections collectionManager;

    public Save(Console console, ManagerCollections collectionManager) {
        super("save", "сохранить коллекцию в файл");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean apply(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            console.println("Использование: '" + getName() + "'");
            return false;
        }

        collectionManager.saveCollection();
        return true;
    }
}
