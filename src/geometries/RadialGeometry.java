package geometries;

/**
 * RadialGeometry class represents a radial geometry in 3D space, defined by a radius.
 * It is an abstract class that serves as a base for other radial geometries.
 */
public abstract class RadialGeometry extends Geometry {
    protected double Radius;

    /**
     * Constructs a radial geometry with the given radius.
     *
     * @param radius the radius of the radial geometry
     */
    public RadialGeometry(double radius) {
        this.Radius = radius;
    }
}