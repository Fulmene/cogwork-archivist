package alchemagis.magic;

import java.util.List;

public final class MagicConstants {

    public static final int MIN_DECK_SIZE = 60;
    public static final int MAX_COPIES = 4;

    public static final List<String> colors = List.of("white", "blue", "black", "red", "green");
    public static final List<String> supertypes = List.of("basic", "legendary", "ongoing", "snow", "world");
    public static final List<String> types = List.of("artifact", "conspiracy", "creature", "enchantment", "instant", "land", "phenomenon", "plane", "planeswalker", "scheme", "sorcery", "tribal", "vanguard");
    public static final List<String> subtypes = List.of(
            // artifact types
            "clue", "contraption", "equipment", "fortification", "treasure", "vehicle",
            // enchantment types
            "aura", "cartouche", "curse", "saga", "shrine",
            // land types
            "desert", "forest", "gate", "island", "lair", "locus", "mine", "mountain", "plains", "power-plant", "swamp", "tower", "urza’s",
            // planeswalker types
            "ajani", "aminatou", "angrath", "arlinn", "ashiok", "bolas", "chandra", "dack", "daretti", "davriel", "domri", "dovin", "elspeth", "estrid", "freyalise", "garruk", "gideon", "huatli", "jace", "jaya", "karn", "kasmina", "kaya", "kiora", "koth", "liliana", "nahiri", "narset", "nissa", "nixilis", "ral", "rowan", "saheeli", "samut", "sarkhan", "sorin", "tamiyo", "teferi", "teyo", "tezzeret", "tibalt", "ugin", "venser", "vivien", "vraska", "will", "windgrace", "xenagos", "yanggu", "yanling",
            // spell types
            "arcane", "trap",
            // creature types
            "advisor", "aetherborn", "ally", "angel", "antelope", "ape", "archer", "archon", "army", "artificer", "assassin", "assembly-worker", "atog", "aurochs", "avatar", "azra", "badger", "barbarian", "basilisk", "bat", "bear", "beast", "beeble", "berserker", "bird", "blinkmoth", "boar", "bringer", "brushwagg", "camarid", "camel", "caribou", "carrier", "cat", "centaur", "cephalid", "chimera", "citizen", "cleric", "cockatrice", "construct", "coward", "crab", "crocodile", "cyclops", "dauthi", "demon", "deserter", "devil", "dinosaur", "djinn", "dragon", "drake", "dreadnought", "drone", "druid", "dryad", "dwarf", "efreet", "egg", "elder", "eldrazi", "elemental", "elephant", "elf", "elk", "eye", "faerie", "ferret", "fish", "flagbearer", "fox", "frog", "fungus", "gargoyle", "germ", "giant", "gnome", "goat", "goblin", "god", "golem", "gorgon", "graveborn", "gremlin", "griffin", "hag", "harpy", "hellion", "hippo", "hippogriff", "homarid", "homunculus", "horror", "horse", "hound", "human", "hydra", "hyena", "illusion", "imp", "incarnation", "insect", "jackal", "jellyfish", "juggernaut", "kavu", "kirin", "kithkin", "knight", "kobold", "kor", "kraken", "lamia", "lammasu", "leech", "leviathan", "lhurgoyf", "licid", "lizard", "manticore", "masticore", "mercenary", "merfolk", "metathran", "minion", "minotaur", "mole", "monger", "mongoose", "monk", "monkey", "moonfolk", "mutant", "myr", "mystic", "naga", "nautilus", "nephilim", "nightmare", "nightstalker", "ninja", "noggle", "nomad", "nymph", "octopus", "ogre", "ooze", "orb", "orc", "orgg", "ouphe", "ox", "oyster", "pangolin", "pegasus", "pentavite", "pest", "phelddagrif", "phoenix", "pilot", "pincher", "pirate", "plant", "praetor", "prism", "processor", "rabbit", "rat", "rebel", "reflection", "rhino", "rigger", "rogue", "sable", "salamander", "samurai", "sand", "saproling", "satyr", "scarecrow", "scion", "scorpion", "scout", "serf", "serpent", "servo", "shade", "shaman", "shapeshifter", "sheep", "siren", "skeleton", "slith", "sliver", "slug", "snake", "soldier", "soltari", "spawn", "specter", "spellshaper", "sphinx", "spider", "spike", "spirit", "splinter", "sponge", "squid", "squirrel", "starfish", "surrakar", "survivor", "tetravite", "thalakos", "thopter", "thrull", "treefolk", "trilobite", "triskelavite", "troll", "turtle", "unicorn", "vampire", "vedalken", "viashino", "volver", "wall", "warrior", "weird", "werewolf", "whale", "wizard", "wolf", "wolverine", "wombat", "worm", "wraith", "wurm", "yeti", "zombie", "zubera",
            // planar types
            "alara", "arkhos", "azgol", "belenon", "bolas’s meditation realm", "dominaria", "equilor", "ergamon", "fabacin", "innistrad", "iquatana", "ir", "kaldheim", "kamigawa", "karsus", "kephalai", "kinshala", "kolbahan", "kyneth", "lorwyn", "luvion", "mercadia", "mirrodin", "moag", "mongseng", "muraganda", "new phyrexia", "phyrexia", "pyrulea", "rabiah", "rath", "ravnica", "regatha", "segovia", "serra’s realm", "shadowmoor", "shandalar", "ulgrotha", "valla", "vryn", "wildfire", "xerex", "zendikar"
    );
    // TODO add actual keywords
    public static final List<String> keywords = List.of("multicolor");
    public static final List<String> targets = List.of("player", "planeswalker", "permanent");

    public static final int MAX_MEANINGFUL_POWER = 7;
    public static final int MAX_MEANINGFUL_CMC = 6;

    public static final boolean canHaveAnyNumberOf(Card card) {
        return new MagicCardQualityPredicate("basic", "land").isSatisfied(card) || card.getText().contains("A deck can have any number of cards named " + card.getName());
    }

}
