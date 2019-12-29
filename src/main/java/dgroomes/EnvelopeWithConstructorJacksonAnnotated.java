package dgroomes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Notice the Jackson annotations on the constructor. This is needed for Jackson to be able to deserialize to this class
 * using its constructor because normally parameter names are not available at runtime because they are optimized away.
 *
 * There are alternatives to this: see https://stackoverflow.com/a/33578478.
 */
public class EnvelopeWithConstructorJacksonAnnotated implements Envelope {

    private String message;

    @JsonCreator
    public EnvelopeWithConstructorJacksonAnnotated(@JsonProperty("message") String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
