package org.lab6.commands;


import common.models.SpaceMarine;
import common.transfer.Request;
import common.transfer.Response;
import common.utils.Command;

import java.util.TreeSet;


public class Invoker {
    public final CommandManager manager;

    public Invoker(CommandManager manager) {
        this.manager = manager;
    }

    public CommandManager getManager(){return this.manager;}
    public Response handle(Request request) {
        var commandsList = manager.getCommandsList();

        var command = request.getCommand();
        if (request.getRequestType() != Request.RequestType.LOCAL) {
            for (Command commandFromList : commandsList) {
                if (commandFromList.getClass().isInstance(command)) {
                    command = commandFromList;
                    break;
                }
            }

            if (command == null)
                return new Response(Response.ResponseType.DEFAULT, true, "No such command: " + request.getCommand(), (TreeSet<SpaceMarine>) null);
            return command.apply(request.getArguments());
        }
        return new Response(Response.ResponseType.DEFAULT,true, "Была исполнена локальная команад");
    }
}