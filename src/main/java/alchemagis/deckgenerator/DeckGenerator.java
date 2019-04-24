package alchemagis.deckgenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alchemagis.deckgenerator.metric.Metric;
import alchemagis.magic.Card;
import alchemagis.magic.CardPool;
import alchemagis.magic.Deck;
import alchemagis.magic.MagicConstants;

public final class DeckGenerator {

    private CardPool cardPool;
    private List<Metric> metrics;
    private Map<String, Double> utilityScores;

    public DeckGenerator(CardPool cardPool, List<Metric> metrics) {
        this.cardPool = cardPool;
        this.metrics = metrics;
        this.utilityScores = new HashMap<>();
    }

    public Deck generateDeck() {
        return this.generateDeck(List.of(this.cardPool.getRandomCard()));
    }

    public Deck generateDeck(List<Card> startingCards) {
        final Deck generatedDeck = new Deck(startingCards);

        while (generatedDeck.size() < MagicConstants.MIN_DECK_SIZE) {
            this.utilityScores.clear();
            Card maxUtilityCard = cardPool.stream().max(
                (c1, c2) ->
                    Double.compare(
                        this.getUtilityScore(generatedDeck, c1),
                        this.getUtilityScore(generatedDeck, c2))).
            get();
            generatedDeck.add(maxUtilityCard);
        }

        return generatedDeck;
    }

    private double getUtilityScore(Deck deck, Card card) {
        if (!this.utilityScores.containsKey(card.getName())) {
            this.utilityScores.put(
                card.getName(),
                this.metrics.stream().
                    mapToDouble(m -> m.getMetricScore(deck, card)).
                    sum());
        }
        return this.utilityScores.get(card.getName());
    }

}
