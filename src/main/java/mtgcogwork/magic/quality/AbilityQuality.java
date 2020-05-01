package mtgcogwork.magic.quality;

public class AbilityQuality extends MagicCardQuality {

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitAbility(this);
    }

}
