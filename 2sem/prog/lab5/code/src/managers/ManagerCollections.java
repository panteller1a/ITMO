package managers;

import models.SpaceMarine;
import util.Console;
import java.time.LocalDateTime;
import java.util.TreeSet;


public class ManagerCollections {
    private TreeSet<SpaceMarine> collection = new TreeSet<SpaceMarine>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private final DumpManager dumpManager;

    public ManagerCollections(DumpManager dumpManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.dumpManager = dumpManager;

        loadCollection();
    }

    /** возвращается коллекция */
    public TreeSet<SpaceMarine> getCollection() {
        return collection;
    }

    /** возвращается последнее время сохранения */
    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    /** возвращается имя типа коллекции */
    public String collectionType() {
        return collection.getClass().getName();
    }

    /** возвращается последнее время инициализации */
    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    /** возвращается размер коллекции */
    public int collectionSize() {
        return collection.size();
    }

    /*** возвращается последний элемент коллекции или null */
    public SpaceMarine getLast() {
        if (collection.isEmpty()) return null;
        SpaceMarine last = null;
        for (SpaceMarine element : collection) {
            last = element;
        }
        return last;
    }

     /** возвращается элемент по id или null */
     public SpaceMarine getById(int id) {
         for (SpaceMarine element : collection) {
             if (element.getId() == id) {
                 return element;
             }
         }
         return null;
     }

    /** добавление элемента в коллекцию */
    public void addToCollection(SpaceMarine element) {
        collection.add(element);
        SpaceMarine.NextId();
    }


    /** очистка коллекции */
    public void clearCollection() {
        collection.clear();
    }

    /** загрузка коллекции из файла */
    private void loadCollection() {
        collection = new TreeSet<SpaceMarine>((TreeSet<SpaceMarine>) dumpManager.readCollection());
        lastInitTime = LocalDateTime.now();
    }

    /** сохранение коллекции в файл */
    public void saveCollection() {
        dumpManager.writeCollection(collection);
        lastSaveTime = LocalDateTime.now();
    }

    /** удаляет элемент из коллекции */
    public void removeFromCollection(SpaceMarine element) {
        collection.remove(element);
    }

    public double averageOfHealth() {
        if (collection.isEmpty()) {
            return Double.NaN;
        }

        double sum = 0.0;
        for (SpaceMarine marine : collection) {
            sum += marine.getHealth();
        }

        return sum / collection.size();
    }


    public TreeSet<SpaceMarine> getElements() {
        return new TreeSet<>(collection);
    }

    @Override
    public String toString() {
        if (collection.isEmpty()) return "Коллекция пуста!";
        SpaceMarine last = getLast();

        StringBuilder info = new StringBuilder();
        for (SpaceMarine spaceMarine : collection) {
            info.append(spaceMarine);
            if (spaceMarine != last) info.append("\n\n");
        }
        return info.toString();
    }
}
