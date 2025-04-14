package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * The Tube class represents a tube in a 3D space.
 * A tube is defined by a central axis (ray) and a radius.
 */
public class Tube extends RadialGeometry {
    protected final Ray axis;

    /**
     * Constructs a Tube with the specified central axis and radius.
     * @param ray the central axis of the tube
     * @param radius the radius of the tube
     */
    public Tube(Double radius, Ray ray) {
        super(radius);
        this.axis = ray;
    }

    /**
     * Calculates the normal vector to the tube at a given point.
     * @param point the point on the surface of the tube
     * @return the normal vector to the tube at the given point
     */
    public Vector getNormal(Point point) {
        // Vector from the axis origin to the point on the surface
        Vector v = point.subtract(axis.getPoint());

        // Project this vector onto the plane perpendicular to the tube axis (i.e., remove the component along the axis)
        double projectionLength = v.dotProduct(axis.getDirection());
        Vector projection = axis.getDirection().scale(projectionLength);

        // The normal is the difference between the point vector and the projection
        Vector normal = v.subtract(projection).normalize();

        return normal;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
