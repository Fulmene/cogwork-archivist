package alchemagis.util;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import alchemagis.magic.CardSet;

public final class FileUtil {

    public static CardSet readMtgJsonSet(URL setURL) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(setURL, CardSet.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final CsvSchema schema = CsvSchema.builder().
        addColumn("Card Name").
        addColumn("Cost Effectiveness").
        addColumn("Synergies").
        setColumnSeparator(':').
        build();

    public static MappingIterator<Map<String, String>> readCsv(URL csvURL) {
        CsvMapper mapper = new CsvMapper();
        ObjectReader oReader = mapper.readerFor(Map.class).with(schema);

        try {
            return oReader.readValues(csvURL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
