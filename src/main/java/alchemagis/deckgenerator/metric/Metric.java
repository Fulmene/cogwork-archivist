package alchemagis.deckgenerator.metric;

import alchemagis.magic.Card;
import alchemagis.magic.Deck;

public abstract class Metric {

    protected double metricWeight;

    protected Metric() {
        this.metricWeight = 1.0;
    }

    protected Metric(double metricWeight) {
        this.metricWeight = metricWeight;
    }

    public final double getMetricScore(Deck deck, Card card) {
        return this.metricWeight * this.getRawMetricScore(deck, card);
    }

    public void preprocessDeck(Deck deck) {}

    protected abstract double getRawMetricScore(Deck deck, Card card);

}
