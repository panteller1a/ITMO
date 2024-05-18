package commands;

import managers.*;
import util.*;
import exceptions.*;
import models.SpaceMarine;
import java.util.Iterator;

public class RemoveGreater extends Command implements Executable {

    private final Console console;
    private final ManagerCollections collectionManager;

    public RemoveGreater(Console console, ManagerCollections collectionManager) {
        super("remove_greater <health>", "удалить из коллекции все элементы, чьё здоровье превышает заданное");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean apply(String[] arguments) {
        if (arguments.length < 2 || arguments[1].isEmpty()) {
            console.println("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
            return false;
        }
        int health;
        try {
            health = Integer.parseInt(arguments[1].trim());
        } catch (NumberFormatException e) {
            console.println("Значение здоровья не распознано.");
            return false;
        }

        int initialSize = collectionManager.getCollection().size();
        Iterator<SpaceMarine> iterator = collectionManager.getCollection().iterator();
        while (iterator.hasNext()) {
            SpaceMarine spaceMarine = iterator.next();
            if (spaceMarine.getHealth() > health) {
                iterator.remove();
            }
        }
        int finalSize = collectionManager.getCollection().size();
        int removedElements = initialSize - finalSize;

        console.println("Удалено элементов: " + removedElements);

        return removedElements > 0;
    }
}
