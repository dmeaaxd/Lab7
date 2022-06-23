package checker;

import java.nio.file.Path;
import java.util.HashSet;

public class ExecuteScriptFileNames {
    private HashSet<Path> executeScriptFileNames = new HashSet<>();

    public boolean checkPath(Path pathP) {
        if (executeScriptFileNames.contains(pathP)) {
            return true;
        }
        executeScriptFileNames.add(pathP);
        return false;
    }

    public void clearList() {
        executeScriptFileNames.clear();
    }
}
