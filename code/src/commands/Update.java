package commands;

import exceptions.*;
import managers.*;
import util.*;
import modelsform.*;

public class Update extends Command{

    private final Console console;
    private final ManagerCollections collectionManager;

    public Update(Console console, ManagerCollections collectionManager) {
        super("update <ID> {element}", "обновить значение элемента коллекции по ID");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean apply(String[] arguments) {
        try {

            if (arguments[1].isEmpty()) throw new WrongAmountOfElements();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmpty();

            var id = Integer.parseInt(arguments[1]);
            var product = collectionManager.getById(id);
            if (product == null) throw new NotFound();

            console.println("* Введите данные обновленного Десанта:");
            console.p2();

            var newProduct = (new SpaceMarineForm(console, collectionManager)).build();
            product.update(newProduct);

            console.println("Космический десант успешно обновлен.");
            return true;

        } catch (WrongAmountOfElements exception) {
            console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmpty exception) {
            console.printError("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            console.printError("ID должен быть представлен числом!");
        } catch (NotFound exception) {
            console.printError("Космического с таким ID в коллекции нет!");
        } catch (IncorrectInput e) {
            console.printError("Проверьте правильность ввода!");
        } catch (InvalidForm e) {
            console.printError("Поля Десанта не валидны! Космический десант не обновлен!");
        }
        return false;
    }

}
