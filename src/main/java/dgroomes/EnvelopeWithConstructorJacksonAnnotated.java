package dgroomes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnvelopeWithConstructorJacksonAnnotated that = (EnvelopeWithConstructorJacksonAnnotated) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }
}
