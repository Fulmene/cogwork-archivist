package alchemagis;

import java.io.File;
import java.lang.Exception;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import alchemagis.magic.Card;
import alchemagis.magic.CardPool;
import alchemagis.magic.CardSet;
import alchemagis.util.FileUtil;

public class Main {

    public static void main(String[] args) throws Exception {
        File json = new File("/home/adelaide/Downloads/AllSetFiles/GRN.json").getAbsoluteFile();
        File json2 = new File("/home/adelaide/Downloads/AllSetFiles/RNA.json").getAbsoluteFile();
        CardSet set = FileUtil.readMtgJsonSetFile(json);
        CardSet set2 = FileUtil.readMtgJsonSetFile(json2);
        CardPool pool = new CardPool(set, set2);
        System.out.println();
        for (Card card : pool.getCards()) {
            System.out.println(card.getName() + " " + card.getManaCost());
            for (String t : card.getSupertypes())
                System.out.print(t + " ");
            for (String t : card.getTypes())
                System.out.print(t + " ");
            System.out.print("- ");
            for (String t : card.getSubtypes())
                System.out.print(t + " ");
            System.out.println();
            System.out.println(card.getText());
            System.out.println(card.getPower() + "/" + card.getToughness());
            System.out.println();
        }
    }

}
