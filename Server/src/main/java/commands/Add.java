package commands;

import app.CollectionManager;
import app.UserManager;
import data.Request;
import data.Response;
import structure.HumanBeing;
import user.User;


/**
 * Команда Add
 */
public class Add implements ICommand{
    private final CollectionManager collectionManager;

    public Add(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request) {
        User user = UserManager.getInstance().get(request.getLogin(), request.getHash());
        if (user == null) {
            return new Response(request.getCommand(), null, "To execute commands login first!\n\n");
        }

        if (request.object == null) {
            return new Response(request.getCommand(), null, "============= Executing command (Add) ===============\n", HumanBeing.class);
        } else {
            String output = "";
            HumanBeing humanBeing = (HumanBeing) request.getObject();
            humanBeing.setOwner(user.getLogin());
            if (collectionManager.add(humanBeing)) {
                output = "============== Operation success (Add) ==============\n\n";
            } else {
                output = "=============== Operation error (Add) ===============\n\n";
            }
            return new Response(request.getCommand(), null, output);
        }

    }

    @Override
    public String toString() {
        return "add {element} - Add a new element to the collection";
    }
}