package alchemagis.magic.quality;

import java.util.stream.Collectors;
import java.util.List;

import alchemagis.magic.Card;

public final class MagicCardQuality {

    private final List<MagicCardQualityType> qualities;

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
