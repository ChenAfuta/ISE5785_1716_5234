package unittests.geometries;

import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Plane class.
 */
class PlaneTests {

    /**
     * Test for creating a valid plane.
     */
    @Test
    void testPlaneConstructorValid() {
        // Test for creating a valid plane
        assertDoesNotThrow(() -> new Plane(
                new Point(0, 0, 0),
                new Point(1, 0, 0),
                new Point(0, 1, 0)
        ));
    }

    /**
     * Test for creating a plane with identical points.
     */
    @Test
    void testPlaneConstructorIdenticalPoints() {
        // Test that an exception is thrown when two points are identical
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Plane(
                new Point(1, 1, 1),
                new Point(1, 1, 1),
                new Point(2, 2, 2)
        ));
        assertEquals("Two or more points are identical", exception.getMessage());
    }

    /**
     * Test for creating a plane with collinear points.
     */
    @Test
    void testPlaneConstructorCollinearPoints() {
        // Test that an exception is thrown when the three points are collinear
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Plane(
                new Point(0, 0, 0),
                new Point(1, 1, 1),
                new Point(2, 2, 2)
        ));
        assertEquals("The points are collinear", exception.getMessage());
    }
    @Test
    public void testFindIntersectionsPlane() {
        Plane plane = new Plane(new Point(0, 0, 1), new Vector(0, 0, 1));

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray intersects the plane
        Ray ray1 = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        List<Point> result = plane.findIntersections(ray1);
        assertNotNull(result, "Ray intersects the plane - must return a point");
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(new Point(0, 0, 1), result.get(0), "Ray intersects the plane");

        // TC02: Ray does not intersect the plane
        Ray ray2 = new Ray(new Point(0, 0, 0), new Vector(0, 1, 0));
        assertNull(plane.findIntersections(ray2), "Ray is parallel to plane - must return null");

        // =============== Boundary Values Tests ==================
        // TC11: Ray lies in the plane
        Ray ray3 = new Ray(new Point(0, 0, 1), new Vector(1, 0, 0));
        assertNull(plane.findIntersections(ray3), "Ray lies in the plane - must return null");

        // TC12: Ray is orthogonal and starts before the plane
        Ray ray4 = new Ray(new Point(0, 0, -1), new Vector(0, 0, 1));
        result = plane.findIntersections(ray4);
        assertNotNull(result, "Orthogonal ray from below - must intersect");
        assertEquals(new Point(0, 0, 1), result.get(0), "Incorrect intersection point");

        // TC13: Ray is orthogonal and starts on the plane
        Ray ray5 = new Ray(new Point(0, 0, 1), new Vector(0, 0, 1));
        assertNull(plane.findIntersections(ray5), "Orthogonal ray from plane - must return null");

        // TC14: Ray is orthogonal and starts after the plane
        Ray ray6 = new Ray(new Point(0, 0, 2), new Vector(0, 0, 1));
        assertNull(plane.findIntersections(ray6), "Orthogonal ray above plane - must return null");
    }
}
