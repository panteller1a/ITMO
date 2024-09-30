package org.lab6.collection;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.stream.Collectors;

import common.models.SpaceMarine;
import common.console.Console;


public class CollectionManager {
    private TreeSet<SpaceMarine> collection = new TreeSet<SpaceMarine>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private final DumpManager dumpManager;

    private long currentId = 1;


    public CollectionManager(DumpManager dumpManager) {
        this.dumpManager = dumpManager;
    }
    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    public void validateAll(Console console) {
        collection.forEach(spaceMarine -> {
            if (!spaceMarine.validate()) {
                console.printError("Космический десант с id=" + spaceMarine.getId() + " имеет невалидные поля.");
            }
        });
        console.println("Выполнена проверка корректности загруженных данных");
    }

    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }


    public boolean init() {
        collection = dumpManager.readCollection();
        lastInitTime = LocalDateTime.now();
        for (SpaceMarine spaceMarine : collection) {
            long id = spaceMarine.getId();
            if (id > currentId) {
                currentId = id;
            }
        }
        return true;
    }

    public long collectionSize() {
        return collection.stream().count();
    }
    public boolean contains(SpaceMarine spaceMarine) {
        return spaceMarine == null || getById(spaceMarine.getId()) != null;
    }

    public boolean add(SpaceMarine spaceMarine) {
        int id = getFreeId();
        spaceMarine.setId(id);
        if (contains(spaceMarine)) return false;
        collection.add(spaceMarine);
        return true;
    }


    public int getFreeId() {
        while (getById(currentId) != null)
            if (++currentId < 0)
                currentId = 1;
        return (int) currentId;
    }


    public SpaceMarine getById(long id) {
        return collection.stream()
                .filter(spaceMarine -> spaceMarine.getId() == id)
                .findFirst()
                .orElse(null);
    }


    public String collectionType() {
        return collection.getClass().getName();
    }


    public SpaceMarine getLast() {
        if (collection.isEmpty()) return null;
        SpaceMarine last = null;
        for (SpaceMarine element : collection) {
            last = element;
        }
        return last;
    }

    public void saveCollection() {
        dumpManager.writeCollection(collection);
        lastSaveTime = LocalDateTime.now();
    }

    public void clear() {
        collection.clear();
    }

    public TreeSet<SpaceMarine> getCollection() {
        return collection;
    }

    public boolean remove(long id) {
        SpaceMarine ToRem = getById(id);
        if (ToRem == null) return false;
        collection.remove(ToRem);
        return true;
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


    public TreeSet<SpaceMarine> getCollectionDescending() {
        Comparator<SpaceMarine> reversedComparator = Comparator.reverseOrder();
        return collection.stream()
                .collect(Collectors.toCollection(() -> new TreeSet<>(reversedComparator)));
    }

    public boolean removeGreater(SpaceMarine spaceMarine) {
        return collection.removeIf(spm -> spm.compareTo(spaceMarine) > 0);
    }

    public double minHealth() {
        return collection.stream()
                .min(Comparator.comparing(SpaceMarine::getHealth))
                .map(SpaceMarine::getHealth)
                .orElse((float) Double.MAX_VALUE);
    }




}
