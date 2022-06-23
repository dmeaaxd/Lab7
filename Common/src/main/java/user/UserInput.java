package user;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

public class UserInput {
    private String login;
    private String password;

    private Scanner input;
    private boolean script;

    public UserInput(Scanner input, boolean silent) {
        this.input = input;
        this.script = silent;
    }

    public boolean loginInput() {
        while (true) {
            if (!script)
                System.out.print("Логин: ");
            login = input.nextLine();
            if (login.trim().length() == 0) {
                System.out.println("Логин не может быть пустым!");
            } else if (login.trim().length() > 10) {
                System.out.println("Логин не может быть больше 10 символов!");
            } else {
                return true;
            }
        }
    }

    public boolean passwordInput() {
        while (true) {
            if (!script)
                System.out.print("Пароль: ");
            password = input.nextLine();
            if (password.trim().length() > 20) {
                System.out.println("Пароль не может быть больше 20 символов!");
            } else {
                return true;
            }
        }
    }

    public User resultElement(Integer id) {
        if (loginInput()
                && passwordInput()
        ) {
            MessageDigest digest = null;
            try {
                digest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException ex) {
                System.out.println("Ошибка алгоритма!");
                System.exit(1);
            }
            byte[] encoded = Base64.getEncoder().encode(digest.digest(password.getBytes(StandardCharsets.UTF_8)));
            String hash = new String(encoded);
            return User.of(login, hash);
        } else {
            return null;
        }
    }
}
