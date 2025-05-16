package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * The {@code Sphere} class represents a 3D sphere in Cartesian space.
 * <p>
 * A sphere is defined by its center point and radius.
 * This class extends {@link RadialGeometry} and implements the {@link Intersectable} interface.
 */
public class Sphere extends RadialGeometry implements Intersectable {

    /**
     * The center point of the sphere.
     */
    protected final Point center;

    /**
     * Constructs a {@code Sphere} with a given radius and center point.
     *
     * @param radius the radius of the sphere
     * @param p      the center point of the sphere
     */
    public Sphere(double radius, Point p) {
        super(radius);
        this.center = p;
    }

    /**
     * Returns the normal vector to the surface of the sphere at the given point.
     * <p>
     * The normal is calculated as the normalized vector from the center to the point.
     *
     * @param point a point on the surface of the sphere
     * @return the normal vector at the given point
     */
    @Override
    protected Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }

    /**
     * Finds the intersection points between the sphere and a given ray.
     * <p>
     * The method calculates the distance from the ray to the center of the sphere,
     * and determines if and where the ray intersects the sphere.
     *
     * @param ray the {@link Ray} to intersect with the sphere
     * @return a list of intersection points, or {@code null} if no intersections exist
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        // Special case: ray starts at the center of the sphere
        if (center.equals(ray.getPoint()))
            return List.of(ray.getPoint(radius));

        Vector headToCenter = center.subtract(ray.getPoint());

        // tm = projection of center-to-ray-origin vector onto ray direction
        double tm = alignZero(ray.getDirection().dotProduct(headToCenter));

        // d = shortest distance from center to ray
        double d = alignZero(Math.sqrt(headToCenter.lengthSquared() - tm * tm));

        // If d >= radius, the ray misses the sphere
        if (d >= radius || (tm < 0 && headToCenter.lengthSquared() >= radius * radius))
            return null;

        // th = distance from closest point on ray to intersection points
        double th = alignZero(Math.sqrt(radius * radius - d * d));

        // Two intersection points
        if (tm - th > 0)
            return List.of(ray.getPoint(tm - th), ray.getPoint(tm + th));

        // One intersection point (tangent or starts inside)
        return List.of(ray.getPoint(tm + th));
    }
}
