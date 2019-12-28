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

    /**
     * Deserialize to a target class that only has a constructor.
     * This fails!
     * Do we need to configure Jackson specially to accommodate constructor-based deserialization?
     */
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
