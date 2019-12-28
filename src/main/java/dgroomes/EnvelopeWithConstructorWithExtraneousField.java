package dgroomes;

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
