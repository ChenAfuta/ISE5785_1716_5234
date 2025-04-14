package geometries;

/**
 * The RadialGeometry class represents radial geometries in a 3D space.
 * It serves as a base class for geometries that have a radius.
 */
public abstract class RadialGeometry extends Geometry {
    protected double radius;

    /**
     * Constructs a RadialGeometry with the specified radius.
     * @param radius the radius of the radial geometry
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }
}