package dgroomes;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import dgroomes.point.PointPojo;
import dgroomes.point.PointRecord;
import dgroomes.point.PointWithGettersAndSetters;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Deserializing CSV documents that include a header
 * <p>
 * Referenced material:
 * * https://github.com/FasterXML/jackson-dataformats-text/tree/master/csv
 */
public class CsvDeserializationTest extends BaseTest {

    String CSV = """
                x,y
                1,2
            """;

    @Test
    void pojo() throws Exception {
        var bootstrapSchema = CsvSchema.emptySchema().withHeader();
        var reader = csvMapper.readerFor(PointPojo.class).with(bootstrapSchema);

        var found = reader.readValue(CSV);

        var expected = new PointPojo();
        expected.x = 1;
        expected.y = 2;
        assertEquals(expected, found);
    }

    @Test
    void withGetters() throws Exception {
        var bootstrapSchema = CsvSchema.emptySchema().withHeader();
        var reader = csvMapper.readerFor(PointWithGettersAndSetters.class).with(bootstrapSchema);

        var found = reader.readValue(CSV);

        var expected = new PointWithGettersAndSetters();
        expected.setX(1);
        expected.setY(2);
        assertEquals(expected, found);
    }

    @Test
    void record() throws Exception {
        var bootstrapSchema = CsvSchema.emptySchema().withHeader();
        var reader = csvMapper.readerFor(PointRecord.class).with(bootstrapSchema);

        var found = reader.readValue(CSV);

        var expected = new PointRecord(1, 2);
        assertEquals(expected, found);
    }
}
