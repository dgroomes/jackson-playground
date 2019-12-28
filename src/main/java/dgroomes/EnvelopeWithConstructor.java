package dgroomes;

import java.util.Objects;

public class EnvelopeWithConstructor implements Envelope {

    private String message;

    public EnvelopeWithConstructor(String message) {
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
        EnvelopeWithConstructor that = (EnvelopeWithConstructor) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }
}
