package dgroomes;

public class EnvelopeWithSetter implements Envelope {

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
}
