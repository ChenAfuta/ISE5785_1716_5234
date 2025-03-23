package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Tube class represents a tube in 3D space, defined by a central axis (ray) and a radius.
 */
public class Tube {
    Double radius;
    Ray ray;

    /**
     * Constructs a tube with the given central axis and radius.
     *
     * @param ray the central axis of the tube
     * @param radius the radius of the tube
     */
    public Tube(Ray ray, Double radius) {
        this.ray = ray;
        this.radius = radius;
    }

    /**
     * Calculates the normal vector to the tube at a given point.
     *
     * @param point the point on the surface of the tube
     * @return the normal vector to the tube at the given point
     */
    public Vector getNormal(Point point) {
        return null;
    }
}