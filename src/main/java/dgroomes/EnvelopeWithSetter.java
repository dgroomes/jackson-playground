package dgroomes;

import java.util.Objects;

public class EnvelopeWithSetter implements Envelope {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
