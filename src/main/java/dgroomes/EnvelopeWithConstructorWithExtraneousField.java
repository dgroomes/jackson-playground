package dgroomes;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnvelopeWithConstructorWithExtraneousField that = (EnvelopeWithConstructorWithExtraneousField) o;
        return Objects.equals(message, that.message) &&
                Objects.equals(unusedField, that.unusedField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, unusedField);
    }
}
