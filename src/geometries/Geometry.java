package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Geometry class represents a geometric object in 3D space.
 * It is an abstract class that serves as a base for other geometric shapes.
 */
public abstract class Geometry {
    /**
     * Returns the normal vector to the geometry at a given point.
     *
     * @param point the point on the geometry
     * @return the normal vector to the geometry at the given point
     */
    public abstract Vector getNormal(Point point);
}