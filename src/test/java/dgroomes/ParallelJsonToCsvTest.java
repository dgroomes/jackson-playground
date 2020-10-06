package dgroomes;

import dgroomes.point.PointRecord;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Deserialize JSON from a file and serialize to CSV. Executes in parallel.
 *
 * This is so slow... the parallelization has no effect. Why? What's the bottleneck? It takes around 90-100 seconds to
 * run this. But I can copy the raw JSON file in about 10 seconds.
 */
public class ParallelJsonToCsvTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(ParallelJsonToCsvTest.class);
    private static final String TEMP_JSON_FILE = "tmp/json_data";
    private static final String TEMP_CSV_FILE = "tmp/csv_data";
    private static final boolean PARALLEL = true;

    @Test
    public void jsonToCsv() throws IOException {
        var csvFile = new File(TEMP_CSV_FILE);
        if (csvFile.createNewFile()) {
            log.info("CSV file was created.");
        }
        var jsonFile = new File(TEMP_JSON_FILE);
        var csvSchema = csvMapper.schemaFor(PointRecord.class);
        var csvWriter = csvMapper.writer(csvSchema);

        final AtomicLong linesReadAndWritten = new AtomicLong(0);
        try (var jsonLines = Files.lines(jsonFile.toPath());
             var csvOutputUnbuffered = Files.newOutputStream(csvFile.toPath());
             var csvOutput = new BufferedOutputStream(csvOutputUnbuffered)) {

            log.info("Parallel enabled: {}", PARALLEL);
            Stream<String> jsonLinesStream = PARALLEL ? jsonLines.parallel() : jsonLines;

            jsonLinesStream
                    .forEach(line -> {
                        try {
                            var deserialized = mapper.readValue(line, PointRecord.class);
                            var csvBytes = csvWriter.writeValueAsBytes(deserialized);
                            csvOutput.write(csvBytes);
                            linesReadAndWritten.getAndIncrement();
                        } catch (IOException e) {
                            throw new IllegalStateException("Unexpected exception while reading, transforming and writing.", e);
                        }
                    });
        }

        log.info("Read {} lines from the large JSON temp file and wrote as many lines to the CSV file.", linesReadAndWritten.get());
        assertEquals(100_000_000, linesReadAndWritten.get());
    }
}
