package dgroomes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test
    void appHasAGreeting() throws Exception {
        var classUnderTest = new App();
        String json =
                """
                { "message": "Hello from the tests!"}
                """;
        var msg = classUnderTest.extractMessage(json);
        assertEquals(msg, "Hello from the tests!");
    }
}
