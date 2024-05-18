package models;

public enum Weapon {


    PLASMA_GUN,
    COMBI_PLASMA_GUN,
    FLAMER,
    GRENADE_LAUNCHER,
    MULTI_MELTA;

    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (var weaponType : values()) {
            nameList.append(weaponType.name()).append(", ");
        }
        return nameList.substring(0, nameList.length()-2);
    }

}
