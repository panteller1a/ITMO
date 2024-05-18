package commands;

import managers.ManagerCollections;
import util.*;
import models.*;

import java.util.TreeSet;

public class PrintDescending extends Command {

    private final Console console;
    private final ManagerCollections collectionManager;

    public PrintDescending(Console console, ManagerCollections collectionManager) {
        super("print_descending", "вывести все элементы коллекции в обратном порядке");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean apply(String[] arguments) {
        TreeSet<SpaceMarine> elements = collectionManager.getElements();
        if (elements.isEmpty()) {
            console.printError("В коллекции нет элементов.");
            return false;
        }

        console.println("Элементы коллекции в обратном порядке:");
        for (SpaceMarine element : elements.descendingSet()) {
            console.println(element.toString());
        }

        return true;
    }

}
