package dgroomes.point;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is like {@link PointWithConstructor} but with the JsonProperty annotation on the constructor arguments.
 * With these annotations, it is not required to configure the ObjectMapper with the "ParameterNamesModule".
 */
public class PointWithConstructorWithJsonPropertyAnnotations {

    private Integer x;
    private Integer y;

    public PointWithConstructorWithJsonPropertyAnnotations(@JsonProperty("x") Integer x, @JsonProperty("y") Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
