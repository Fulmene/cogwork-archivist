package mtgcogwork.magic.quality;

import java.util.stream.Collectors;
import java.util.List;

import mtgcogwork.magic.Card;

public final class MagicCardQuality {

    public static final MagicCardQuality NONE = new MagicCardQuality();

    private final List<MagicCardQualityType> qualities;

    private MagicCardQuality() {
        this.qualities = List.of();
    }

    public MagicCardQuality(Card card) {
        this.qualities = List.of(
            new ColorQuality(card.getColors()),
            new ManaCostQuality(card.getManaCost()),
            new StatQuality(card.getPower(), card.getToughness()),
            new TypeQuality(card.getSupertypes(), card.getTypes(), card.getSubtypes())
        );
    }

    public final List<MagicCardQualityType> getQualities() {
        return this.qualities;
    }

}
