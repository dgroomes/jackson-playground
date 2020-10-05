package dgroomes;

import dgroomes.point.PointPojo;
import dgroomes.point.PointRecord;
import dgroomes.point.PointWithGettersAndSetters;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Serializing to CSV
 *
 * Referenced material:
 *   * https://github.com/FasterXML/jackson-dataformats-text/tree/master/csv
 */
public class CsvSerializationTest extends BaseTest {

    String EXPECTED_CSV = "1,2\n";

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

    @Test
    void record() throws Exception {
        var point = new PointRecord(1, 2);
        var schema = csvMapper.schemaFor(PointRecord.class);
        var writer = csvMapper.writer(schema);

        var serialized = writer.writeValueAsString(point);

        assertEquals(EXPECTED_CSV, serialized);
    }
}
