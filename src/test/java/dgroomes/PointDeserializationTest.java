package dgroomes;

import dgroomes.point.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Deserializing a JSON document using various target Java types including a Record-based type ({@link PointRecord}.
 */
class PointDeserializationTest extends BaseTest {

    String json = """
            {
               "x": 1,
               "y": 2
            }
            """;

    @Test
    void pojo() throws Exception {
        var extracted = mapper.readValue(json, PointPojo.class);

        assertEquals(1, extracted.x);
    }

    @Test
    void withSetter() throws Exception {
        var extracted = mapper.readValue(json, PointWithSetter.class);

        assertEquals(1, extracted.getX());
    }

    @Test
    void withConstructor() throws Exception {
        var extracted = mapperParamNames.readValue(json, PointWithConstructor.class);

        assertEquals(1, extracted.getX());
    }

    @Test
    void withConstructorWithJsonPropertyAnnotations() throws Exception {
        var extracted = mapper.readValue(json, PointWithConstructorWithJsonPropertyAnnotations.class);

        assertEquals(1, extracted.getX());
    }

    @Test
    void record() throws Exception {
        var extracted = mapperParamNames.readValue(json, PointRecord.class);

        assertEquals(1, extracted.x());
    }
}
