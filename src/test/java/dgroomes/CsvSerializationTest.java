package dgroomes;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import dgroomes.point.PointPojo;
import dgroomes.point.PointRecord;
import dgroomes.point.PointWithGettersAndSetters;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Serializing to CSV
 * <p>
 * Referenced material:
 * * https://github.com/FasterXML/jackson-dataformats-text/tree/master/csv
 */
public class CsvSerializationTest extends BaseTest {

    String EXPECTED_CSV = "1,2\n";

    /**
     * Serialize a POJO (plain old Java object with public assignable (non-final) fields) to a CSV string
     */
    @Test
    void pojo() throws Exception {
        var point = new PointPojo();
        point.x = 1;
        point.y = 2;
        var schema = csvMapper.schemaFor(PointPojo.class);
        var writer = csvMapper.writer(schema);

        var serialized = writer.writeValueAsString(point);

        assertEquals(EXPECTED_CSV, serialized);
    }

    /**
     * Serialize an object with getters and setters to a CSV string
     */
    @Test
    void withGetters() throws Exception {
        var point = new PointWithGettersAndSetters();
        point.setX(1);
        point.setY(2);
        var schema = csvMapper.schemaFor(PointWithGettersAndSetters.class);
        var writer = csvMapper.writer(schema);

        var serialized = writer.writeValueAsString(point);

        assertEquals(EXPECTED_CSV, serialized);
    }

    /**
     * Serialize a record to a CSV string
     */
    @Test
    void record() throws Exception {
        var point = new PointRecord(1, 2);
        var schema = csvMapper.schemaFor(PointRecord.class);
        var writer = csvMapper.writer(schema);

        var serialized = writer.writeValueAsString(point);

        assertEquals(EXPECTED_CSV, serialized);
    }

    /**
     * Serialize a map to a CSV string
     */
    @Test
    void map() throws Exception {
        Map<String, Integer> point;
        ObjectWriter writer;
        {
            point = new HashMap<>();
            point.put("x", 1);
            point.put("y", 2);
            var schema = CsvSchema.builder()
                    .addNumberColumn("x")
                    .addNumberColumn("y")
                    .build();
            writer = csvMapper.writer(schema);
        }

        var serialized = writer.writeValueAsString(point);

        assertEquals(EXPECTED_CSV, serialized);
    }

    /**
     * Serialize multiple entries to a CSV string. In other words, the CSV should have multiple rows.
     */
    @Test
    void multipleEntries() throws Exception {
        List<Map<String, Integer>> points;
        ObjectWriter writer;
        {
            var schema = CsvSchema.builder()
                    .addNumberColumn("x")
                    .addNumberColumn("y")
                    .build();
            writer = csvMapper.writer(schema);
        }
        {
            points = List.of(
                    Map.of("x", 1, "y", 2),
                    Map.of("x", 3, "y", 4));
        }

        var serialized = writer.writeValueAsString(points);

        assertEquals("""
                1,2
                3,4
                """, serialized);
    }
}
