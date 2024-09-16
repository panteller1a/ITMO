package org.lab6.commands.commands;

import common.transfer.Response;
import common.utils.ArgumentType;
import common.utils.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RemoveGreater extends Command {
    private static final long serialVersionUID = 8246554633654697392L;

    public RemoveGreater() {
        super("remove_greater <HEALTH>", "Remove an item exceeding the specified");
    }

    @Override
    public ArrayList<ArgumentType> getArgumentType() {return new ArrayList<>(List.of(ArgumentType.SPACEMARINE));}

    @Override
    public Response apply(Map<ArgumentType, Object> args){return null;}

}