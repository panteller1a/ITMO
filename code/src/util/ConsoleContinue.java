package util;

public class ConsoleContinue implements Console {
    private static final String p1 = "$ ";
    private static final String p2 = "> ";

    public void print(Object obj) {
        System.out.print(obj);
    }

    public void println(Object obj) {
        System.out.println(obj);
    }

    public void printError(Object obj) {
        System.out.println("ошибка: " + obj);
    }

    public void printTable(Object elementLeft, Object elementRight) {
        System.out.printf(" %-35s%-1s%n", elementLeft, elementRight);
    }

    public void p1() {
        print(p1);
    }

    public void p2() {
        print(p2);
    }

    public String getp1() {
        return p1;
    }

}

