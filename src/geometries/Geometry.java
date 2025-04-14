package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * The Geometry class is an abstract base class for all geometric objects in a 3D space.
 * It defines a method to calculate the normal vector at a given point on the geometry.
 */
public abstract class Geometry implements Intersectable {
    /**
     * Calculates the normal vector to the geometry at a given point.
     * @param point the point on the geometry
     * @return the normal vector to the geometry at the given point
     */
    protected abstract Vector getNormal(Point point);
}