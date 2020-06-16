package mtgcogwork.deckgenerator.metric.synergy;

import mtgcogwork.magic.MagicConstants;
import mtgcogwork.magic.quality.ColorQuality;

public class ColorSynergy extends BaseSynergy {

    private String color;

    public static ColorSynergy get(String keyword) {
        if (MagicConstants.colors.contains(keyword))
            return new ColorSynergy(keyword);
        switch (keyword) {
            case "colorless":
                return new ColorSynergy(keyword) {
                    @Override
                    public Boolean visitColor(ColorQuality color) {
                        return color.getColors().size() == 0;
                    }
                };
            case "monocolored":
                return new ColorSynergy(keyword) {
                    @Override
                    public Boolean visitColor(ColorQuality color) {
                        return color.getColors().size() == 1;
                    }
                };
            case "multicolored":
                return new ColorSynergy(keyword) {
                    @Override
                    public Boolean visitColor(ColorQuality color) {
                        return color.getColors().size() > 1;
                    }
                };
            default: throw new SynergyFormatException("color(" + keyword + ")");
        }
    }

    private ColorSynergy(String color) {
        this.color = color;
    }

    @Override
    public Boolean visitColor(ColorQuality color) {
        return color.isColor(this.color);
    }

}
