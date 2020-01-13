package dgroomes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import dgroomes.point.PointPojo;
import dgroomes.point.PointWithConstructor;
import dgroomes.point.PointWithConstructorWithJsonPropertyAnnotations;
import dgroomes.point.PointWithSetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

public class Main {

    private static Logger log = LoggerFactory.getLogger(Main.class);

    public ObjectMapper mapper = new ObjectMapper();
    public ObjectMapper mapperParamNames = new ObjectMapper().registerModule(new ParameterNamesModule());
    public String json =
            """
            {
               "x": 1,
               "y": 2
            }
            """;

    public static void main(String[] args) throws JsonProcessingException {
        var main = new Main();
        main.extract();
    }

    /**
     * Illustrate various combinations of Java classes, Jackson annotations and Jackson configurations that can extract
     * the "x" and "y" fields from a JSON payload
     */
    public void extract() throws JsonProcessingException {
        extract(PointPojo.class, it -> it.x, it -> it.y, mapper);
        extract(PointWithSetter.class, it -> it.getX(), it -> it.getY(), mapper);
        extract(PointWithConstructor.class, it -> it.getX(), it -> it.getY(), mapperParamNames);
        extract(PointWithConstructorWithJsonPropertyAnnotations.class, it -> it.getX(), it -> it.getY(), mapper);
    }

    /**
     * Use the ObjectMapper to deserialize a JSON payload with "x" and "y" fields. Prints the extracted data.
     */
    public <T> void extract(Class<T> clazz, Function<T, Integer> getX, Function<T, Integer> getY, ObjectMapper objectMapper) throws JsonProcessingException {
        var extracted = objectMapper.readValue(json, clazz);
        var x = getX.apply(extracted);
        var y = getY.apply(extracted);

        log.info("For class={} and modules={} extracted x={} y={}", clazz, objectMapper.getRegisteredModuleIds(), x, y);
    }
}
