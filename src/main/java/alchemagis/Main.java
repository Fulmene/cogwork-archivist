package alchemagis;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import alchemagis.deckgenerator.DeckGenerator;
import alchemagis.magic.Card;
import alchemagis.magic.CardPool;
import alchemagis.magic.CardSet;
import alchemagis.magic.Deck;
import alchemagis.util.FileUtil;

public class Main {

    public static void main(String[] args) throws Exception {
        List<String> sets = List.of("XLN", "RIX", "DOM", "M19", "GRN");
        List<Integer> manaCurve = List.of(12, 12, 11, 3);
        List<Integer> cardTypeList = List.of(24, 20, 16);

        DeckGenerator generator = DeckGenerator.createDeckGenerator(sets, manaCurve, cardTypeList);
        Deck deck = generator.generateDeck(
            "Wizard's Lightning",
            "Wizard's Lightning",
            "Wizard's Lightning",
            "Wizard's Lightning");

        new File("result/").mkdirs();
        Files.write(Paths.get("result/" + LocalDateTime.now() + ".txt"), Collections.singletonList(deck.toString()));
    }

}
