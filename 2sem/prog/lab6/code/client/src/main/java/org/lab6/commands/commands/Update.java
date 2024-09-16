package org.lab6.commands.commands;

import common.transfer.Response;
import common.utils.ArgumentType;
import common.utils.Command;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Update extends Command {
    private static final long serialVersionUID = -6208747329644320661L;

    public Update() {
        super("update <ID>", "Update element by ID");
    }

    @Override
    public ArrayList<ArgumentType> getArgumentType() {return new ArrayList<>(List.of(ArgumentType.ID, ArgumentType.HEALTH, ArgumentType.SPACEMARINE));}

    @Override
    public Response apply(Map<ArgumentType, Object> args){return null;}

}