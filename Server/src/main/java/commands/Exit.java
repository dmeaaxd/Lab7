package commands;

import data.Request;
import data.Response;

public class Exit implements ICommand {
    public Exit() {
    }

    @Override
    public Response execute(Request request) {
        return null;
    }

    @Override
    public String toString() {
        return "exit - Exit from the application";
    }
}
