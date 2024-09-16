package org.lab6.utils;


import common.console.Console;
import common.exceptions.*;
import common.models.*;


public class InstanceFiller {


    public static Coordinates fillCoordinates(Console console){
        float x;
        while (true) {
            console.print("Coordinates (X): ");
            var line = console.readln().trim();
            try{
                if (!line.isEmpty()){
                    x = Integer.parseInt(line);
                    if (x <= -653) throw new ValidateExeption("Coordinates X must be bigger than -465.");
                    else break;
                }
                else throw new ValidateExeption("Coordinates.x musn't be null.");
            }
            catch (ValidateExeption | NumberFormatException e) {
                console.printError(e.getMessage());
            }
        }

        long y;
        while (true) {
            console.print("Coordinates (Y): ");
            var line = console.readln().trim();
            try {
                y = Long.parseLong(line);
                if (y > 452) throw new ValidateExeption("Coordinates Y must be smoller than -493.");
                else break;
            }
            catch (ValidateExeption | NumberFormatException e) {
                console.printError(e.getMessage());
            }
        }

        return new Coordinates(x, y);
    }


    public static Chapter fillChapter(Console console){

        String name;
        while (true){

            console.print("Chapter name: ");
            name = console.readln();

            try{
                if (name == null || name.trim().isEmpty()) throw new ValidateExeption("Chapter name mustn't be null or empty.");
                else break;
            }
            catch (ValidateExeption e){
                console.printError(e.getMessage());
            }
        }
        console.print("Chapter world: ");
        String world = console.readln().trim();

        return new Chapter(name.trim(), world);
    }



    public static AstaresCategory fillAstaresCategory (Console console){
        AstaresCategory r;
        while (true) {
            console.print("AstaresCategory ("+AstaresCategory.names()+"): ");
            var line = console.readln().trim();
            line = line.toUpperCase();
            try {
                r = AstaresCategory.valueOf(line);
                break;
            } catch (IllegalArgumentException e) {
                console.printError(e.getMessage());
            }
        }
        return r;
    }

    public static MeleeWeapon fillMeleeWeapon (Console console){
        MeleeWeapon r;
        while (true) {
            console.print("MeleeWeapon ("+MeleeWeapon.names()+"): ");
            var line = console.readln().trim();
            line = line.toUpperCase();
            try {
                r = MeleeWeapon.valueOf(line);
                break;
            } catch (IllegalArgumentException e) {
                console.printError(e.getMessage());
            }
        }
        return r;
    }

    public static Weapon fillWeapon (Console console){
        Weapon r;
        while (true) {
            console.print("Weapon ("+Weapon.names()+"): ");
            var line = console.readln().trim();
            line = line.toUpperCase();
            try {
                r = Weapon.valueOf(line);
                break;
            } catch (IllegalArgumentException e) {
                console.printError(e.getMessage());
            }
        }
        return r;
    }

    public static SpaceMarine fillSpaceMarine (Console console){
        console.println("Creating Spacee Marine");

        String name;
        while (true){
            console.print("Name: ");
            name = console.readln();
            try{
                if (name == null || name.trim().isEmpty())
                    throw new ValidateExeption("Name mustn't be null or empty.");
                else
                    break;
            }
            catch (ValidateExeption e){
                console.printError(e.getMessage());
            }
        }
        Coordinates coordinates = fillCoordinates(console);
        AstaresCategory astaresCategory = fillAstaresCategory(console);
        MeleeWeapon meleeWeapon = fillMeleeWeapon(console);
        Weapon weapon = fillWeapon(console);
        Chapter chapter = fillChapter(console);
        Float health;
        while (true){
            console.print("health: ");
            var line = console.readln().trim();
            try{
                if (line.isEmpty()) throw new ValidateExeption("health mustn't be null.");
                else {
                    health = Float.parseFloat(line);
                    if (health <= 0) throw new ValidateExeption("health must be bigger than 0.");
                    else break;
                }
            }
            catch (ValidateExeption | NumberFormatException  e){
                console.printError(e.getMessage());
            }
        }


        return new SpaceMarine(-1, name, coordinates, health, astaresCategory, weapon, meleeWeapon, chapter);
    }
}