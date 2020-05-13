package mtgcogwork.magic.quality;

@SuppressWarnings("serial")
public class QualityFormatException extends RuntimeException {

    public QualityFormatException(String msg) {
        super("Unknown quality " + msg);
    }

}
