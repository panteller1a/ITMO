package common.models;



import common.models.interfaces.Validatable;

import java.io.Serializable;
import java.security.PublicKey;
import java.util.Objects;

public class Coordinates implements Validatable, Serializable{
    private static final long serialVersionUID = 2L;
    private float x;
    private Long y;

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

    public float getX() {
        return this.x;
    }
    public Long getY() {
        return this.y;
    }

    public void setX(float x) {
        this.x = x;
    }
    public void setY(Long y) {
        this.y = y;
    }

}

