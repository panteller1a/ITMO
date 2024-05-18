package modelsform;

import exceptions.*;
import models.*;
import util.*;
import java.util.NoSuchElementException;

public class AstaresCategoryForm extends Form<AstaresCategory>{
    private final Console console;

    public AstaresCategoryForm(Console console) {
        this.console = console;
    }

    @Override
    public AstaresCategory build() throws IncorrectInput {
        var fileMode = Scan.fileMode();

        String strAstaresCategory;
        AstaresCategory astaresCategory;
        while (true) {
            try {
                console.println("Список классов десанта - " + AstaresCategory.names());
                console.println("Введите класс:");
                console.p2();

                strAstaresCategory = Scan.getUserScanner().nextLine().trim();
                if (fileMode) console.println(strAstaresCategory);

                if (strAstaresCategory.equals("") || strAstaresCategory.equalsIgnoreCase("null") || strAstaresCategory.equals("0")) {
                    throw new IllegalArgumentException("У каждого десанта есть свой класс!");
                }
                astaresCategory = AstaresCategory.valueOf(strAstaresCategory.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                console.printError("Класс не распознан!");
                if (fileMode) throw new IncorrectInput();
            } catch (IllegalArgumentException exception) {
                console.printError("Класса нет в списке!");
                if (fileMode) throw new IncorrectInput();
            } catch (IllegalStateException exception) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return astaresCategory;
    }
}
