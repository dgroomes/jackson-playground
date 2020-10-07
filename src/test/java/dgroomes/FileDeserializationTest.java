package dgroomes;

import com.fasterxml.jackson.core.JsonProcessingException;
import dgroomes.point.PointRecord;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Deserialize from a file
 */
public class FileDeserializationTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(FileDeserializationTest.class);
    private static final String TEMP_FILE = "tmp/json_data";

    /**
     * Read from a file and deserialize each JSON line into a Java object.
     */
    @Test
    public void fromFile() throws IOException {
        var file = new File(TEMP_FILE);
        log.info("Reading from the large JSON temp file");
        int linesRead = 0;
        String line;
        PointRecord point = null;
        try (var reader = Files.newBufferedReader(file.toPath())) {
            while ((line = reader.readLine()) != null) {
                linesRead++;
                point = mapper.readValue(line, PointRecord.class);
            }
        }
        log.info("Read {} lines from the large JSON temp file", linesRead);
        log.info("The final row was deserialized to an instance of FieldAB equal to {}", point);
        assertEquals(100_000_000, linesRead);
        var expected = new PointRecord(100_000_000, 0);
        assertEquals(expected, point);
    }

    /**
     * Like fromFile but with Java 8 Streams
     * <p>
     * When we pop out of our familiar "for loop-based" programming model into the "Java 8 Stream" programming model I
     * suddenly can't easily compute both a count of the number of lines read *and* grab the last line. I can easily get
     * the count with the "count" method but this is a terminal operation and that means it's the only computation I can
     * do on the stream instance. Could I implement my own "Collector" that computed both the total count and grapped
     * the last line?
     */
    @Test
    public void streamFromFile() throws IOException {
        log.info("Reading from the large JSON temp file");
        var file = new File(TEMP_FILE);

        long linesRead = 0;
        try (var lines = Files.lines(file.toPath())) {
            linesRead = lines.map(line -> {
                try {
                    return mapper.readValue(line, PointRecord.class);
                } catch (JsonProcessingException e) {
                    throw new IllegalStateException(e);
                }
            }).count();
        }

        log.info("Read {} lines from the large JSON temp file", linesRead);
        assertEquals(100_000_000, linesRead);
    }

    /**
     * Just like streamFromFile but parallelizes the JSON deserialization in the "map" operation using "parallel()". On
     * my computer, the parallelization makes the program about twice as fast.
     */
    @Test
    public void streamFromFile_parallel() throws IOException {
        log.info("Reading from the large JSON temp file");
        var file = new File(TEMP_FILE);

        var csvSchema = csvMapper.schemaFor(PointRecord.class);
        var csvWriter = csvMapper.writer(csvSchema);

        long linesRead = 0;
        try (var lines = Files.lines(file.toPath())) {
            linesRead = lines.parallel().map(line -> {
                try {
                    var point = mapper.readValue(line, PointRecord.class);
                    return csvWriter.writeValueAsBytes(point);
                } catch (JsonProcessingException e) {
                    throw new IllegalStateException(e);
                }
            }).count();
        }

        log.info("Read {} lines from the large JSON temp file", linesRead);
        assertEquals(100_000_000, linesRead);
    }
}
