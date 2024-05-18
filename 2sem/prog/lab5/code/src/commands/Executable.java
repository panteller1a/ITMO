package commands;

public interface Executable {
    /** интерфейс выполнения */
    boolean apply(String[] arguments);
}
