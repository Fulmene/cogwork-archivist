package mtgcogwork.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.util.List;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public final class FileUtil {

    public static void copyFileFromUrl(URL source, Path dest) throws IOException {
        try (ReadableByteChannel sourceChannel = Channels.newChannel(source.openStream()); FileOutputStream out = new FileOutputStream(dest.toFile())) {
            out.getChannel().transferFrom(sourceChannel, 0, Long.MAX_VALUE);
        }
    }

    public static MappingIterator<List<String>> readCsvToList(URL csvURL) throws IOException {
        CsvMapper mapper = new CsvMapper().enable(CsvParser.Feature.WRAP_AS_ARRAY);
        ObjectReader oReader = mapper.readerFor(List.class).with(CsvSchema.builder().setColumnSeparator(':').build());
        return oReader.readValues(csvURL);
    }

}
