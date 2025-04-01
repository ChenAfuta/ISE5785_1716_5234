package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * The Plane class represents a plane in a 3D space.
 * A plane is defined by a point on the plane and a normal vector.
 */
public class Plane {
    Point point;
    Vector normal;

    /**
     * Constructs a Plane with the specified point and normal vector.
     * @param point the point on the plane
     * @param normal the normal vector to the plane
     */
    public Plane(Point point, Vector normal) {
        this.point = point;
        this.normal = normal.normalize();
    }

    /**
     * Constructs a Plane with three points on the plane.
     * The normal vector is calculated as the cross product of the vectors defined by these points.
     * @param point the first point on the plane
     * @param otherPoint the second point on the plane
     * @param anotherPoint the third point on the plane
     */
    public Plane(Point point, Point otherPoint, Point anotherPoint) {
        this.point = point;
        this.normal = otherPoint.subtract(point).crossProduct(anotherPoint.subtract(point)).normalize();
    }

    /**
     * Returns the normal vector to the plane at a given point.
     * @param point the point on the plane
     * @return the normal vector to the plane at the given point
     */
    public Vector getNormal(Point point) {
        return this.normal;
    }
}