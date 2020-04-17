package mtgcogwork.deckgenerator.metric.synergy;

import mtgcogwork.magic.MagicConstants;
import mtgcogwork.magic.quality.ColorQuality;
import mtgcogwork.magic.quality.TypeQuality;

public abstract class QualitySynergy extends BaseSynergy {

    public static final QualitySynergy createQualitySynergy(String quality) {
        if (MagicConstants.colors.contains(quality))
            return new ColorSynergy(quality);
        else if (MagicConstants.supertypes.contains(quality) || MagicConstants.types.contains(quality) || MagicConstants.subtypes.contains(quality))
            return new TypeSynergy(quality);
        else {
            switch (quality) {
                case "multicolored":
                    return new QualitySynergy() {
                        @Override
                        public Boolean visitColor(ColorQuality color) {
                            return color.isMulticolored();
                        }
                    };
                case "permanent":
                    return new QualitySynergy() {
                        @Override
                        public Boolean visitType(TypeQuality type) {
                            return type.isPermanent();
                        }
                    };
                case "historic":
                    return new QualitySynergy() {
                        @Override
                        public Boolean visitType(TypeQuality type) {
                            return type.isType("legendary") || type.isType("artifact") || type.isType("saga");
                        }
                    };
                default: throw new SynergyFormatException(quality);
            }
        }
    }

    protected final String quality;

    protected QualitySynergy() {
        this.quality = "";
    }

    protected QualitySynergy(String quality) {
        this.quality = quality;
    }

    private static final class ColorSynergy extends QualitySynergy {

        private ColorSynergy(String color) {
            super(color);
        }

        @Override
        public Boolean visitColor(ColorQuality color) {
            return color.isColor(this.quality);
        }

    }

    private static final class TypeSynergy extends QualitySynergy {

        private TypeSynergy(String type) {
            super(type);
        }

        @Override
        public Boolean visitType(TypeQuality type) {
            return type.isType(this.quality);
        }

    }

}
