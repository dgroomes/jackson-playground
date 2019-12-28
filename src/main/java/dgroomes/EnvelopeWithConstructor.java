package dgroomes;

public class EnvelopeWithConstructor implements Envelope {

    private String message;

    public EnvelopeWithConstructor(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
