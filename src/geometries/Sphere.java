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
    protected List<Point> findGeoIntersectionsHelper(Ray ray) {
        Point p0 = ray.getPoint(0);
        Vector dir = ray.getDirection();

        // if the ray starts at the center of the sphere
        if (center.equals(p0))
            return List.of(new GeoPoint(this, ray.getPoint(radius)));

        Vector u = (center.subtract(p0));
        double tm = dir.dotProduct(u);
        double d = Util.alignZero(Math.sqrt(u.lengthSquared() - tm * tm));
        if (d >= radius)
            return null;

        double th = Math.sqrt(radius * radius - d * d);
        double t1 = Util.alignZero(tm - th);
        double t2 = Util.alignZero(tm + th);

        // if the ray starts before the sphere
        if (t1 > 0 && t2 > 0)
            return List.of(new Point(this,ray.getPoint(t1)),new GeoPoint(this, ray.getPoint(t2)));

        // if the ray starts inside the sphere
        if (t1 > 0)
            return List.of(new Point(this,ray.getPoint(t1)));
        if (t2 > 0)
            return List.of(new Point(this,ray.getPoint(t2)));

        return null;
}
}
