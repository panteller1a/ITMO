package modelsform;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import exceptions.*;
import managers.*;
import models.*;
import util.*;


public class SpaceMarineForm extends Form<SpaceMarine>{
    private final Console console;

    private final long MIN_HEALTH = 1;

    public SpaceMarineForm(Console console, ManagerCollections collectionManager) {
        this.console = console;
    }

    @Override
    public SpaceMarine build() throws IncorrectInput, InvalidForm {
        var spacemarine = new SpaceMarine(
                askName(),
                askCoordinates(),
                LocalDate.now(),
                askHealth(),
                askCategory(),
                askWeaponType(),
                askMeleeWeapon(),
                askChapter()
        );
        if (!spacemarine.validate()) throw new InvalidForm();
        return spacemarine;
    }

    private String askName() throws IncorrectInput {
        String name;
        var fileMode = Scan.fileMode();
        while (true) {
            try {
                console.println("Введите имя Космического Десанта:");
                console.p2();

                name = Scan.getUserScanner().nextLine().trim();
                if (fileMode) console.println(name);
                if (name.equals("")) throw new ShouldBeNotEmpty();
                break;
            } catch (NoSuchElementException exception) {
                console.printError("Имя не распознано!");
                if (fileMode) throw new IncorrectInput();
            } catch (ShouldBeNotEmpty exception) {
                console.printError("Имя не может быть пустым!");
                if (fileMode) throw new IncorrectInput();
            } catch (IllegalStateException exception) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }

        return name;
    }

    private Coordinates askCoordinates() throws IncorrectInput, InvalidForm {
        return new CoordinatesForm(console).build();
    }

    private Double askHealth() throws IncorrectInput {
        var fileMode = Scan.fileMode();
        double health;
        while (true) {
            try {
                console.println("Введите количество здоровья Космического Десанта:");
                console.p2();

                var strHealth = Scan.getUserScanner().nextLine().trim();
                if (fileMode) console.println(strHealth);

                health = Double.parseDouble(strHealth);
                if (health < MIN_HEALTH) throw new NotInDeclaredLimits();
                break;
            } catch (NoSuchElementException exception) {
                console.printError("Здоровье Космического Десанта не распознано!");
                if (fileMode) throw new IncorrectInput();
            } catch (NotInDeclaredLimits exception) {
                console.printError("Здоровье Космического Десанта должно быть больше 0!");
                if (fileMode) throw new IncorrectInput();
            } catch (NumberFormatException exception) {
                console.printError("Здоровье Космического Десанта должно быть числом!");
                if (fileMode) throw new IncorrectInput();
            } catch (NullPointerException | IllegalStateException exception) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return health;
    }

    private AstaresCategory askCategory() throws IncorrectInput {
        return new AstaresCategoryForm(console).build();
    }

    private Weapon askWeaponType() throws IncorrectInput {
        return new WeaponForm(console).build();
    }

    private MeleeWeapon askMeleeWeapon() throws IncorrectInput{
        return new MeleeWeaponForm(console).build();
    }

    public String askWorld() throws IncorrectInput {
        String world;
        var fileMode = Scan.fileMode();
        while (true) {
            try {
                console.println("Введите планету (она может быть и null):");
                console.p2();

                world = Scan.getUserScanner().nextLine().trim();
                if (fileMode) console.println(world);

                if (world.equals("null") || world.equals("")) return null;
                if (world.length() > 1000) throw new IllegalStateException();
                break;
            } catch (NoSuchElementException exception) {
                console.printError("World не распознан!");
                if (fileMode) throw new IncorrectInput();
            } catch (IllegalStateException exception) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }

        return world;
    }
    private Chapter askChapter() throws IncorrectInput, InvalidForm {
        var chapter = new Chapter(askNameOfWorld(), askWorld());
        if (!chapter.validate()) throw new InvalidForm();
        return chapter;

    }

    private String askNameOfWorld() throws IncorrectInput {
        String name;
        var fileMode = Scan.fileMode();
        while (true) {
            try {
                console.println("Введите название мира:");
                console.p2();

                name = Scan.getUserScanner().nextLine().trim();
                if (fileMode) console.println(name);
                if (name.equals("")) throw new ShouldBeNotEmpty();
                break;
            } catch (NoSuchElementException exception) {
                console.printError("Название не распознано!");
                if (fileMode) throw new IncorrectInput();
            } catch (ShouldBeNotEmpty exception) {
                console.printError("Название не может быть пустым!");
                if (fileMode) throw new IncorrectInput();
            } catch (IllegalStateException exception) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }

        return name;
    }

}
