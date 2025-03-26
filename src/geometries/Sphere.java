package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * The Sphere class represents a sphere in a 3D space.
 * A sphere is defined by its center point and radius.
 */
public class Sphere extends RadialGeometry{
    final protected  Point center;


    /**
     * Constructs a Sphere with the specified center point and radius.
     * @param center the center point of the sphere
     */
    public Sphere(Point center,double radius)
    {
        super(radius);
        this.center = center;
    }

    /**
     * Calculates the normal vector to the sphere at a given point.
     * @param point the point on the surface of the sphere
     * @return the normal vector to the sphere at the given point
     */
    public Vector getNormal(Point point) {
        return null;
    }
}