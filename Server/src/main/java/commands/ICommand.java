package commands;

import data.Request;
import data.Response;

public interface ICommand {
    Response execute(Request request);
}
