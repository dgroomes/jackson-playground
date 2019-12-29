package dgroomes;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Ideally, there should be no Jackson annotations in this class if we aim to keep our model classes decoupled from the
 * deserialization/serialization concerns by leveraging the Jackson ParameterNamesModule. But there is a special case
 * for classes that have a single-arg constructor where @JsonProperty("propertyName") is required. This class
 * illustrates that issue.
 *
 * Also, see the note at See note at https://github.com/FasterXML/jackson-modules-java8/pull/26/files
 */
public class EnvelopeWithConstructor implements Envelope {

    private String message;

    public EnvelopeWithConstructor(@JsonProperty("message") String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
