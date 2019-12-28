package dgroomes;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    private <T extends Envelope> void execute(Class<T> targetClass) throws Exception {
        var classUnderTest = new App();
        String json = """
                { "message": "Hello from the tests!"}
                """;

        var msg = classUnderTest.extractMessage(json, targetClass);

        assertEquals("Hello from the tests!", msg);
    }

    @Test
    void withSetter() throws Exception {
        execute(EnvelopeWithSetter.class);
    }

    @Test
    void withConstructorJacksonAnnotated() throws Exception {
        execute(EnvelopeWithConstructorJacksonAnnotated.class);
    }

    @Disabled
    @Test
    void withConstructor() throws Exception {
        execute(EnvelopeWithConstructor.class);
    }

    @Test
    void withPojo() throws Exception {
        execute(EnvelopePojo.class);
    }
}
