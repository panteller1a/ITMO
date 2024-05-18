package models;

public enum AstaresCategory {

    SCOUT,
    SUPPRESSOR,
    TACTICAL,
    TERMINATOR;

    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (var weaponType : values()) {
            nameList.append(weaponType.name()).append(", ");
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
