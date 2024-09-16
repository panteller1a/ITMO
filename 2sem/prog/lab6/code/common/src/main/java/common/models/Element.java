package common.models;

import common.models.interfaces.Validatable;

public abstract class Element implements Comparable<Element>, Validatable {
    abstract public int getId();

}