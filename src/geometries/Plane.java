package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

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

    /**
     * Finds the intersection point of the plane with a given {@link Ray}.
     * <p>
     * The method checks whether the ray is parallel to the plane, starts on the plane,
     * or intersects it at a point in the ray's direction.
     *
     * @param ray the ray to intersect with the plane
     * @return a list containing the intersection point if it exists; {@code null} otherwise
     */
    @Override
    protected List<Point> findGeoIntersectionsHelper(Ray ray) {
        Vector direction = ray.getDirection();
        Point p0 = ray.getPoint(0);
        // if the ray is parallel to the plane or the ray starts on the plane at the point q
        if (Util.isZero(direction.dotProduct(normal)) || q.equals(p0))
            return null;

        // calculate the intersection point
        double t = normal.dotProduct(q.subtract(p0)) / normal.dotProduct(direction);

        return Util.alignZero(t) <= 0 ? null : List.of(new Point(this, ray.getPoint(t)));
}
}
