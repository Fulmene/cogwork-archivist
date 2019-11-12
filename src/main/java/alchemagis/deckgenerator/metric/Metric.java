package alchemagis.deckgenerator.metric;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import alchemagis.magic.Card;
import alchemagis.magic.Deck;

public abstract class Metric {

    private final Logger log = LogManager.getLogger(this.getClass());

    protected double metricWeight;

    protected Metric() {
        this.metricWeight = 1.0;
    }

    protected Metric(double metricWeight) {
        this.metricWeight = metricWeight;
    }

    public final double getMetricScore(Deck deck, Card card) {
        double rawScore = this.getRawMetricScore(deck, card);
        double score = this.metricWeight * rawScore;
        log.debug("        Total score: {}", score);
        return score;
    }

    public void preprocessDeck(Deck deck) {}

    protected abstract double getRawMetricScore(Deck deck, Card card);

}
