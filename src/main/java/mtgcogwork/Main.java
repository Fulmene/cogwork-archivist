package mtgcogwork;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import mtgcogwork.deckgenerator.DeckGenerator;
import mtgcogwork.magic.CardPool;
import mtgcogwork.magic.Deck;
import mtgcogwork.util.MtgJsonUtil;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Checking for update...");
        if (MtgJsonUtil.checkMtgJsonUpdate()) {
            System.out.println("Downloading MTGJson files...");
            MtgJsonUtil.updateMtgJsonFiles();
        } else {
            System.out.println("Everything up to date!");
        }

        List<String> sets = List.of("XLN", "RIX", "DOM", "M19", "GRN");
        List<Integer> manaCurve = List.of(0, 12, 12, 11, 3);
        List<Integer> cardTypeList = List.of(24, 20, 16);

        CardPool cardPool = CardPool.loadConstructedCardPool(sets);

        DeckGenerator generator = DeckGenerator.builder(cardPool).
            withCostEffectivenessMetric().
            withSynergyMetric().
            withManaCurveMetric(manaCurve).
            withCardTypeMetric(cardTypeList).
            build();
        Deck deck = generator.generateDeck(
            "Wizard's Lightning",
            "Wizard's Lightning",
            "Wizard's Lightning",
            "Wizard's Lightning");

        System.out.println(deck);

        Path resultDirectory = Paths.get("result/");
        Files.createDirectories(resultDirectory);
        Files.writeString(resultDirectory.resolve(LocalDateTime.now() + ".txt"), deck.toString());
    }

}
