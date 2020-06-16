package mtgcogwork.magic;

public enum Format {

    CONSTRUCTED(60), LIMITED(40);

    public final int minDeckSize;

    private Format(int minDeckSize) {
        this.minDeckSize = minDeckSize;
    }

}
