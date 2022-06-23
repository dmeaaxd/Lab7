package commands;

import app.UserManager;
import data.Request;
import data.Response;
import user.User;

public class UserRegister implements ICommand {
    public UserRegister() {
    }

    public Response execute(Request request) {
        if (request.object == null) {
            return new Response(request.getCommand(), null, "======== Executing command (User Register) ==========\n", User.class);
        } else {
            String login = null;
            String hash = null;
            String output = "";
            User user = (User) request.getObject();
            User auser = UserManager.getInstance().get(user.getLogin());

            if (auser != null) {
                output = "User with such login already exists\n";
            }
            if (auser == null && UserManager.getInstance().register(user)) {
                output = output + "======== Operation success (User Register) ==========\n\n";
                login = user.getLogin();
                hash = user.getHash();
            } else {
                output = output + "========== Operation error (User Register) ==========\n\n";
            }
            return new Response(request.getCommand(), null, output, login, hash);
        }
    }

    @Override
    public String toString() {
        return "user_register - Register new user";
    }
}
