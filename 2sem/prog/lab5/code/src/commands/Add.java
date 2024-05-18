package commands;

import exceptions.*;
import managers.ManagerCollections;
import modelsform.SpaceMarineForm;
import util.Console;

public class Add extends Command{
    private final Console console;
    private final ManagerCollections Managercollection;

    public Add(Console console, ManagerCollections collectionManager) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.console = console;
        this.Managercollection = collectionManager;
    }

    @Override
    public boolean apply(String[] arguments) {
        try {
            if (!arguments[1].isEmpty()) throw new WrongAmountOfElements();
            console.println("* Создание нового бойца:");
            Managercollection.addToCollection((new SpaceMarineForm(console, Managercollection)).build());
            console.println("Боец успешно добавлен!");
            return true;

        } catch (WrongAmountOfElements exception) {
            console.printError("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
        } catch (InvalidForm exception) {
            console.printError("Поля бойца не валидны! Боец не создан!");
        } catch (IncorrectInput ignored) {}
        return false;
    }

}
