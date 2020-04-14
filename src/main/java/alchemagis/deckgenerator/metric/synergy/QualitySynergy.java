package alchemagis.deckgenerator.metric.synergy;

import java.util.Map;
import java.util.HashMap;

import alchemagis.magic.MagicConstants;
import alchemagis.magic.quality.ColorQuality;
import alchemagis.magic.quality.TypeQuality;

public abstract class QualitySynergy extends BaseSynergy {

    private static final Map<String, QualitySynergy> INSTANCES = new HashMap<>();

    public static final QualitySynergy getInstance(String quality) {
        QualitySynergy target = INSTANCES.get(quality);
        if (target == null) {
            target = createQualitySynergy(quality);
            INSTANCES.put(quality, target);
        }
        return target;
    }

    private static final QualitySynergy createQualitySynergy(String quality) {
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
