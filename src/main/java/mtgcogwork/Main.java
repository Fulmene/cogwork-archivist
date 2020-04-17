package mtgcogwork;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import mtgcogwork.deckgenerator.DeckGenerator;
import mtgcogwork.magic.Deck;

public class Main {

    public static void main(String[] args) throws Exception {
        List<String> sets = List.of("XLN", "RIX", "DOM", "M19", "GRN");
        List<Integer> manaCurve = List.of(0, 12, 12, 11, 3);
        List<Integer> cardTypeList = List.of(24, 20, 16);

        DeckGenerator generator = DeckGenerator.createDeckGenerator(sets, manaCurve, cardTypeList);
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
