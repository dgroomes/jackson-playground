package dgroomes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void withSetter() throws Exception {
        var classUnderTest = new App();
        String json = """
                { "message": "Hello from the tests!"}
                """;

        var msg = classUnderTest.extractMessage(json, EnvelopeWithSetter.class);

        assertEquals("Hello from the tests!", msg);
    }
}
