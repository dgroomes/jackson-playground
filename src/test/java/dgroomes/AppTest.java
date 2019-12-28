package dgroomes;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.junit.jupiter.api.Disabled;
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
     * I don't know why this is failing. If I add an extraneous field to the class, I can get it work. See
     * {@link #withConstructorWithExtraneousField()}
     */
    @Disabled
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
