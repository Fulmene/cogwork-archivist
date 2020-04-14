package alchemagis.deckgenerator.metric.synergy;

@SuppressWarnings("serial")
public class SynergyFormatException extends RuntimeException {

    public SynergyFormatException(String msg) {
        super("Unknown synergy " + msg);
    }

}
