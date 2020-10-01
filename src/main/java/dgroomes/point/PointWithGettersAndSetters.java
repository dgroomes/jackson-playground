package dgroomes.point;

/**
 * This class has getters/setters but no constructor.
 *
 * Jackson is able to to serialize/deserialize to this class without any special configuration.
 */
public class PointWithGettersAndSetters {

    private Integer x;
    private Integer y;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
