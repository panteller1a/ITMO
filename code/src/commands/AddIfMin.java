package commands;

import managers.ManagerCollections;
import util.*;
import exceptions.*;
import models.*;
import modelsform.*;


public class AddIfMin extends Command{

    private final Console console;
    private final ManagerCollections collectionManager;

    public AddIfMin(Console console, ManagerCollections collectionManager) {
        super("add_if_min {element}", "добавить новый элемент в коллекцию, если его здоровье меньше минимальной здоровья этой коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean apply(String[] arguments) {
        try {
            if (!arguments[1].isEmpty()) throw new WrongAmountOfElements();
            console.println("* Создание нового Космического Десанта (add_if_min):");
            var spaceMarine = (new SpaceMarineForm(console, collectionManager)).build();

            var minHealth = minHealth();
            if (spaceMarine.getHealth() < minHealth) {
                collectionManager.addToCollection(spaceMarine);
                console.println("Космический Десант успешно добавлен!");
            } else {
                console.println("Космический Десант не добавлен, здоровье не минимальное (" + spaceMarine.getHealth() + " > " + minHealth +")");
            }
            return true;

        } catch (WrongAmountOfElements exception) {
            console.printError("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
        } catch (InvalidForm exception) {
            console.printError("Поля бойца не валидны! Боец не создан!");
        } catch (IncorrectInput ignored) {}
        return false;
    }

    private Double minHealth() {
        double minHealth = Double.MAX_VALUE;
        for (SpaceMarine spaceMarine : collectionManager.getCollection()) {
            double health = spaceMarine.getHealth();
            if (health < minHealth) {
                minHealth = health;
            }
        }
        return minHealth;
    }


}
