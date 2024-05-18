package models;

import util.Validatable;
import java.util.Objects;
public class Chapter implements Validatable {
    private final String name;
    private final String world;

    public Chapter(String name, String world) {
        this.name = name;
        this.world = world;
    }
    @Override
    public boolean validate() {
        return name != null && !name.isEmpty();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chapter address = (Chapter) o;
        return Objects.equals(name, address.name) && Objects.equals(world, address.world);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, world);
    }

    @Override
    public String toString() {
        return "глава " + name + (world == null ? "" : ", " + world);
    }
}


