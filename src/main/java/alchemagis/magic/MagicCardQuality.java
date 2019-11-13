package alchemagis.magic;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public final class MagicCardQuality {

    private final List<String> colors;
    private final List<String> supertypes;
    private final List<String> types;
    private final List<String> subtypes;
    private final List<String> keywords;

    public static final MagicCardQuality ANY = new MagicCardQuality();

    private MagicCardQuality() {
        this.colors = List.of();
        this.supertypes = List.of();
        this.types = List.of();
        this.subtypes = List.of();
        this.keywords = List.of();
    }

    public MagicCardQuality(String... qualities) {
        this(Arrays.asList(qualities));
    }

    public MagicCardQuality(List<String> qualities) {
        this.colors = new ArrayList<>();
        this.supertypes = new ArrayList<>();
        this.types = new ArrayList<>();
        this.subtypes = new ArrayList<>();
        this.keywords = new ArrayList<>();

        if (!(qualities.size() == 1 && qualities.get(0).equals("any"))) {
            for (String q : qualities) {
                if (MagicConstants.colors.contains(q) || (q.startsWith("non") && MagicConstants.colors.contains(q.substring(3))))
                    this.colors.add(q);
                else if (MagicConstants.supertypes.contains(q) || (q.startsWith("non") && MagicConstants.supertypes.contains(q.substring(3))))
                    this.supertypes.add(q);
                else if (MagicConstants.types.contains(q) || (q.startsWith("non") && MagicConstants.types.contains(q.substring(3))))
                    this.types.add(q);
                else if (MagicConstants.subtypes.contains(q) || (q.startsWith("non") && MagicConstants.subtypes.contains(q.substring(3))))
                    this.subtypes.add(q);
                else if (MagicConstants.keywords.contains(q) || (q.startsWith("non") && MagicConstants.keywords.contains(q.substring(3))))
                    this.keywords.add(q);
                else
                    throw new IllegalQualityException(q);
            }
        }
    }

    public boolean isSatisfied(Card card) {
        boolean satisfied = true;
        for (String c : colors) {
            if (c.startsWith("non"))
                satisfied &= !card.getColors().contains(c.substring(3));
            else
                satisfied &= card.getColors().contains(c);
        }
        for (String t : supertypes)
            if (t.startsWith("non"))
                satisfied &= !card.getSupertypes().contains(t.substring(3));
            else
                satisfied &= card.getSupertypes().contains(t);
        for (String t : types)
            if (t.startsWith("non"))
                satisfied &= !card.getTypes().contains(t.substring(3));
            else
                satisfied &= card.getTypes().contains(t);
        for (String t : subtypes)
            if (t.startsWith("non"))
                satisfied &= !card.getSubtypes().contains(t.substring(3));
            else
                satisfied &= card.getSubtypes().contains(t);
        for (String k : keywords) {
            // TODO if k starts with non
            switch (k) {
                case "multicolor":
                    satisfied &= card.getColors().size() > 1;
                    break;
                default:
            }
        }

        return satisfied;
    }

    @SuppressWarnings("serial")
    public static class IllegalQualityException extends RuntimeException {
        private IllegalQualityException(String quality) {
            super("Unknown quality " + quality);
        }
    }

}
