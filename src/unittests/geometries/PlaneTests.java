package unittests.geometries;

import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.Point;
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
}
