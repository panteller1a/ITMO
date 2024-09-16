package org.lab6.managers;

import common.console.Console;
import common.console.StandartConsole;
import common.utils.Command;
import org.lab6.commands.commands.*;

import java.util.*;


public class CommandManager {
    private final Map<String, Command> commands;
    private final ArrayDeque<String> history = new ArrayDeque<>();

    public CommandManager(Console console){
        this.commands = new LinkedHashMap<>();
        commands.put("help", new Help());
        commands.put("exit", new Exit());
        commands.put("add", new Add());
        commands.put("show", new Show());
        commands.put("clear", new Clear());
        commands.put("info", new Info());
        commands.put("remove_by_id", new RemoveById());
        commands.put("update", new Update());
        commands.put("remove_greater", new RemoveGreater());
        commands.put("add_if_min", new AddIfMin());
        commands.put("average_of_health", new AverageOfHealth());
        commands.put("print_descending", new PrintDescending());
        commands.put("history", new History());
        commands.put("execute_script", new ExecuteScript(console));
    }

    public Map<String, Command> getCommands() {
        return Collections.unmodifiableMap(commands);
    }

    public void addHistory(String hist_piece){
        if (history.size() == 9){
            history.pop();
        }
        history.push(hist_piece);
    }


    public String getHistory(){
        Iterator<String> nEl = history.iterator();
        StringBuilder req = new StringBuilder();
        while (nEl.hasNext()){
            req.append(nEl.next()).append("\n");
        }
        return req.toString();
    }
}