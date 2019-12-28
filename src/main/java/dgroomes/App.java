package dgroomes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Extract the "message" field of a JSON payload
     */
    public String extractMessage(String jsonPayload) throws JsonProcessingException {
        var payloadWithMessage = objectMapper.readValue(jsonPayload, PayloadWithMessage.class);
        return payloadWithMessage.getMessage();
    }
}
