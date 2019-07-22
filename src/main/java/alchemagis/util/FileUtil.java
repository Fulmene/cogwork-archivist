package alchemagis.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.RuntimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import alchemagis.magic.CardSet;

public final class FileUtil {

    public static CardSet readMtgJsonSetFile(File setFile) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(setFile, CardSet.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static MappingIterator<Map<String, String>> readCsvFile(File csvFile) {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        ObjectReader oReader = mapper.readerFor(Map.class).with(schema);

        try (Reader reader = new FileReader(csvFile)) {
            return oReader.readValues(reader);
        } catch (FileNotFoundException e1) {
            throw new RuntimeException(e1);
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

}
