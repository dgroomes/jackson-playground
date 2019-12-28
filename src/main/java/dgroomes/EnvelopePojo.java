package dgroomes;

/**
 * Plain Old Java Object (POJO): No setters or constructors; just a public field (plus the getter method to accommodate
 * the interface)
 */
public class EnvelopePojo implements Envelope {

    public String message;

    public String getMessage() {
        return message;
    }
}
