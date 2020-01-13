package dgroomes.singleargconstructor;

/**
 * This class is like {@link dgroomes.point.PointWithConstructor} but for a class with a single-arg constructor. It illustrates a
 * limitation in Jackson and the "ParameterNamesModule". For classes with a single-arg constructor, the ParameterNamesModule
 * has no effect. Jackson cannot be configured to serialize/deserialize to this class!
 *
 * See the note at https://github.com/FasterXML/jackson-modules-java8/pull/26/files
 *
 * Instead, you must introduce the JsonProperty annotation to this class. See {@link SingleArgConstructorWithJsonProperty}
 */
public class SingleArgConstructor {

    private String message;

    public SingleArgConstructor(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
