package geometries;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Geometry interface (or abstract class).
 * This test uses Sphere as a representative geometry.
 */
class GeometryTests {


    /**
     * Test the findIntersections method for a geometry (Sphere in this case).
     * Validates both intersection and non-intersection scenarios.
     */
    @Test
    public void testFindIntersections() {
        // Create a sphere with radius 1 and center at (0, 0, 3)
        Geometry geo = new Sphere(1, new Point(0, 0, 3));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray intersects the sphere at two points
        Ray ray1 = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        List<Point> result = geo.findIntersections(ray1);
        assertNotNull(result, "Expected intersections");
        assertEquals(2, result.size(), "Wrong number of intersection points");

        // TC02: Ray does not intersect the sphere
        Ray ray2 = new Ray(new Point(0, 2, 0), new Vector(0, 0, 1));
        assertNull(geo.findIntersections(ray2), "Ray should not intersect the geometry");
   }
}