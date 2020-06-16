package mtgcogwork.magic;

import java.util.HashMap;
import java.util.Map;

public class MagicAction {

    private static final Map<String, MagicAction> INSTANCES = new HashMap<>();

    public static final MagicAction getInstance(String actionString) {
        return INSTANCES.computeIfAbsent(actionString, MagicAction::new);
    }

    private final String type;

    private MagicAction(String actionString) {
        String[] args = actionString.split(" ");
        this.type = args[0];
    }

    public String getType() {
        return this.type;
    }

}
