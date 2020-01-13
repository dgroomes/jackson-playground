package dgroomes.singleargconstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is like {@link SingleArgConstructor} but with the additional JsonProperty annotation to enable Jackson
 * to actually serialize/deserialize to this class.
 */
public class SingleArgConstructorWithJsonProperty {

    private String message;

    public SingleArgConstructorWithJsonProperty(@JsonProperty("message") String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
