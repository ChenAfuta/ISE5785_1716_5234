package geometries;

import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Plane class.
 */
class

PlaneTests {

    /**
     * Test for constructing a valid plane from three non-collinear, distinct points.
     */
    @Test
    void testPlaneConstructorValid() {
        // Valid construction of a plane with three distinct, non-collinear points
        assertDoesNotThrow(() -> new Plane(
                new Point(0, 0, 0),
                new Point(1, 0, 0),
                new Point(0, 1, 0)
        ));
    }

    /**
     * Test for constructing a plane with two identical points - should throw exception.
     */
    @Test
    void testPlaneConstructorIdenticalPoints() {
        // Expect IllegalArgumentException due to identical points
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Plane(
                new Point(1, 1, 1),
                new Point(1, 1, 1),
                new Point(2, 2, 2)
        ));
        assertEquals("Two or more points are identical", exception.getMessage());
    }

    /**
     * Test for constructing a plane with collinear points - should throw exception.
     */
    @Test
    void testPlaneConstructorCollinearPoints() {
        // Expect IllegalArgumentException due to collinear points
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Plane(
                new Point(0, 0, 0),
                new Point(1, 1, 1),
                new Point(2, 2, 2)
        ));
        assertEquals("The points are collinear", exception.getMessage());
    }

    /**
     * Test the findIntersections method of Plane.
     * Covers equivalence partitions and boundary value tests.
     */
    @Test
    public void testfindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        Plane plane = new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1));
        Ray ray = new Ray(new Point(1, 1, 1), new Vector(-1, -1, -1));
        List<Point> result = plane.findIntersections(ray);
        assertEquals(1, result.size(), "Plane should intersect with the ray");
        assertEquals(new Point(1.0 / 3, 1.0 / 3, 1.0 / 3), result.get(0), "Intersection point is incorrect");
}
}
