package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * The {@code Tube} class represents a geometric tube in 3D space.
 * <p>
 * A tube is an infinite cylinder defined by a central axis {@link Ray}
 * and a constant radius. Unlike a finite cylinder, a tube extends infinitely in both directions.
 * </p>
 * The tube inherits the radius field and behavior from {@link RadialGeometry}.
 */
public class Tube extends RadialGeometry {

    /**
     * The central axis (infinite ray) that defines the orientation and location of the tube.
     */
    protected final Ray axis;

    /**
     * Constructs a new {@code Tube} with the given radius and central axis.
     *
     * @param radius the radius of the tube (must be positive)
     * @param ray    the central axis of the tube
     */
    public Tube(Double radius, Ray ray) {
        super(radius);
        this.axis = ray;
    }

    /**
     * Returns the normal vector to the surface of the tube at a given point.
     * <p>
     * This is computed by projecting the point onto the axis and finding the vector
     * perpendicular to the axis direction from the axis to the point.
     *
     * @param point a point on the surface of the tube
     * @return the normalized vector orthogonal to the tube at the given point
     */
    public Vector getNormal(Point point) {
        Vector fromAxisOrigin = point.subtract(axis.getPoint(0));

        // Project the vector onto the axis direction to find the closest point on the axis
        double t = fromAxisOrigin.dotProduct(axis.getDirection());

        // Find the closest point on the axis to the given point
        Point closestPointOnAxis = axis.getPoint(t);

        // The normal is the vector from the closest point on the axis to the surface point
        return point.subtract(closestPointOnAxis).normalize();
    }

    /**
     * Finds the intersection points of a given ray with the tube.
     * <p>
     * This method is not yet implemented and currently returns {@code null}.
     *
     * @param ray the ray for which to find intersections with the tube
     * @return a list of intersection points, or {@code null} if unimplemented
     */
    @Override
    protected List<Intersection> calculateIntersectionsHelper(Ray ray) {
        throw new UnsupportedOperationException("This method is not implemented yet");
    }
}
