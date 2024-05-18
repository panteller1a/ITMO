import java.util.Scanner;


import models.*;
import commands.*;
import managers.*;
import util.*;


public class Main {
    public static void main(String[] args) {
        var console = new ConsoleContinue();
        Scanner userScanner = new Scanner(System.in);
        Scan.setUserScanner(userScanner);


        if (args.length == 0) {
            System.out.println("Введите название файла для работы с ним");
            System.exit(-1);
        }

        var dumpManager = new DumpManager(args[0], console);
        var collectionManager = new ManagerCollections(dumpManager);


        SpaceMarine.updateNextId(collectionManager);

        var commandManager = new CommandManager() {{
            register("help", new Help(console, this));
            register("info", new Info(console, collectionManager));
            register("show", new Show(console, collectionManager));
            register("add", new Add(console, collectionManager));
            register("update", new Update(console, collectionManager));
            register("remove_by_id", new RemoveById(console, collectionManager));
            register("clear", new Clear(console, collectionManager));
            register("save", new Save(console, collectionManager));
            register("execute_script", new Execute(console));
            register("exit", new Exit(console));
            register("add_if_min", new AddIfMin(console, collectionManager));
            register("remove_greater", new RemoveGreater(console, collectionManager));
            register("history", new History(console, this));
            register("average_of_health", new AverageOfHealth(console, collectionManager));
            register("print_descending", new PrintDescending(console, collectionManager));

        }};

        new Run(console, commandManager).interactiveMode();
    }
}
