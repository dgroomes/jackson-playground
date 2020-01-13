package dgroomes.point;

/**
 * With a constructor and with the ObjectMapper configured with Jackson's "ParameterNamesModule", Jackson can
 * serialize/deserialize to this class.
 *
 * This strategy affords one major benefit: the model class remains decoupled from the serialization/deserialization
 * library. No Jackson annotations are needed in this class! So, a different serialization/deserialization library can
 * be substituted into this app to replace Jackson and this class would not have to change.
 */
public class PointWithConstructor {

    private Integer x;
    private Integer y;

    public PointWithConstructor(Integer x, Integer y) {
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
