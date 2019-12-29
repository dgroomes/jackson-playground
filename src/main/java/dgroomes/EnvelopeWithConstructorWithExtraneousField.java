package dgroomes;

import java.util.Objects;

/**
 * Companion example to dgroomes.EnvelopeWithConstructor which illustrates how a model class be completely bare of
 * Jackson annotations.
 */
public class EnvelopeWithConstructorWithExtraneousField implements Envelope {

    private String message;

    private String unusedField;

    public EnvelopeWithConstructorWithExtraneousField(String message, String unusedField) {
        this.message = message;
        this.unusedField = unusedField;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
