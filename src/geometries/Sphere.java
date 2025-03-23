package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Sphere class represents a sphere in 3D space, defined by a center point and a radius.
 */
public class Sphere {
    Point center;
    double radius;

    /**
     * Constructs a sphere with the given center point and radius.
     *
     * @param center the center point of the sphere
     * @param radius the radius of the sphere
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * Calculates the normal vector to the sphere at a given point.
     *
     * @param point the point on the surface of the sphere
     * @return the normal vector to the sphere at the given point
     */
    public Vector getNormal(Point point) {
        return null;
    }
}