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

public class AddIfMin extends Command {
    private transient final Console console;
    private transient final CollectionManager collectionManager;

    private static final long serialVersionUID = -3197192756815042260L;


    public AddIfMin(Console console, CollectionManager collectionManager) {
        super("add_if_min <element>", "добавить новый элемент в коллекцию, если его здоровье меньше минимальной здоровья этой коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
    }


    @Override
    public Response apply(Map<ArgumentType, Object> args) {
        try {
            SpaceMarine spaceMarine = (SpaceMarine) args.get(ArgumentType.SPACEMARINE);
            double health = spaceMarine.getHealth();
            double minHealth = collectionManager.minHealth();


            if (health < minHealth) {
                collectionManager.add(spaceMarine);
            }

            return new Response(Response.ResponseType.DEFAULT,true, null, collectionManager.getCollectionDescending());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<ArgumentType> getArgumentType() {
        return new ArrayList<>(List.of(ArgumentType.HEALTH));
    }
}
