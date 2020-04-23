package mtgcogwork.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import mtgcogwork.magic.CardSet;

public class MtgJsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static final Path MTGJSON_DIR = Paths.get("mtgjson/");

    private static final URL mtgJsonUrl;
    static {
        try {
            mtgJsonUrl = new URL("https://www.mtgjson.com/files/");
        } catch (MalformedURLException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    private static final String versionFileName = "version.json";
    private static final List<String> targetFiles = List.of("version.json", "AllPrintings.json", "AllCards.json", "AllSetFiles.zip");

    public static boolean checkMtgJsonUpdate() throws IOException {
        Path versionPath = MTGJSON_DIR.resolve(versionFileName);
        if (Files.notExists(versionPath))
            return true;
        String latestDate = mapper.readTree(new URL(mtgJsonUrl, versionFileName)).get("date").asText();
        String currentDate = mapper.readTree(versionPath.toFile()).get("date").asText();
        return latestDate.compareTo(currentDate) > 0;
    }

    public static void updateMtgJsonFiles() throws IOException {
        Files.createDirectories(MTGJSON_DIR);
        for (String filename : targetFiles) {
            FileUtil.copyFileFromUrl(new URL(mtgJsonUrl, filename), MTGJSON_DIR.resolve(filename));
        }
    }

    public static List<CardSet> readMtgJsonSet(String... setCodes) throws IOException {
        return readMtgJsonSet(List.of(setCodes));
    }

    public static List<CardSet> readMtgJsonSet(Collection<String> setCodes) throws IOException {
        try (ZipFile setFiles = new ZipFile(MTGJSON_DIR.resolve("AllSetFiles.zip").toFile(), ZipFile.OPEN_READ)) {
            List<CardSet> sets = new ArrayList<>();
            for (String setCode : setCodes) {
                InputStream in = setFiles.getInputStream(setFiles.getEntry(setCode + ".json"));
                CardSet set = mapper.readValue(in, CardSet.class);
                sets.add(set);
            }
            return Collections.unmodifiableList(sets);
        }
    }

    @Deprecated
    public static CardSet readMtgJsonSet(URL setURL) {
        try {
            return mapper.readValue(setURL, CardSet.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
