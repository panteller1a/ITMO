package modelsform;

import exceptions.*;
import util.*;
import models.*;
import java.util.NoSuchElementException;
public class CoordinatesForm extends Form<Coordinates>  {

    private final Console console;

    public CoordinatesForm(Console console) {
        this.console = console;
    }

    @Override
    public Coordinates build() throws IncorrectInput, InvalidForm {
        var coordinates = new Coordinates(askX(), askY());
        if (!coordinates.validate()) throw new InvalidForm();
        return coordinates;
    }

    /** запрашивает координату у пользователя и возвращает либо ее либо ошибку*/
    public Float askX() throws IncorrectInput {
        var fileMode = Scan.fileMode();
        float x;
        while (true) {
            try {
                console.println("Введите координату X (Значение должно быть больше -653!):");
                console.p2();
                var strX = Scan.getUserScanner().nextLine().trim();
                if (fileMode) console.println(strX);

                x = Float.parseFloat(strX);
                if (x < -653) throw new NotInDeclaredLimits();
                break;
            } catch (NoSuchElementException exception) {
                console.printError("Координата X не распознана!");
                if (fileMode) throw new IncorrectInput();
            } catch (NotInDeclaredLimits exception) {
                console.printError("Координата X должна быть больше -653!");
                if (fileMode) throw new IncorrectInput();
            } catch (NumberFormatException exception) {
                console.printError("Координата X должна быть представлена числом!");
                if (fileMode) throw new IncorrectInput();
            } catch (NullPointerException | IllegalStateException exception) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return x;
    }

    /** запрашивает координату у пользователя и возвращает либо ее либо ошибку*/
    public Long askY() throws IncorrectInput {
        var fileMode = Scan.fileMode();
        long y;
        while (true) {
            try {
                console.println("Введите координату Y (Значение должно быть меньше 452!):");
                console.p2();
                var strY = Scan.getUserScanner().nextLine().trim();
                if (fileMode) console.println(strY);

                y = Long.parseLong(strY);
                if (y > 452) throw new NotInDeclaredLimits();
                break;
            } catch (NoSuchElementException exception) {
                console.printError("Координата Y не распознана!");
                if (fileMode) throw new IncorrectInput();
            } catch (NotInDeclaredLimits exception) {
                console.printError("Значение должно быть меньше 452!");
                if (fileMode) throw new IncorrectInput();
            } catch (NumberFormatException exception) {
                console.printError("Координата Y должна быть представлена числом!");
                if (fileMode) throw new IncorrectInput();
            } catch (NullPointerException | IllegalStateException exception) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return y;
    }
}
