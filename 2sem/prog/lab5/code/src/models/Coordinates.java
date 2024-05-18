package models;

import java.util.Objects;
import util.Validatable;

public class Coordinates implements Validatable{
    private final float x;
    private final Long y;

    public Coordinates (float x, Long y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean validate() {
        if (x <= -653)
            return false;
        return y <= 452;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}

