package dgroomes.point;

import java.util.Objects;

/**
 * This is like {@link PointPojo} but the field names are multiple "words". Specifically, instead of field "x" it is
 * "xCoordinate".
 */
public class PointPojoWithMultiWordFields {

    public Integer xCoordinate;
    public Integer yCoordinate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointPojoWithMultiWordFields that = (PointPojoWithMultiWordFields) o;
        return Objects.equals(xCoordinate, that.xCoordinate) && Objects.equals(yCoordinate, that.yCoordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCoordinate, yCoordinate);
    }

    @Override
    public String toString() {
        return "PointPojoWithMultiWordFields{" +
                "xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                '}';
    }
}
