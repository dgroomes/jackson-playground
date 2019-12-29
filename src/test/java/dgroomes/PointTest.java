package dgroomes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import dgroomes.point.PointPojo;
import dgroomes.point.PointRecord;
import dgroomes.point.PointWithConstructor;
import dgroomes.point.PointWithConstructorWithJsonPropertyAnnotations;
import dgroomes.point.PointWithSetter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointTest {

    ObjectMapper mapper = new ObjectMapper();
    ObjectMapper mapperParamNames = new ObjectMapper().registerModule(new ParameterNamesModule());
    String json =
            """
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
