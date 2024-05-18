package util;

import exceptions.*;
import managers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Run {

    public enum ExitCode {
        OK,
        ERROR,
        EXIT,
    }

    private final Console console;
    private final CommandManager commandManager;
    private final List<String> scriptStack = new ArrayList<>();

    public Run(Console console, CommandManager commandManager) {
        this.console = console;
        this.commandManager = commandManager;
    }

    /** режим */
    public void interactiveMode() {
        var userScanner = Scan.getUserScanner();
        try {
            ExitCode commandStatus;
            String[] userCommand = {"", ""};

            do {
                console.p1();
                userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();

                commandManager.addToHistory(userCommand[0]);
                commandStatus = launchCommand(userCommand);
            } while (commandStatus != ExitCode.EXIT);

        } catch (NoSuchElementException exception) {
            console.printError("Пользовательский ввод не обнаружен!");
        } catch (IllegalStateException exception) {
            console.printError("Непредвиденная ошибка!");
        }
    }

    public ExitCode scriptMode(String argument) {
        String[] userCommand = {"", ""};
        ExitCode commandStatus = ExitCode.OK;
        scriptStack.add(argument);
        if (!new File(argument).exists()) {
            argument = "../" + argument;
        }
        try (Scanner scriptScanner = new Scanner(new File(argument))) {
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = Scan.getUserScanner();
            Scan.setUserScanner(scriptScanner);
            Scan.setFileMode();

            while (scriptScanner.hasNextLine() && commandStatus == ExitCode.OK) {
                String line = scriptScanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                userCommand = (line + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                console.println(console.getp1() + String.join(" ", userCommand));

                if ("execute_script".equals(userCommand[0])) {
                    for (String script : scriptStack) {
                        if (userCommand[1].equals(script)) throw new ScriptRecursion();
                    }
                }
                commandStatus = launchCommand(userCommand);
            }

            Scan.setUserScanner(tmpScanner);
            Scan.setUserMode();

            if (commandStatus == ExitCode.ERROR && !("execute_script".equals(userCommand[0]) && !userCommand[1].isEmpty())) {
                console.println("Проверьте скрипт на корректность введенных данных!");
            }

            return commandStatus;

        } catch (FileNotFoundException exception) {
            console.printError("Файл со скриптом не найден!");
        } catch (NoSuchElementException exception) {
            console.printError("Файл со скриптом пуст!");
        } catch (ScriptRecursion exception) {
            console.printError("Скрипты не могут вызываться рекурсивно!");
        } catch (IllegalStateException exception) {
            console.printError("Непредвиденная ошибка!");
            System.exit(0);
        } finally {
            scriptStack.remove(scriptStack.size() - 1);
        }
        return ExitCode.ERROR;
    }

    private ExitCode launchCommand(String[] userCommand) {
        if (userCommand[0].equals("")) return ExitCode.OK;
        var command = commandManager.getCommands().get(userCommand[0]);

        if (command == null) {
            console.printError("Команда '" + userCommand[0] + "' не найдена. Наберите 'help' для справки");
            return ExitCode.ERROR;
        }

        if ("exit".equals(userCommand[0])) {
            if (!command.apply(userCommand)) return ExitCode.ERROR;
            else return ExitCode.EXIT;
        } else if ("execute_script".equals(userCommand[0])) {
            if (!command.apply(userCommand)) return ExitCode.ERROR;
            else return scriptMode(userCommand[1]);
        } else {
            if (!command.apply(userCommand)) return ExitCode.ERROR;
        }

        return ExitCode.OK;
    }

}
