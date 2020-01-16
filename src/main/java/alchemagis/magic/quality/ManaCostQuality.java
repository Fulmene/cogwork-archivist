package alchemagis.magic.quality;

import java.util.List;

public final class ManaCostQuality extends MagicCardQualityType {

    private final List<String> manaCost;
    private final int convertedManaCost;

    public ManaCostQuality(String manaCostString) {
        List<String> manaCost = List.of(manaCostString.substring(1, manaCostString.length()-1).split("\\}\\{"));
        this.manaCost = manaCost;
        this.convertedManaCost = getConvertedManaCost(manaCost);
    }

    public <T> T accept(MagicCardQualityType.Visitor<T> visitor) {
        return visitor.visitManaCost(this);
    }

    public List<String> getManaCost() {
        return this.manaCost;
    }

    public int getConvertedManaCost() {
        return this.convertedManaCost;
    }

    public static final int getConvertedManaCost(List<String> manaCost) {
        return manaCost.stream().mapToInt(s -> {
                String[] manaSymbols = s.split("/");
                if (manaSymbols[0].matches("[0-9]+"))
                    return Integer.parseInt(manaSymbols[0]);
                else
                    return 1;
            }).
            sum();
    }

}
