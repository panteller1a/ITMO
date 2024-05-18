package modelsform;

import models.Weapon;
import util.*;
import exceptions.*;

import java.util.NoSuchElementException;

public class WeaponForm extends Form<Weapon>{

    private final Console console;

    public WeaponForm(Console console) {
        this.console = console;
    }

    @Override
    public Weapon build() throws IncorrectInput {
        var fileMode = Scan.fileMode();

        String strWepon;
        Weapon weapon;
        while (true) {
            try {
                console.println("Список дальнего оружия - " + Weapon.names());
                console.println("Введите оружие (или null):");
                console.p2();

                strWepon = Scan.getUserScanner().nextLine().trim();
                if (fileMode) console.println(strWepon);

                if (strWepon.equals("") || strWepon.equals("null")) return null;
                weapon = Weapon.valueOf(strWepon.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                console.printError("Дальнее оружие не распознано!");
                if (fileMode) throw new IncorrectInput();
            } catch (IllegalArgumentException exception) {
                console.printError("Дальнего оружия нет в списке!");
                if (fileMode) throw new IncorrectInput();
            } catch (IllegalStateException exception) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return weapon;
    }
}
