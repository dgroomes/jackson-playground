package dgroomes;

import dgroomes.point.*;
import org.junit.jupiter.api.Disabled;
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

    String jsonMultiWordFields = """
            {
               "x_coordinate": 1,
               "y_coordinate": 2
            }
            """;

    @Test
    void pojo() throws Exception {
        var extracted = mapper.readValue(json, PointPojo.class);

        assertEquals(1, extracted.x);
    }

    @Test
    void pojoWithMultiWordFields() throws Exception {
        var extracted = mapperParamNames.readValue(jsonMultiWordFields, PointPojoWithMultiWordFields.class);

        assertEquals(1, extracted.xCoordinate);
    }

    @Test
    void withSetters() throws Exception {
        var extracted = mapper.readValue(json, PointWithGettersAndSetters.class);

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

    /**
     * The combination of a Java record and a multi-word snake case JSON field used to not work, but it works. Thanks
     * Jackson and open source! See <a href="https://github.com/FasterXML/jackson-databind/issues/3102">GitHub issue #3102</a>
     * which describes the same thing.
     */
    @Test
    void recordWithMultiWordFields() throws Exception {
        var extracted = mapperParamNames.readValue(jsonMultiWordFields, PointRecordWithMultiWordFields.class);

        assertEquals(1, extracted.xCoordinate());
    }
}
