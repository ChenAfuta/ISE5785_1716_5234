package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Plane class represents a plane in 3D space, defined by a point and a normal vector.
 */
public class Plane {
    Point point;
    Vector normal;

    /**
     * Constructs a plane with the given point and normal vector.
     *
     * @param point the point on the plane
     * @param normal the normal vector to the plane
     */
    public Plane(Point point, Vector normal) {
        this.point = point;
        this.normal = normal.normalize();
    }

    /**
     * Constructs a plane with three points on the plane.
     * The normal vector is calculated as the cross product of the vectors defined by these points.
     *
     * @param point the first point on the plane
     * @param otherPoint the second point on the plane
     * @param anotherPoint the third point on the plane
     */
    public Plane(Point point, Point otherPoint, Point anotherPoint) {
        this.point = point;
        this.normal = (otherPoint.subtract(point)).crossProduct(anotherPoint.subtract(point)).normalize();
    }

    /**
     * Returns the normal vector to the plane at a given point.
     *
     * @param point the point on the plane
     * @return the normal vector to the plane at the given point
     */
    public Vector getNormal(Point point) {
        return this.normal;
    }
}