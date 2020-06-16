package mtgcogwork.deckgenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mtgcogwork.deckgenerator.metric.CardTypeMetric;
import mtgcogwork.deckgenerator.metric.CostEffectivenessMetric;
import mtgcogwork.deckgenerator.metric.ManaCurveMetric;
import mtgcogwork.deckgenerator.metric.Metric;
import mtgcogwork.deckgenerator.metric.SynergyMetric;
import mtgcogwork.magic.Card;
import mtgcogwork.magic.CardPool;
import mtgcogwork.magic.ConstructedCardPool;
import mtgcogwork.magic.Deck;
import mtgcogwork.magic.Format;

public final class DeckGenerator {

    private static final Logger log = LogManager.getLogger(DeckGenerator.class);

    public static final class Builder {

        private final CardPool cardPool;
        private final Format format;
        private List<Metric> metrics;

        private Builder(CardPool cardPool, Format format) {
            this.cardPool = cardPool;
            this.format = format;
            this.metrics = new ArrayList<>();
        }

        public final DeckGenerator build() {
            return new DeckGenerator(this);
        }

        public final Builder withCostEffectivenessMetric() {
            this.metrics.add(new CostEffectivenessMetric(this.cardPool));
            return this;
        }

        public final Builder withSynergyMetric() {
            this.metrics.add(new SynergyMetric(this.cardPool));
            return this;
        }

        public final Builder withManaCurveMetric(List<Integer> manaCurve) {
            this.metrics.add(new ManaCurveMetric(manaCurve));
            return this;
        }

        public final Builder withCardTypeMetric(List<Integer> cardTypeCount) {
            this.metrics.add(new CardTypeMetric(cardTypeCount));
            return this;
        }

    }

    public static final Builder builderConstructed(Collection<String> sets) throws IOException {
        return new Builder(ConstructedCardPool.load(sets), Format.CONSTRUCTED);
    }

    public static final Builder builderConstructed(CardPool cardPool) {
        return new Builder(cardPool, Format.CONSTRUCTED);
    }

    public static final Builder builderLimited(CardPool cardPool) {
        return new Builder(cardPool, Format.LIMITED);
    }

    private final CardPool cardPool;
    private final Format format;
    private final List<Metric> metrics;
    private final Map<String, Double> utilityScores;

    private DeckGenerator(Builder builder) {
        this(builder.cardPool, builder.format, builder.metrics);
    }

    private DeckGenerator(CardPool cardPool, Format format, List<Metric> metrics) {
        this.cardPool = cardPool;
        this.format = format;
        this.metrics = Collections.unmodifiableList(metrics);
        this.utilityScores = new HashMap<>();
    }

    public Deck generateDeck(String ...cardNames) {
        return this.generateDeck(List.of(cardNames));
    }

    public Deck generateDeck(List<String> cardNames) {
        return this.generateDeck(
            cardNames.stream().
                map(this.cardPool::getCard).
                collect(Collectors.toList()));
    }

    public Deck generateDeck(Iterable<Card> startingCards) {
        final Deck generatedDeck = new Deck(startingCards);

        log.debug("Start deck generation");
        log.debug("Starting cards: {}", startingCards);

        while (generatedDeck.size() < this.format.minDeckSize) {
            log.debug("Card #{}", generatedDeck.size()+1);
            this.utilityScores.clear();
            for (Metric m : metrics)
                m.preprocessDeck(generatedDeck);
            Card maxUtilityCard = cardPool.getCards().stream().
                filter(c ->
                    generatedDeck.count(c) < cardPool.count(c)).
                max((c1, c2) ->
                    Double.compare(
                        this.getUtilityScore(generatedDeck, c1),
                        this.getUtilityScore(generatedDeck, c2))).
                get();
            generatedDeck.add(maxUtilityCard);
            log.debug("Added {}", maxUtilityCard);
        }

        return generatedDeck;
    }

    private double getUtilityScore(Deck deck, Card card) {
        if (!this.utilityScores.containsKey(card.getName())) {
            log.debug("    Calculating utility score for {}", card);
            double score = this.metrics.stream().
                mapToDouble(m -> m.getMetricScore(deck, card)).
                sum();
            this.utilityScores.put(card.getName(), score);
            log.debug("    Utility score for {}: {}", card, score);
        }
        return this.utilityScores.get(card.getName());
    }

}
