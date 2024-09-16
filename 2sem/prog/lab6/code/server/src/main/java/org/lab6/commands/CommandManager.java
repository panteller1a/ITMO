package org.lab6.commands;

import common.console.Console;
import common.console.StandartConsole;
import common.utils.Command;
import org.lab6.collection.CollectionManager;
import org.lab6.commands.commands.*;

import java.util.*;
public class CommandManager {

    private final Map<String, Command> commands;

    public CommandManager(CollectionManager collectionManager, Console console){
        this.commands = new LinkedHashMap<>();
        commands.put("help", new Help(this));
        commands.put("exit", new Exit());
        commands.put("add", new Add(console, collectionManager));
        commands.put("show", new Show(console, collectionManager));
        commands.put("clear", new Clear(console, collectionManager));
        commands.put("info", new Info(console, collectionManager));
        commands.put("remove_by_id", new RemoveById(console, collectionManager));
        commands.put("update", new Update(console, collectionManager));
        commands.put("remove_greater", new RemoveGreater(console, collectionManager));
        commands.put("add_if_min", new AddIfMin(console, collectionManager));
        commands.put("average_of_health", new AverageOfHealth(console, collectionManager));
        commands.put("print_descending", new PrintDescending(console, collectionManager));
        commands.put("history", new History());
        commands.put("save", new Save(console, collectionManager));
    }


    public Map<String, Command> getCommands() {
        return Collections.unmodifiableMap(commands);
    }

    public List<Command> getCommandsList(){
        List<Command> res = new ArrayList<>();
        for (String key: commands.keySet()) {
            res.add(commands.get(key));
        }
        return res;
    }
}
