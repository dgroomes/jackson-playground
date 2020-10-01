package dgroomes;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import dgroomes.singleargconstructor.SingleArgConstructor;
import dgroomes.singleargconstructor.SingleArgConstructorWithJsonProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Deserializing a JSON document for a target Java type that has a single-arg constructor. Single-arg constructors are a
 * special case for Jackson.
 */
class SingleArgConstructorTest extends BaseTest {

    String json = """
            {
               "message": "hello"
            }
            """;

    @Test
    void withParameterNamesModule() {
        assertThrows(MismatchedInputException.class, () -> {
            mapperParamNames.readValue(json, SingleArgConstructor.class);
        });
    }

    @Test
    void withJsonProperty() throws Exception {
        var extracted = mapper.readValue(json, SingleArgConstructorWithJsonProperty.class);

        assertEquals("hello", extracted.getMessage());
    }
}
