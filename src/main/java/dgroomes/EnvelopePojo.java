package dgroomes;

import java.util.Objects;

/**
 * Plain Old Java Object (POJO): No setters or constructors; just a public field (plus the getter method to accommodate
 * the interface)
 */
public class EnvelopePojo implements Envelope {

    public String message;

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnvelopePojo that = (EnvelopePojo) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }
}
