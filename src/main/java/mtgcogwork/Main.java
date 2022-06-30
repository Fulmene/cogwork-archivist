package mtgcogwork;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import mtgcogwork.deckgenerator.DeckGenerator;
import mtgcogwork.magic.CardPool;
import mtgcogwork.magic.ConstructedCardPool;
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

        CardPool cardPool = ConstructedCardPool.load(sets);

        Path resultDirectory = Paths.get("result/");
        Files.createDirectories(resultDirectory);

        String dateTime = LocalDateTime.now().toString();

        DeckGenerator generator = DeckGenerator.builderConstructed(cardPool).
            withCostEffectivenessMetric(200).
            withSynergyMetric(50).
            withManaCurveMetric(manaCurve, 2000).
            withCardTypeMetric(cardTypeList, 2000).
            build();
        Deck deck = generator.generateDeck(
                "Wizard's Lightning",
                "Wizard's Lightning",
                "Wizard's Lightning",
                "Wizard's Lightning");

        System.out.println(deck);

        /*
        try (var resultFile = new ZipOutputStream(new FileOutputStream(resultDirectory.resolve(dateTime + ".zip").toFile()))) {

            for (int costEffectivenessWeight = 0; costEffectivenessWeight <= 100; costEffectivenessWeight += 25) {
                for (int synergyWeight = 0; synergyWeight <= 100; synergyWeight += 25) {
                    for (int manaCurveWeight = 0; manaCurveWeight <= 100; manaCurveWeight += 25) {
                        for (int cardTypeWeight = 0; cardTypeWeight <= 100; cardTypeWeight += 25) {
                            DeckGenerator generator = DeckGenerator.builderConstructed(cardPool).
                                withCostEffectivenessMetric((double)costEffectivenessWeight/100).
                                withSynergyMetric((double)synergyWeight/100).
                                withManaCurveMetric(manaCurve, (double)manaCurveWeight/100).
                                withCardTypeMetric(cardTypeList, (double)cardTypeWeight/100).
                                build();
                            Deck deck = generator.generateDeck(
                                    "Wizard's Lightning",
                                    "Wizard's Lightning",
                                    "Wizard's Lightning",
                                    "Wizard's Lightning");

                            System.out.println(deck);

                            String fileName = String.format("%s_%03d_%03d_%03d_%03d.txt",
                                    dateTime,
                                    costEffectivenessWeight,
                                    synergyWeight,
                                    manaCurveWeight,
                                    cardTypeWeight);

                            ZipEntry e = new ZipEntry(fileName);
                            resultFile.putNextEntry(e);
                            resultFile.write(deck.toString().getBytes());
                            resultFile.closeEntry();

                            // Files.writeString(resultDirectory.resolve(fileName), deck.toString());
                        }
                    }
                }
            }

        }
        */
    }

}
