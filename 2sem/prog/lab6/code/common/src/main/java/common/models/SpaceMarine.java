package common.models;

import common.models.interfaces.Validatable;
import common.models.Element;
import common.models.SpaceMarine;

import java.io.Serial;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.io.Serializable;


public class SpaceMarine extends Element implements Validatable, Serializable {

    @Serial
    private static final long serialVersionUID = 10L;
    private int id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float health; //Поле не может быть null, Значение поля должно быть больше 0
    private AstaresCategory category; //Поле не может быть null
    private Weapon weaponType; //Поле может быть null
    private MeleeWeapon meleeWeapon; //Поле не может быть null
    private Chapter chapter; //Поле может быть null


    public SpaceMarine(int id, String name, Coordinates coordinates, Float health, AstaresCategory
            category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = new Date();
        this.health = health;
        this.category = category;
        this.weaponType = weaponType;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }

    public SpaceMarine() {}

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Float getHealth() {
        return health;
    }

    public AstaresCategory getCategory() {
        return category;
    }

    public Weapon getWeaponType() {
        return weaponType;
    }

    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setHealth(Float health) {
        this.health = health;
    }

    public void setCategory(AstaresCategory category) {
        this.category = category;
    }

    public void setWeaponType(Weapon weaponType) {
        this.weaponType = weaponType;
    }

    public void setMeleeWeapon(MeleeWeapon meleeWeapon) {
        this.meleeWeapon = meleeWeapon;
    }

    public void setChapter(Chapter chapter) {
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

