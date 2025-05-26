package geometries;

import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.*;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

import java.util.List;

/**
 * Unit tests for the Sphere class.
 */
class SphereTests {

    /** A point at (0,0,1) used in some tests */
    private final Point p001 = new Point(0, 0, 1);
    /** A point at (1,0,0) used in some tests - also the center of the test sphere */
    private final Point p100 = new Point(1, 0, 0);
    /** A vector in the Z-axis direction */
    private final Vector v001 = new Vector(0, 0, 1);

    @Override
    protected List<Intersectable.Intersection> calculateIntersectionsHelper(Ray ray) {
        // Point that represents the ray's head
        final Point rayPoint = ray.getPoint(0);

        // in case the ray's head is the sphere's center, we calculate the one intersection directly
        if(rayPoint.equals(center))
            return List.of(new Intersectable.Intersection(this, ray.getPoint(radius)));

        final Vector u = center.subtract(rayPoint);
        final double tm = ray.getVector().dotProduct(u);
        final double d = Math.sqrt(u.lengthSquared() - tm * tm);
        // if (d ≥ r) there are no intersections
        if( alignZero(d - radius) > 0)
            return null;

        final double th = Math.sqrt(radius * radius - d * d);
        // in case the ray is tangent to the sphere, there are no intersections
        if(isZero(th))
            return null;
        final double t1 = alignZero(tm - th);
        final double t2 = alignZero(tm + th);

        // 2 intersections
        if(t1 > 0 && t2 > 0) {
            Intersectable.Intersection intersection1 = new Intersectable.Intersection(this, ray.getPoint(t1));
            Intersectable.Intersection intersection2 = new Intersectable.Intersection(this, ray.getPoint(t2));
            return List.of(intersection1, intersection2);
        }
        // 1 intersection
        else if(t1 > 0)
            return List.of(new Intersectable.Intersection(this, ray.getPoint(t1)));
            // 1 intersection
        else if(t2 > 0)
            return List.of(new Intersectable.Intersection(this, ray.getPoint(t2)));
            // 0 intersections
        else
            return null;
    }

}