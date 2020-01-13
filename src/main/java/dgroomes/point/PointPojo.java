package dgroomes.point;

/**
 * Plain Old Java Object (POJO): No setters or constructors; just public fields.
 *
 * Jackson is able to to serialize/deserialize to this class without any special configuration.
 */
public class PointPojo {

    public Integer x;
    public Integer y;
}
