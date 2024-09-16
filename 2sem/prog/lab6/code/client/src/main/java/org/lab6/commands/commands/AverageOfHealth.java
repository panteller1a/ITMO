package org.lab6.commands.commands;



import common.transfer.Response;
import common.utils.ArgumentType;
import common.utils.Command;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AverageOfHealth extends Command {

    private static final long serialVersionUID = -3189197453506352448L;

    public AverageOfHealth() {
        super("average_of_health", "Print average health");
    }

    @Override
    public ArrayList<ArgumentType> getArgumentType() { return new ArrayList<>(List.of());}

    @Override
    public Response apply(Map<ArgumentType, Object> args){return null;}

}
