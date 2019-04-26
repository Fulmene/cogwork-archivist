package alchemagis.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.RuntimeException;
import java.util.Map;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public final class FileUtil {

    public static MappingIterator<Map<String, String>> readCsvFile(File csvFile) {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        ObjectReader oReader = mapper.readerFor(Map.class).with(schema);

        try (Reader reader = new FileReader(csvFile)) {
            return oReader.readValues(reader);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
