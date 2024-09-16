package org.lab6.commands.commands;

import common.console.Console;
import common.models.SpaceMarine;
import common.transfer.Response;
import common.utils.ArgumentType;
import common.utils.Command;
import org.lab6.collection.CollectionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AverageOfHealth extends Command {

    private static final long serialVersionUID = -3189197453506352448L;


    private transient final Console console;
    private transient final CollectionManager collectionManager;

    public AverageOfHealth(Console console, CollectionManager collectionManager) {
        super("average_of_health", "вычислить среднее значение здоровья всех бойцов в коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public Response apply(Map<ArgumentType, Object> args) {
        double averageHealth = collectionManager.getCollection().stream()
                .mapToDouble(SpaceMarine::getHealth)
                .average()
                .orElse(0.0);

        return new Response(Response.ResponseType.DEFAULT, true, "Среднее значение здоровья всех бойцов в коллекции: " + averageHealth);
    }

    public ArrayList<ArgumentType> getArgumentType() {
        return new ArrayList<>();
    }
}


