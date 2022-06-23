package commands;

import app.CollectionManager;
import app.UserManager;
import data.Request;
import data.Response;
import user.User;

public class Clear implements ICommand {
    private final CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request) {
        User user = UserManager.getInstance().get(request.getLogin(), request.getHash());
        if (user == null) {
            return new Response(request.getCommand(), null, "To execute commands login first!\n\n");
        }

        String output;
        if (collectionManager.clear(user.getLogin())) {
            output = "============= Operation success (Clear) =============\n\n";
        } else {
            output = "\n============== Operation error (Clear) ==============\n\n";
        }
        return new Response(request.getCommand(), null, output);
    }


    @Override
    public String toString() {
        return "clear - Clear the collection";
    }
}
