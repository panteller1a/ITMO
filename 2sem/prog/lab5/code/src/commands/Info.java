package commands;
import managers.ManagerCollections;
import util.Console;
import java.time.LocalDateTime;

public class Info extends Command{

    private final Console console;
    private final ManagerCollections Managercollection;

    public Info(Console console, ManagerCollections collectionManager) {
        super("info", "вывести информацию о коллекции");
        this.console = console;
        this.Managercollection = collectionManager;
    }

    /** выполнение команды info с выводом успеха */
    @Override
    public boolean apply(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            console.println("Использование: '" + getName() + "'");
            return false;
        }

        LocalDateTime lastInitTime = Managercollection.getLastInitTime();
        String lastInitTimeString = (lastInitTime == null) ? "в данной сессии инициализации еще не происходило" :
                lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();

        LocalDateTime lastSaveTime = Managercollection.getLastSaveTime();
        String lastSaveTimeString = (lastSaveTime == null) ? "в данной сессии сохранения еще не происходило" :
                lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.toLocalTime().toString();

        console.println("Сведения о коллекции:");
        console.println(" Тип: " + Managercollection.collectionType());
        console.println(" Дата последней инициализации: " + lastInitTimeString);
        console.println(" Дата последнего сохранения: " + lastSaveTimeString);
        console.println(" Количество элементов: " + Managercollection.collectionSize());
        return true;
    }

}
