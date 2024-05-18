package commands;

import exceptions.*;
import managers.*;
import util.*;

public class RemoveById extends Command{

    private final Console console;
    private final ManagerCollections collectionManager;

    public RemoveById(Console console, ManagerCollections collectionManager) {
        super("remove_by_id <ID>", "удалить элемент из коллекции по ID");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /** выполнение команды remove_by_id с выводом успеха */
    @Override
    public boolean apply(String[] arguments) {
        try {
            if (arguments[1].isEmpty()) throw new WrongAmountOfElements();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmpty();

            var id = Integer.parseInt(arguments[1]);
            var productToRemove = collectionManager.getById(id);
            if (productToRemove == null) throw new NotFound();

            collectionManager.removeFromCollection(productToRemove);
            console.println("Космический десант успешно удален.");
            return true;

        } catch (WrongAmountOfElements exception) {
            console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmpty exception) {
            console.printError("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            console.printError("ID должен быть представлен числом!");
        } catch (NotFound exception) {
            console.printError("Космического десанта с таким ID в коллекции нет!");
        }
        return false;
    }

}
