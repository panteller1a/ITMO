package modelsform;

import exceptions.IncorrectInput;
import models.MeleeWeapon;
import util.Console;
import util.Scan;

import java.util.NoSuchElementException;

public class MeleeWeaponForm extends Form<MeleeWeapon>{

    private final Console console;

    public MeleeWeaponForm(Console console) {
        this.console = console;
    }

    @Override
    public MeleeWeapon build() throws IncorrectInput {
        var fileMode = Scan.fileMode();

        String strMeleeWeapon;
        MeleeWeapon meleeWeapon;
        while (true) {
            try {
                console.println("Список ближнего оружия - " + MeleeWeapon.names());
                console.println("Введите ближние оружие:");
                console.p2();

                strMeleeWeapon = Scan.getUserScanner().nextLine().trim();
                if (fileMode) console.println(strMeleeWeapon);

                if (strMeleeWeapon.equals("") || strMeleeWeapon.equalsIgnoreCase("null") || strMeleeWeapon.equals("0")) {
                    throw new IllegalArgumentException("Десант не может существовать без ближнего оружия!");
                }
                meleeWeapon = MeleeWeapon.valueOf(strMeleeWeapon.toUpperCase());
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
        return meleeWeapon;
    }
}
