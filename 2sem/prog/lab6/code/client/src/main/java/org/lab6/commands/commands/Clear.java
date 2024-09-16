package org.lab6.commands.commands;


import common.transfer.Response;
import common.utils.ArgumentType;
import common.utils.Command;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Clear extends Command {

    private static final long serialVersionUID = 2735156827423035L;
    public Clear() {
        super("clear", "Clear collection elements");
    }

    @Override
    public ArrayList<ArgumentType> getArgumentType() { return new ArrayList<>(List.of());}

    @Override
    public Response apply(Map<ArgumentType, Object> args){return null;}

}