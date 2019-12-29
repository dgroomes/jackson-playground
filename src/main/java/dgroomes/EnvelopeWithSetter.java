package dgroomes;

public class EnvelopeWithSetter implements Envelope {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
