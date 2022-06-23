package commands;

import app.CollectionManager;
import app.UserManager;
import data.Request;
import data.Response;
import structure.HumanBeing;
import user.User;

public class Update implements ICommand {
    private final CollectionManager collectionManager;
    public Update(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request) {
        User user = UserManager.getInstance().get(request.getLogin(), request.getHash());
        if (user == null) {
            return new Response(request.getCommand(), null, "To execute commands login first!\n\n");
        }

        HumanBeing updateHumanBeing = null;
        String output = null;
        try {
            int id = Integer.parseInt(request.getArgs()[0]);
            updateHumanBeing = collectionManager.getById(id);
            if (updateHumanBeing == null) {
                output = "Object with id=" + id + " not found!\n\n";
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            output = "Invalid input! ID was not specified!\n\n";
        }
        catch (NumberFormatException e) {
            output = "Invalid input! ID must be a number!\n\n";
        }

        if (output != null) {
            return new Response(request.getCommand(), null, output);
        }

        if (request.object == null) {
            return new Response(request.getCommand(), request.getArgs(), "=========== Executing command (Update) ==============\n", HumanBeing.class);
        } else {
            HumanBeing humanBeing = (HumanBeing) request.getObject();
            if (collectionManager.updateElement(updateHumanBeing, humanBeing, user.getLogin())) {
                output = "============ Operation success (Update) =============\n\n";
            } else {
                output = "============= Operation error (Update) ==============\n\n";
            }
            return new Response(request.getCommand(), null, output);
        }
    }

    @Override
    public String toString() {
        return "update {id} - Update the value of the collection element whose id is equal to the given one";
    }
}
