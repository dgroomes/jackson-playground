package dgroomes;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Runner {

    private static Logger log = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) throws JsonProcessingException {
        var app = new App();
        String json =
        """
        { "message": "Hello world."}
        """;
        var msg = app.extractMessage(json, EnvelopeWithSetter.class);
        log.info("Extracted message: {}", msg);
    }
}
