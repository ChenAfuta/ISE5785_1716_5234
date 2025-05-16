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
    public List<Point> findIntersections(Ray ray) {
        List<Point> planeIntersection = plane.findIntersections(ray);
        if (planeIntersection == null) // No intersection with the plane
            return null;

        // Vectors from ray origin to triangle vertices
        Vector v1 = vertices.get(0).subtract(ray.getPoint());
        Vector v2 = vertices.get(1).subtract(ray.getPoint());
        Vector v3 = vertices.get(2).subtract(ray.getPoint());

        // Normal vectors to triangle edges using cross product
        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();

        // Check if the intersection point is inside the triangle using sign of dot products
        if (Util.compareSign(ray.getDirection().dotProduct(n1), ray.getDirection().dotProduct(n2)) &&
                Util.compareSign(ray.getDirection().dotProduct(n2), ray.getDirection().dotProduct(n3)) &&
                Util.compareSign(ray.getDirection().dotProduct(n3), ray.getDirection().dotProduct(n1))) {
            return planeIntersection;
        }

        return null;
    }
}
