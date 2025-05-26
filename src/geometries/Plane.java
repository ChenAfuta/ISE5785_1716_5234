package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * The {@code Plane} class represents an infinite plane in 3D space.
 * <p>
 * A plane is defined by a point on the plane and a normal vector perpendicular to it.
 * This class implements the {@link Intersectable} interface and extends {@link Geometry}.
 */
public class Plane extends Geometry implements Intersectable {

    /** A point on the plane. */
    private final Point point;

    /** The normalized vector perpendicular to the plane (its normal). */
    private final Vector normal;

    /**
     * Constructs a plane from a point and a normal vector.
     *
     * @param point  a point on the plane
     * @param normal the normal vector to the plane (will be normalized)
     */
    public Plane(Point point, Vector normal) {
        this.point = point;
        this.normal = normal.normalize();
    }

    /**
     * Constructs a plane from three non-collinear points.
     *
     * @param p1 the first point
     * @param p2 the second point
     * @param p3 the third point
     * @throws IllegalArgumentException if any two points are equal or if the points are collinear
     */
    public Plane(Point p1, Point p2, Point p3) {
        if (p1.equals(p2) || p1.equals(p3) || p2.equals(p3)) {
            throw new IllegalArgumentException("Two or more points are identical");
        }

        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);

        if (v1.normalize().equals(v2.normalize()) || v1.normalize().equals(v2.normalize().scale(-1))) {
            throw new IllegalArgumentException("The points are collinear");
        }

        this.point = p1;
        this.normal = v1.crossProduct(v2).normalize();
    }

    /**
     * Returns the normal vector of the plane at the given point (constant for a plane).
     *
     * @param point a point on the plane (not used since the normal is the same everywhere)
     * @return the normal vector
     */
    public Vector getNormal(Point point) {
        return this.normal;
    }

    /**
     * Returns the normal vector of the plane.
     *
     * @return the normal vector
     */
    public Vector getNormal() {
        return this.normal;
    }

    @Override
    protected List<Intersection> calculateIntersectionsHelper(Ray ray) {
        // Point that represents the ray's head
        final Point rayPoint = ray.getPoint(0);
        // Vector that represents the ray's axis
        final Vector rayVector = ray.getVector();

        // in case the ray's head is the reference point in the plane, there are no intersections
        if(rayPoint.equals(head))
            return null;

        // numerator for the formula
        final double numerator = normal.dotProduct(head.subtract(rayPoint));
        // denominator for the formula
        final double denominator = normal.dotProduct(rayVector);
        // in case ray is parallel to the plane
        if (isZero(denominator))
            return null;

        final double t = alignZero(numerator / denominator);

        // if (0 ≥ t) there are no intersections
        return t > 0 ? List.of(new Intersection(this, ray.getPoint(t))) : null;
    }
}
