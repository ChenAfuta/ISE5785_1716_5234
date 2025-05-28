package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

/**
 * The {@code Triangle} class represents a triangle in 3D space.
 * <p>
 * A triangle is a special case of a polygon with exactly three vertices.
 * It extends the {@link Polygon} class and inherits its properties and methods.
 */
public class Triangle extends Polygon {

    /**
     * Constructs a triangle defined by three vertices.
     *
     * @param a the first vertex
     * @param b the second vertex
     * @param c the third vertex
     */
    public Triangle(Point a, Point b, Point c) {
        super(a, b, c);
    }

    /**
     * Finds the intersection points between a given {@link Ray} and the triangle.
     * <p>
     * This method checks if the ray intersects the plane containing the triangle and
     * verifies whether the intersection point lies inside the triangle.
     *
     * @param ray the ray to intersect with the triangle
     * @return a list containing the intersection point if it lies within the triangle;
     *         {@code null} if there is no intersection or the point is outside the triangle
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
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
            return List.of(new GeoPoint(this,ray.getPoint(t1)),new GeoPoint(this, ray.getPoint(t2)));

        // if the ray starts inside the sphere
        if (t1 > 0)
            return List.of(new GeoPoint(this,ray.getPoint(t1)));
        if (t2 > 0)
            return List.of(new GeoPoint(this,ray.getPoint(t2)));

        return null;
}
}
