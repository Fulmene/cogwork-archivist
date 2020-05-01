package mtgcogwork.magic.quality;

public class AbilityQuality extends MagicCardQualityType {

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitAbility(this);
    }

}
