package managers;

import com.google.gson.reflect.TypeToken;
import com.google.gson.*;
import java.io.*;
import java.time.LocalDate;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.TreeSet;
import models.SpaceMarine;
import util.LocalDateTimeAdapter;
import util.Console;
import com.google.gson.JsonParseException;

public class DumpManager {

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .registerTypeAdapter(LocalDate.class, new LocalDateTimeAdapter())
            .create();

    private final String fileName;
    private final Console console;

    public DumpManager(String fileName, Console console) {
        String filePath = fileName;
        if (!(new File(fileName).exists())) {
            filePath = "../" + fileName;
        }
        this.fileName = filePath;
        this.console = console;


    }

    public void writeCollection(TreeSet<SpaceMarine> collection) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(gson.toJson(collection));
            console.println("Коллекция успешно сохранена в файл!");
        } catch (IOException exception) {
            console.printError("Загрузочный файл не может быть открыт!");
        }
    }

    public TreeSet<SpaceMarine> readCollection() {
        if (fileName != null && !fileName.isEmpty()) {
            try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
                var collectionType = new TypeToken<TreeSet<SpaceMarine>>() {}.getType();

                var jsonString = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (!line.equals("")) {
                        jsonString.append(line);
                    }
                }

                if (jsonString.isEmpty()) {
                    return new TreeSet<>();
                }

                TreeSet<SpaceMarine> collection = gson.fromJson(jsonString.toString(), collectionType);

                TreeSet<SpaceMarine> validatedCollection = new TreeSet<>();
                for (SpaceMarine spaceMarine : collection) {
                    if (spaceMarine.validate()) {
                        validatedCollection.add(spaceMarine);
                    } else {
                        console.printError("Космический Десант с id=" + spaceMarine.getId() + " имеет невалидные поля и не будет загружен.");
                    }
                }

                console.println("Коллекция успешно загружена!");
                return validatedCollection;

            } catch (FileNotFoundException exception) {
                console.printError("Загрузочный файл не найден!");
            } catch (JsonParseException exception) {
                console.printError("В загрузочном файле не обнаружена необходимая коллекция!");
            } catch (IllegalStateException | IOException exception) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        } else {
            console.printError("Аргумент командной строки с загрузочным файлом не найден!");
        }
        return new TreeSet<>();
    }
}
