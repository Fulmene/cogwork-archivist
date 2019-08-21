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
        String[] sets = {"XLN", "RIX", "DOM", "M19", "GRN"};

        DeckGenerator generator = DeckGenerator.createDeckGenerator(sets);
        Deck deck = generator.generateDeck();

        new File("result/").mkdirs();
        Files.write(Paths.get("result/" + LocalDateTime.now()), Collections.singletonList(deck.toString()));
    }

}
