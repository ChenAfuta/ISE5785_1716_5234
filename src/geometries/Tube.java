package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * The Tube class represents a tube in a 3D space.
 * A tube is defined by a central axis (ray) and a radius.
 */
public class Tube extends RadialGeometry{
    protected final Ray axis;

    /**
     * Constructs a Tube with the specified central axis and radius.
     * @param ray the central axis of the tube
     * @param radius the radius of the tube
     */
    public Tube( Double radius, Ray ray) {
        super(radius);
        this.axis = ray;
    }

    /**
     * Calculates the normal vector to the tube at a given point.
     * @param point the point on the surface of the tube
     * @return the normal vector to the tube at the given point
     */
    public Vector getNormal(Point point) {
        double t=point.subtract(axis.).dotProduct(axis.getDirection());
        return point.subtract(axis.getpoint(t)).normalize();
    }
}