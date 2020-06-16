package mtgcogwork.magic;

import java.util.HashMap;
import java.util.Map;

public class Action {

    private static final Map<String, Action> INSTANCES = new HashMap<>();

    public static final Action getInstance(String actionString) {
        return INSTANCES.computeIfAbsent(actionString, Action::new);
    }

    private final String type;

    private Action(String actionString) {
        String[] args = actionString.split(" ");
        this.type = args[0];
    }

    public String getType() {
        return this.type;
    }

}
