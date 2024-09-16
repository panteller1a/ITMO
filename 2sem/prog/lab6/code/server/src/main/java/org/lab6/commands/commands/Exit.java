package org.lab6.commands.commands;


import common.transfer.Response;
import common.utils.ArgumentType;
import common.utils.Command;
import org.lab6.commands.CommandManager;
import java.util.ArrayList;
import java.util.Map;

public class Exit extends Command  {

    public Exit() {
        super("exit", "завершить программу на клиенте");
    }
    @Override
    public ArrayList<ArgumentType> getArgumentType() {return new ArrayList<>();}

    @Override
    public Response apply(Map<ArgumentType, Object> args) {
        return new Response(Response.ResponseType.DEFAULT,true, "Ждем вас снова");
    }
}
