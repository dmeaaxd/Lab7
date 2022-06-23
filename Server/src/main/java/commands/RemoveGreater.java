package commands;

import app.CollectionManager;
import app.UserManager;
import data.Request;
import data.Response;
import structure.HumanBeing;
import user.User;

public class RemoveGreater implements ICommand {
    private final CollectionManager collectionManager;
    public RemoveGreater(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request) {
        User user = UserManager.getInstance().get(request.getLogin(), request.getHash());
        if (user == null) {
            return new Response(request.getCommand(), null, "To execute commands login first!\n\n");
        }

        if (request.object == null) {
            return new Response(request.getCommand(), null, "======== Executing command (RemoveGreater) ==========\n", HumanBeing.class);
        } else {
            String output = "";
            HumanBeing humanBeing = (HumanBeing) request.getObject();
            if (collectionManager.removeGreater(humanBeing, user.getLogin())) {
                output = "======== Operation success (RemoveGreater) ==========\n\n";
            } else {
                output = "\n========= Operation error (RemoveGreater) ===========\n\n";
            }
            return new Response(request.getCommand(), null, output);
        }
    }

    @Override
    public String toString() {
        return "remove_greater - Remove from the collection all elements greater than the given";
    }
}
