package common.transfer;

import common.models.SpaceMarine;
import common.utils.Command;
import java.io.Serializable;
import java.util.*;

public class Response implements Serializable {
    public enum ResponseType {
        DEFAULT,
        ACK
    }
    private static final long serialVersionUID = 666;
    private final boolean success;
    private final String message;
    private final TreeSet<SpaceMarine> spaceMarines;
    private final ArrayList<Command> commands;
    private final ResponseType responseType;

    public Response(ResponseType responseType, boolean success, String message, TreeSet<SpaceMarine> spaceMarines) {
        this.success = success;
        this.message = message;
        this.spaceMarines = spaceMarines;
        this.commands = null;
        this.responseType = responseType;
    }

    public Response(ResponseType responseType, boolean success, String message, ArrayList<Command> commands) {
        this.success = success;
        this.message = message;
        this.spaceMarines = null;
        this.commands = commands;
        this.responseType = responseType;
    }

    public Response(ResponseType responseType, boolean success, String message) {
        this.success = success;
        this.message = message;
        this.spaceMarines = null;
        this.commands = null;
        this.responseType = responseType;
    }

    public TreeSet<SpaceMarine> getSpaceMarines() {
        return this.spaceMarines;
    }

    public String getMessage() {
        return message;
    }

    public ResponseType getResponseType() { return this.responseType; }

    public boolean isSuccess() {
        return success;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return Objects.equals(message, response.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, message, spaceMarines);
    }

    @Override
    public String toString() {
        return "Response{" +
                "success='" + success + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }
}