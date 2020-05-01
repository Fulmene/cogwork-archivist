package mtgcogwork.magic.quality;

import java.util.List;

import mtgcogwork.magic.Card;

public final class MagicCardQualityList {

    public static final MagicCardQualityList NONE = new MagicCardQualityList();

    private final List<MagicCardQuality> qualities;

    private MagicCardQualityList() {
        this.qualities = List.of();
    }

    public MagicCardQualityList(Card card) {
        this.qualities = List.of(
            new ColorQuality(card.getColors()),
            new ManaCostQuality(card.getManaCost()),
            new StatQuality(card.getPower(), card.getToughness()),
            new TypeQuality(card.getSupertypes(), card.getTypes(), card.getSubtypes())
        );
    }

    public final List<MagicCardQuality> getQualities() {
        return this.qualities;
    }

}
