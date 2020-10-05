package dgroomes;

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

    @Test
    public void fromFile() throws IOException {
        record DummyType(String field_a, String field_b) {
        }

        var file = new File(TEMP_FILE);
        log.info("Reading from the large JSON temp file");
        int linesRead = 0;
        String line;
        DummyType dummyType = null;
        try (var reader = Files.newBufferedReader(file.toPath())) {
            while ((line = reader.readLine()) != null) {
                linesRead++;
                dummyType = mapper.readValue(line, DummyType.class);
            }
        }
        log.info("Read {} lines from the large JSON temp file", linesRead);
        log.info("The final row was deserialized to an instance of FieldAB equal to {}", dummyType);
        assertEquals(10_000_000, linesRead);
        var expected = new DummyType("9999999", "value_b");
        assertEquals(expected, dummyType);
    }
}
