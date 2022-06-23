package app;

import commands.*;
import data.Request;
import data.Response;

import java.util.HashMap;

public class DataProvider {
    public static final HashMap<String, ICommand> commands = new HashMap<>();
    private CollectionManager collectionManager;

    public DataProvider() {
        collectionManager = new CollectionManager();
        commands.put("help", new Help());
        commands.put("clear", new Clear(collectionManager));
        commands.put("add", new Add(collectionManager));
        commands.put("info", new Info(collectionManager));
        commands.put("remove_greater", new RemoveGreater(collectionManager));
        commands.put("remove_lower", new RemoveLower(collectionManager));
        commands.put("show", new Show(collectionManager));
        commands.put("update", new Update(collectionManager));
        commands.put("remove_by_id", new RemoveById(collectionManager));
        commands.put("print_ascending", new PrintAscending(collectionManager));
        commands.put("exit", new Exit());
        commands.put("execute_script", new ExecuteScript(this, collectionManager));
        commands.put("user_register", new UserRegister());
        commands.put("user_login", new UserLogin());

    }

    public Response execute(Request request) {
        if (commands.containsKey(request.getCommand())) {
            return commands.get(request.getCommand()).execute(request);
        } else {
            return new Response(request.getCommand(), null, "Command not found!\n\n");
        }
    }
}