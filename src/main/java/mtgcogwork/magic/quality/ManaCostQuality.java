package mtgcogwork.magic.quality;

import java.util.List;

public final class ManaCostQuality extends MagicCardQualityType {

    // TODO INSTANCES

    private final List<String> manaCost;
    private final int convertedManaCost;

    public ManaCostQuality(String manaCostString) {
        if (manaCostString != null) {
            List<String> manaCost = List.of(manaCostString.substring(1, manaCostString.length()-1).split("\\}\\{"));
            this.manaCost = manaCost;
            this.convertedManaCost = calculateCmc(manaCost);
        } else {
            this.manaCost = List.of();
            this.convertedManaCost = 0;
        }
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

    public boolean hasX() {
        return this.manaCost.contains("X");
    }

    public static final int calculateCmc(List<String> manaCost) {
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
