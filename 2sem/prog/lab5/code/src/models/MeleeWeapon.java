package models;

public enum MeleeWeapon {

    CHAIN_SWORD,
    LIGHTING_CLAW,
    POWER_BLADE;

    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (var weaponType : values()) {
            nameList.append(weaponType.name()).append(", ");
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
