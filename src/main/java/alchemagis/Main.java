package alchemagis;

import java.io.File;
import java.lang.Exception;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        System.out.println(deck);
    }

}
