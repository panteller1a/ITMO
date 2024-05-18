package models;

import managers.ManagerCollections;
import util.Element;
import util.Validatable;

import java.time.LocalDate;
import java.util.Objects;


public class SpaceMarine extends Element implements Validatable {
    private static int nextID = 1;
    private final int id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double health; //Поле не может быть null, Значение поля должно быть больше 0
    private AstaresCategory category; //Поле не может быть null
    private Weapon weaponType; //Поле может быть null
    private MeleeWeapon meleeWeapon; //Поле не может быть null
    private Chapter chapter; //Поле может быть null


    public SpaceMarine(String name, Coordinates coordinates, LocalDate creationDate, Double health, AstaresCategory
            category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter) {
        this.id = nextID;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.health = health;
        this.category = category;
        this.weaponType = weaponType;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }

    @Override
    public boolean validate() {
        if (id <= 0)
            return false;
        if (name == null || name.isEmpty())
            return false;
        if (coordinates == null)
            return false;
        if (creationDate == null)
            return false;
        if (health == null || health <= 0)
            return false;
        if (category == null)
            return false;
        return meleeWeapon != null;
    }

    public void update(SpaceMarine spaceMarine) {
        this.name = spaceMarine.name;
        this.coordinates = spaceMarine.coordinates;
        this.creationDate = spaceMarine.creationDate;
        this.health = spaceMarine.health;
        this.category = spaceMarine.category;
        this.weaponType = spaceMarine.weaponType;
        this.meleeWeapon = spaceMarine.meleeWeapon;
        this.chapter = spaceMarine.chapter;
    }

    public static void updateNextId(ManagerCollections collectionManager) {
        int maxId = 0;
        for (SpaceMarine spaceMarine : collectionManager.getCollection()) {
            if (spaceMarine != null) {
                int id = spaceMarine.getId();
                if (id > maxId) {
                    maxId = id;
                }
            }
        }
        nextID = 1 + maxId;
    }


    public static void NextId() {
        nextID++;
    }

    @Override
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public Double getHealth() {
        return health;
    }

    @Override
    public int compareTo(Element element) {
        return (this.id - element.getId());
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpaceMarine spaceMarine = (SpaceMarine) o;
        return id == spaceMarine.id && Objects.equals(name, spaceMarine.name) && Objects.equals(coordinates, spaceMarine.coordinates)
                && Objects.equals(creationDate, spaceMarine.creationDate) && Objects.equals(health, spaceMarine.health)
                && Objects.equals(category, spaceMarine.category) && Objects.equals(weaponType, spaceMarine.weaponType)
                && Objects.equals(meleeWeapon, spaceMarine.meleeWeapon) && Objects.equals(chapter, spaceMarine.chapter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, health, category, weaponType, meleeWeapon, chapter);
    }

    @Override
    public String toString() {
        String info = "";
        info += "Космический десант №" + id;
        info += " (добавлен " + creationDate.toString() + ")";
        info += "\n Имя: " + name;
        info += "\n Местоположение: " + coordinates;
        info += "\n Количество здоровья: " + health;
        info += "\n Вид Астартеса: " + category;
        info += "\n Тип дальнего оружия: " + weaponType;
        info += "\n Тип ближнего оружия: " + meleeWeapon;
        info += "\n Миссия: " + chapter;
        return info;
    }
}

