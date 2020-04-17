package mtgcogwork.deckgenerator.metric;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import mtgcogwork.magic.Card;
import mtgcogwork.magic.Deck;

public abstract class Metric {

    private final Logger log = LogManager.getLogger(this.getClass());

    protected Metric() {
    }

    public final double getMetricScore(Deck deck, Card card) {
        double rawScore = this.getRawMetricScore(deck, card);
        log.debug("        Total score: {}", rawScore);
        return rawScore;
    }

    public void preprocessDeck(Deck deck) {}

    protected abstract double getRawMetricScore(Deck deck, Card card);

}
