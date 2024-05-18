package commands;

import managers.ManagerCollections;
import util.*;

public class Show extends Command{
    private final Console console;
    private final ManagerCollections collectionManager;

    public Show(Console console, ManagerCollections collectionManager) {
        super("show", "вывести все элементы коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean apply(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            console.println("Использование: '" + getName() + "'");
            return false;
        }

        console.println(collectionManager);
        return true;
    }
}
