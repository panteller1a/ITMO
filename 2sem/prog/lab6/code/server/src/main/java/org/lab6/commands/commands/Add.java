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

public class Add extends Command {
    private transient final Console console;
    private transient final CollectionManager collectionManager;

    private static final long serialVersionUID = -8419048391436636351L;
    public Add(Console console, CollectionManager collectionManager) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public Response apply(Map<ArgumentType, Object> args) {
        try {
            SpaceMarine spaceMarines = (SpaceMarine) args.get(ArgumentType.SPACEMARINE);
            spaceMarines.setId(collectionManager.getFreeId());
            if (!spaceMarines.validate()) {
                return new Response(Response.ResponseType.DEFAULT,false, "Поля космического десанта не валидны! Космический десант не создан!");
            }
            boolean success = collectionManager.add(spaceMarines);
            if (success)
                return new Response(Response.ResponseType.DEFAULT,success, "Космический десант успешно добавлен!");
            else
                return new Response(Response.ResponseType.DEFAULT,success, "Полное имя не уникально!");
        } catch (Exception e) {
            console.printError(e.toString());
            return new Response(Response.ResponseType.DEFAULT,false, e.toString());
        }
    }

    public ArrayList<ArgumentType> getArgumentType() {
        return new ArrayList<>(List.of(ArgumentType.SPACEMARINE));
    }

}
