package alchemagis.magic.quality;

public abstract class MagicCardQualityType {

    public abstract void accept(Visitor v);

    public static interface Visitor {
        default void visitStat(StatQuality stat) {}
    }

}
