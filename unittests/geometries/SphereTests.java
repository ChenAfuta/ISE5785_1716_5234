package geometries;

import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.*;
import static org.junit.jupiter.api.Assertions.*;
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

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     * Covers several scenarios for ray-sphere intersection.
     */
    @Test
    public void testFindIntersections() {
        // Create a sphere with radius 1 and center at (1, 0, 0)
        Sphere sphere = new Sphere(1d, p100);

        // Expected intersection points for one of the test cases
        final Point gp1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        final Point gp2 = new Point(1.53484692283495, 0.844948974278318, 0);
        final var exp = List.of(gp1, gp2);

        final Vector v310 = new Vector(3, 1, 0); // Vector that will cross the sphere
        final Vector v110 = new Vector(1, 1, 0); // Vector that bypasses the sphere
        final Point p01 = new Point(-1, 0, 0);   // Start point for rays

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere - should return null
        assertNull(sphere.findIntersections(new Ray(p01, v110)), "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere - should return 2 points
        final var result1 = sphere.findIntersections(new Ray(p01, v310));
        assertNotNull(result1, "Expected non-empty list of intersection points");
        assertEquals(2, result1.size(), "Wrong number of intersection points");
        assertEquals(exp, result1, "Unexpected intersection points");

        // TC03: Ray starts inside the sphere - should return 1 intersection point
        Ray ray3 = new Ray(new Point(1, 0, 0), new Vector(1, 0, 0));
        List<Point> result3 = sphere.findIntersections(ray3);
        assertEquals(1, result3.size(), "Ray from inside sphere should intersect once");
        assertEquals(new Point(2, 0, 0), result3.get(0), "Wrong intersection point");

        // TC04: Ray starts after the sphere - should return null
        Ray ray4 = new Ray(new Point(3, 0, 0), new Vector(1, 0, 0));
        assertNull(sphere.findIntersections(ray4), "Ray starts after the sphere, should be no intersection");
 }

}