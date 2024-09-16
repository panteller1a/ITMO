package org.lab6.managers;


import common.console.Console;
import common.models.SpaceMarine;
import common.transfer.Response;
import common.utils.Command;

public class ResponseHandler {

    public void handle(Console console, Response response) {
        if (response.isSuccess()) {
            if (response.getMessage() != null) console.println(response.getMessage());

            if (response.getSpaceMarines() != null && !response.getSpaceMarines().isEmpty()) {
                for (SpaceMarine spaceMarines : response.getSpaceMarines()) {
                    console.println(spaceMarines);
                }
            }
            if (response.getCommands() != null && !response.getCommands().isEmpty()) {

                for (Command command : response.getCommands()) {
                    console.println(command.getName() + " " + command.getDescr());
                }
            }
        } else {
            console.printError(response.getMessage());
        }
    }
}
