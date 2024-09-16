package org.lab6.commands.commands;

import common.console.Console;
import common.models.SpaceMarine;
import common.transfer.Response;
import common.utils.ArgumentType;
import common.utils.Command;
import org.lab6.collection.CollectionManager;

import java.util.*;

public class RemoveGreater extends Command {

    private static final long serialVersionUID = 8246554633654697392L;

    private transient final Console console;
    private transient final CollectionManager collectionManager;

    public RemoveGreater(Console console, CollectionManager collectionManager) {
        super("remove_greater {element}", "удалить все элементы из коллекции, которые больше данного");
        this.console = console;
        this.collectionManager = collectionManager;
    }


    @Override
    public Response apply(Map<ArgumentType, Object> args) {
        SpaceMarine spaceMarine = (SpaceMarine) args.get(ArgumentType.SPACEMARINE);
        var res = collectionManager.removeGreater(spaceMarine);
        if (res)
            return new Response(Response.ResponseType.DEFAULT,res, "Организации успешно удалены.");
        else
            return new Response(Response.ResponseType.DEFAULT,res, "Что-то пошло не так");
    }
    public ArrayList<ArgumentType> getArgumentType() {
        return new ArrayList<>(List.of(ArgumentType.SPACEMARINE));
    }
}
