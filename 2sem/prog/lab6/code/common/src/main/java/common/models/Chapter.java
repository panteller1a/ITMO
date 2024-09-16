package common.models;


import common.models.interfaces.Validatable;

import java.io.Serializable;
import java.util.Objects;

public class Chapter implements Validatable, Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String world;

    public Chapter(String name, String world) {
        this.name = name;
        this.world = world;
    }

    public Chapter(){}

    @Override
    public boolean validate() {
        return name != null && !name.isEmpty();
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


    public String getName() {
        return this.name;
    }

    public String getWorld() {
        return this.world;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWorld(String world) {
        this.world = world;
    }
}


