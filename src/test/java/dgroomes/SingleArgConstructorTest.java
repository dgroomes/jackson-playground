package dgroomes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import dgroomes.singleargconstructor.SingleArgConstructor;
import dgroomes.singleargconstructor.SingleArgConstructorWithJsonProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SingleArgConstructorTest {

    ObjectMapper mapper = new ObjectMapper();
    ObjectMapper mapperParamNames = new ObjectMapper().registerModule(new ParameterNamesModule());
    String json =
        """
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
