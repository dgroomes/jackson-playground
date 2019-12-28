package dgroomes;

/**
 * Modelling JSON like:
 *
 * {
 *     "message": "hello"
 * }
 *
 * Can we use Java "Records" to model this data?
 */
public class PayloadWithMessage {

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
}
