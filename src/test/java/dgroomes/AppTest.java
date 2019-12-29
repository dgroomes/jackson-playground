package dgroomes;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    /**
     * Test harness
     */
    private <T extends Envelope> void execute(App app, Class<T> targetClass) throws Exception {
        String json = """
                { "message": "Hello from the tests!"}
                """;

        var msg = app.extractMessage(json, targetClass);

        assertEquals("Hello from the tests!", msg);
    }

    private <T extends Envelope> void execute(Class<T> targetClass) throws Exception {
        execute(new App(), targetClass);
    }

    @Test
    void withSetter() throws Exception {
        execute(EnvelopeWithSetter.class);
    }

    @Test
    void withConstructorJacksonAnnotated() throws Exception {
        execute(EnvelopeWithConstructorJacksonAnnotated.class);
    }

    /**
     * This is an edge case where you must be careful. A class with a single-arg constructor must have its arg annotated
     * with @JsonProperty("propertyName"). See note at https://github.com/FasterXML/jackson-modules-java8/pull/26/files
     */
    @Test
    void withConstructor() throws Exception {
        var app = new App();
        app.objectMapper.registerModule(new ParameterNamesModule());
        execute(app, EnvelopeWithConstructor.class);
    }

    @Test
    void withConstructorWithExtraneousField() throws Exception {
        var app = new App();
        app.objectMapper.registerModule(new ParameterNamesModule());
        execute(app, EnvelopeWithConstructorWithExtraneousField.class);
    }

    @Test
    void withPojo() throws Exception {
        execute(EnvelopePojo.class);
    }
}
