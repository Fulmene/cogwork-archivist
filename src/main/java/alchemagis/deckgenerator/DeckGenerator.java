package alchemagis.deckgenerator;

import java.util.List;

import alchemagis.deckgenerator.metric.Metric;
import alchemagis.magic.Card;
import alchemagis.magic.Deck;
import alchemagis.magic.MagicConstants;

public final class DeckGenerator {

    private List<Metric> metrics;

    public Deck generateDeck() {
        return null;
    }

    public Deck generateDeck(List<Card> startingCards) {
        final Deck generatedDeck = new Deck(startingCards);

        while (generatedDeck.size() < MagicConstants.MIN_DECK_SIZE) {
            
        }

        return generatedDeck;
    }

    private double getUtilityScore(Deck deck, Card card) {
        return this.metrics.stream().
            mapToDouble(m -> m.getMetricScore(deck, card)).
            sum();
    }

}
