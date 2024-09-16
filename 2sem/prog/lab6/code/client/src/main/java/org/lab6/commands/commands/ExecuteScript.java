package org.lab6.commands.commands;



import common.console.Console;
import common.transfer.Response;
import common.utils.ArgumentType;
import common.utils.Command;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ExecuteScript extends Command {
    private final Console console;

    public ExecuteScript(Console console) {
        super("execute_script <file_name>", "Execute script from file");
        this.console = console;
    }

    @Override
    public Response apply(Map<ArgumentType, Object> args) {
        if (args.get(ArgumentType.SCRIPT_NAME) == null) {
            console.println("Использование: '" + getName() + "'");
            return new Response(Response.ResponseType.DEFAULT,false, "Некорректное использование...");
        }
        console.println("Выполнение скрипта '" + args.get(ArgumentType.SCRIPT_NAME) + "'...");
        return new Response(Response.ResponseType.DEFAULT,true, "Выполнение скрипта...");

    }

    public ArrayList<ArgumentType> getArgumentType() {
        return new ArrayList<>(List.of(ArgumentType.SCRIPT_NAME));
    };

}

