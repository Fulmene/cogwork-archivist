package mtgcogwork.deckgenerator.metric.synergy;

import mtgcogwork.magic.MagicConstants;
import mtgcogwork.magic.quality.TypeQuality;

public class TypeSynergy extends BaseSynergy {

    private String type;

    public static TypeSynergy get(String keyword) {
        if (MagicConstants.supertypes.contains(keyword) || MagicConstants.types.contains(keyword) || MagicConstants.subtypes.contains(keyword))
            return new TypeSynergy(keyword);
        switch (keyword) {
            case "permanent":
                return new TypeSynergy(keyword) {
                    @Override
                    public Boolean visitType(TypeQuality type) {
                        return type.isPermanent();
                    }
                };
            case "historic":
                return new TypeSynergy(keyword) {
                    @Override
                    public Boolean visitType(TypeQuality type) {
                        return type.isType("legendary") || type.isType("artifact") || type.isType("saga");
                    }
                };
            default: throw new SynergyFormatException("type(" + keyword + ")");
        }
    }

    private TypeSynergy(String type) {
        this.type = type;
    }

    @Override
    public Boolean visitType(TypeQuality type) {
        return type.isType(this.type);
    }

}

