package dgroomes;

import dgroomes.point.PointPojo;
import dgroomes.point.PointRecord;
import dgroomes.point.PointWithGettersAndSetters;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Serializing various Java types to a JSON document
 */
public class PointSerializationTest extends BaseTest {

    String expectedJson = """
            {"x":1,"y":2}""";

    @Test
    void pojo() throws Exception {
        var point = new PointPojo();
        point.x = 1;
        point.y = 2;

        var serialized = mapper.writeValueAsString(point);

        assertEquals(expectedJson, serialized);
    }

    @Test
    void withGetters() throws Exception {
        var point = new PointWithGettersAndSetters();
        point.setX(1);
        point.setY(2);

        var serialized = mapper.writeValueAsString(point);

        assertEquals(expectedJson, serialized);
    }

    /**
     * This does not work! Why? I'm not really sure... see https://github.com/FasterXML/jackson-future-ideas/issues/46
     * Adding `@JsonAutoDetect` annotation should fix it but I'm not sure why.
     */
    @Disabled
    @Test
    void record() throws Exception {
        var point = new PointRecord(1, 2);

        var serialized = mapper.writeValueAsString(point);

        assertEquals(expectedJson, serialized);
    }
}
