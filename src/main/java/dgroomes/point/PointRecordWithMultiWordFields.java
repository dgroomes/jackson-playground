package dgroomes.point;

/**
 * This is like {@link PointRecord} but the field names are multiple "words". Specifically, instead of field "x" it is
 * "xCoordinate".
 */
public record PointRecordWithMultiWordFields(
        Integer xCoordinate,
        Integer yCoordinate) {
}
