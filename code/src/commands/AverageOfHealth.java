package commands;

import managers.*;
import util.*;

public class AverageOfHealth extends Command{

    private final Console console;
    private final ManagerCollections collectionManager;


    public AverageOfHealth(Console console, ManagerCollections collectionManager) {
        super("average_of_health", "вычислить среднее значение здоровья всех бойцов в коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
    }


    @Override
    public boolean apply(String[] arguments) {
        try {

            double averageHealth = collectionManager.averageOfHealth();

            if (Double.isNaN(averageHealth)) {
                console.printError("Коллекция пуста, не удалось вычислить среднее значение здоровья.");
                return false;
            }

            console.println("Среднее значение здоровья Космических Десантов в коллекции: " + averageHealth);
            return true;

        } catch (Exception e) {
            console.printError("Произошла ошибка во время вычисления среднего значения здоровья");
            return false;
        }
    }
}
