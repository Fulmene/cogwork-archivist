package mtgcogwork.magic.quality;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import mtgcogwork.magic.Card;

public final class MagicCardQualityList {

    public static final MagicCardQualityList NONE = new MagicCardQualityList();

    private final List<MagicCardQuality> qualities;

    private MagicCardQualityList() {
        this.qualities = List.of();
    }

    public MagicCardQualityList(List<MagicCardQuality> qualities) {
        this.qualities = Collections.unmodifiableList(qualities);
    }

    public MagicCardQualityList(Card card) {
        List<MagicCardQuality> qualities = getBasicQualityList(card);
        this.qualities = Collections.unmodifiableList(qualities);
    }

    public final List<MagicCardQuality> getQualities() {
        return this.qualities;
    }

    @Override
    public String toString() {
        return String.join("/", this.qualities.stream().map(q -> q.toString()).collect(Collectors.toUnmodifiableList()));
    }

    private static final List<MagicCardQuality> getBasicQualityList(Card card) {
        List<MagicCardQuality> qualities = new ArrayList<>();

        qualities.add(new ColorQuality(card.getColors()));

        qualities.add(new ManaCostQuality(card.getManaCost()));

        TypeQuality typeQuality = new TypeQuality(card.getSupertypes(), card.getTypes(), card.getSubtypes());
        qualities.add(typeQuality);

        if (typeQuality.isType("creature") || typeQuality.isType("vehicle"))
            qualities.add(new StatQuality(card.getPower(), card.getToughness()));

        return qualities;
    }

}
