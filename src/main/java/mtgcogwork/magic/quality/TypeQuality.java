package mtgcogwork.magic.quality;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class TypeQuality extends MagicCardQuality {

    public static final List<String> SUPERTYPES = List.of("basic", "legendary", "ongoing", "snow", "world");
    public static final List<String> TYPES = List.of("artifact", "conspiracy", "creature", "enchantment", "instant", "land", "phenomenon", "plane", "planeswalker", "scheme", "sorcery", "tribal", "vanguard");
    public static final List<String> SUBTYPES = List.of(
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
    public static final List<String> PERMANENT_TYPES = List.of("artifact", "creature", "enchantment", "land", "planeswalker");

    private final List<String> types;

    public TypeQuality(String... types) {
        this.types = List.of(types);
    }

    public TypeQuality(List<String> types) {
        this.types = Collections.unmodifiableList(types);
    }

    @SafeVarargs
    public TypeQuality(List<String>... types) {
        this.types = Stream.of(types).flatMap(List::stream).collect(Collectors.toList());
    }

    public List<String> getTypes() {
        return this.types;
    }

    public boolean isType(String type) {
        return this.types.contains(type);
    }

    public <T> T accept(MagicCardQuality.Visitor<T> visitor) {
        return visitor.visitType(this);
    }

    @Override
    public String toString() {
        return "type(" + String.join(",", this.types) + ")";
    }

}
