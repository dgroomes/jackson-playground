package dgroomes.point;

import java.util.Objects;

/**
 * Plain Old Java Object (POJO): No setters or constructors; just public fields.
 *
 * Jackson is able to to serialize/deserialize to this class without any special configuration.
 */
public class PointPojo {

    public Integer x;
    public Integer y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointPojo pointPojo = (PointPojo) o;
        return Objects.equals(x, pointPojo.x) &&
                Objects.equals(y, pointPojo.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "PointPojo{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
