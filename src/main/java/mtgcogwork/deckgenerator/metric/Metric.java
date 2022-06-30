package mtgcogwork.deckgenerator.metric;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import mtgcogwork.magic.Card;
import mtgcogwork.magic.Deck;

public abstract class Metric {

    //private final Logger log = LogManager.getLogger(this.getClass());

    protected final double metricWeight;

    protected Metric() {
        this(1.0);
    }

    protected Metric(double metricWeight) {
        this.metricWeight = metricWeight;
    }

    public final double getMetricScore(Deck deck, Card card) {
        double rawScore = this.getRawMetricScore(deck, card);
        //log.debug("        Total score: {}", rawScore);
        return this.metricWeight*rawScore;
    }

    public void preprocessDeck(Deck deck) {}

    protected abstract double getRawMetricScore(Deck deck, Card card);

}
