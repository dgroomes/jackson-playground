package dgroomes.point;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointWithGettersAndSetters that = (PointWithGettersAndSetters) o;
        return Objects.equals(x, that.x) &&
                Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "PointWithGettersAndSetters{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
