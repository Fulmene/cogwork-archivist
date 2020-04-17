package mtgcogwork.util;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.List;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import mtgcogwork.magic.CardSet;

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

    public static MappingIterator<List<String>> readCsv(URL csvURL) {
        CsvMapper mapper = new CsvMapper().enable(CsvParser.Feature.WRAP_AS_ARRAY);
        ObjectReader oReader = mapper.readerFor(List.class).with(CsvSchema.builder().setColumnSeparator(':').build());

        try {
            return oReader.readValues(csvURL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
