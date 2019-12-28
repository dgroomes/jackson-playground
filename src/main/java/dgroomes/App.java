package dgroomes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Harness for exercising FasterXML/Jackson
 */
public class App {

    public ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Extract the "message" field of a JSON payload.
     * <p>
     * The target Class type to deserialize into must implement Envelope. We want to experiment with different
     * implementations of the concrete target type to learn about Jackson (and hopefully Java Records).
     */
    public <T extends Envelope> String extractMessage(String jsonPayload, Class<T> clazz) throws JsonProcessingException {
        var foo = objectMapper.readValue(jsonPayload, clazz);
        return foo.getMessage();
    }
}
