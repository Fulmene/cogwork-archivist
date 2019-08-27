package alchemagis.deckgenerator;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import alchemagis.deckgenerator.metric.Metric;
import alchemagis.deckgenerator.metric.CardTypeMetric;
import alchemagis.deckgenerator.metric.CostEffectivenessMetric;
import alchemagis.deckgenerator.metric.ManaCurveMetric;
import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;
import alchemagis.magic.CardPool;
import alchemagis.magic.Deck;
import alchemagis.magic.MagicConstants;

public final class DeckGenerator {

    private CardPool cardPool;
    private List<Metric> metrics;
    private Map<String, Double> utilityScores;

    public static DeckGenerator createDeckGenerator(Collection<String> sets, List<Integer> manaCurve, List<Integer> cardTypeList) {
        CardPool cardPool = CardPool.loadCardPool(sets);
        Metric costEffectivenessMetric = new CostEffectivenessMetric(sets);
        Metric synergyMetric = new SynergyMetric(sets);
        Metric manaCurveMetric = new ManaCurveMetric(manaCurve);
        Metric cardTypeMetric = new CardTypeMetric(cardTypeList);

        return new DeckGenerator(
            cardPool,
            List.of(
                costEffectivenessMetric,
                synergyMetric,
                manaCurveMetric,
                cardTypeMetric));
    }

    public DeckGenerator(CardPool cardPool, List<Metric> metrics) {
        this.cardPool = cardPool;
        this.metrics = metrics;
        this.utilityScores = new HashMap<>();
    }

    public Deck generateDeck() {
        return this.generateDeck(List.of(this.cardPool.getRandomCard()));
    }

    public Deck generateDeck(String ...cardNames) {
        return this.generateDeck(
            Arrays.stream(cardNames).
                map(this.cardPool::getCard).
                collect(Collectors.toList()));
    }

    public Deck generateDeck(Iterable<Card> startingCards) {
        final Deck generatedDeck = new Deck(startingCards);

        while (generatedDeck.size() < MagicConstants.MIN_DECK_SIZE) {
            this.utilityScores.clear();
            this.metrics.stream().forEach(m -> m.preprocessDeck(generatedDeck));
            Card maxUtilityCard = cardPool.stream().
                filter(c ->
                    generatedDeck.count(c) < MagicConstants.MAX_COPIES ||
                    MagicConstants.canHaveAnyNumberOf(c)).
                max((c1, c2) ->
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
