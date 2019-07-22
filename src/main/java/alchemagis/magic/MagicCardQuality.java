package alchemagis.magic;

import java.util.ArrayList;
import java.util.List;

public final class MagicCardQuality {

    private final List<String> colors;
    private final List<String> supertypes;
    private final List<String> types;
    private final List<String> subtypes;
    private final List<String> keywords;

    public static final MagicCardQuality ANY = new MagicCardQuality();

    public MagicCardQuality() {
        this.colors = new ArrayList<>();
        this.supertypes = new ArrayList<>();
        this.types = new ArrayList<>();
        this.subtypes = new ArrayList<>();
        this.keywords = new ArrayList<>();
    }

    public MagicCardQuality(String... qualities) throws IllegalQualityException {
        this.colors = new ArrayList<>();
        this.supertypes = new ArrayList<>();
        this.types = new ArrayList<>();
        this.subtypes = new ArrayList<>();
        this.keywords = new ArrayList<>();

        for (String q : qualities) {
            if (MagicConstants.colors.contains(q))
                this.colors.add(q);
            else if (MagicConstants.subtypes.contains(q))
                this.subtypes.add(q);
            else if (MagicConstants.types.contains(q))
                this.types.add(q);
            else if (MagicConstants.subtypes.contains(q))
                this.types.add(q);
            else if (MagicConstants.keywords.contains(q))
                this.keywords.add(q);
            else
                throw new IllegalQualityException(q);
        }
    }

    public boolean isSatisfied(Card card) {
        boolean satisfied = true;
        for (String c : colors)
            satisfied &= card.getColors().contains(c);
        for (String t : supertypes)
            satisfied &= card.getSupertypes().contains(t);
        for (String t : types)
            satisfied &= card.getTypes().contains(t);
        for (String t : subtypes)
            satisfied &= card.getSubtypes().contains(t);
        // TODO keywords

        return satisfied;
    }

    public static class IllegalQualityException extends Exception {

        public static final long serialVersionUID = 1l;
        private String quality;

        private IllegalQualityException(String quality) {
            this.quality = quality;
        }

    }

}
