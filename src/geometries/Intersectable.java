package geometries;

import primitives.*;

import java.util.List;



/**
 * The Intersectable interface defines a method for finding intersections between a ray and geometric objects.
 * It is implemented by classes that represent geometric shapes in 3D space.
 */
public interface Intersectable
{
    /**
     * Finds the intersections between a ray and the geometric object.
     *
     * @param ray the ray to check for intersections
     * @return a list of intersection points, or an empty list if there are no intersections
     */
    List<Point> findIntersections(Ray ray);
}
