package util;
import java.util.Scanner;

public class Scan {
    private static Scanner userScanner;
    private static boolean fileMode = false;

    public static void setUserScanner(Scanner userScanner) {
        Scan.userScanner = userScanner;
    }
    public static Scanner getUserScanner() {
        return userScanner;
    }
    public static void setUserMode() {
        Scan.fileMode = false;
    }
    public static boolean fileMode() {
        return fileMode;
    }
    public static void setFileMode() {
        Scan.fileMode = true;
    }



}
