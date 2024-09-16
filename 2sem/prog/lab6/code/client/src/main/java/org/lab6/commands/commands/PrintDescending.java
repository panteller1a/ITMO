package org.lab6.commands.commands;

import common.transfer.Response;
import common.utils.ArgumentType;
import common.utils.Command;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrintDescending extends Command {

    private static final long serialVersionUID = 6148383715202775637L;

    public PrintDescending() {
        super("print_descending", "");
    }

    @Override
    public ArrayList<ArgumentType> getArgumentType() {return new ArrayList<>(List.of());}

    @Override
    public Response apply(Map<ArgumentType, Object> args){return null;}

}