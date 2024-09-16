package org.lab6.commands.commands;



import common.transfer.Response;
import common.utils.ArgumentType;
import common.utils.Command;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddIfMin extends Command {

    private static final long serialVersionUID = -3197192756815042260L;

    public AddIfMin() {
        super("add_if_min <element>", "Add element smaller then smallest");
    }

    @Override
    public ArrayList<ArgumentType> getArgumentType() { return new ArrayList<>(List.of(ArgumentType.SPACEMARINE));}

    @Override
    public Response apply(Map<ArgumentType, Object> args){return null;}

}
