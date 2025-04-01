package unittests.primitives;

import primitives.Vector;
import org.junit.jupiter.api.Test;
import primitives.Point;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Point class.
 */
class PointTests {
    private final Point point1 = new Point(1, 2, 3);
    private final Point point2 = new Point(4, 5, 6);
    private final Vector vector1 = new Vector(1, 2, 3);

    /**
     * Test for the subtract method.
     */
    @Test
    void testSubtract() {
        // In this case, Point - Point creates a vector, so we need to check if the resulting vector is correct
        assertEquals(new Vector(3, 3, 3), point2.subtract(point1), "ERROR: Point - Point does not work correctly");

        // Fixed the error, replaced NullPointerException with IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> point1.subtract(point1), "ERROR: Point - Point subtraction does not throw an exception when subtracting the same point");
    }

    /**
     * Test for the add method.
     */
    @Test
    void testAdd() {
        assertEquals(new Point(2, 4, 6), point1.add(vector1), "ERROR: Point + Vector does not work correctly");
        assertEquals(Point.ZERO, point1.add(new Vector(-1, -2, -3)), "ERROR: Point + Vector does not work correctly");
    }

    /**
     * Test for the distanceSquared method.
     */
    @Test
    void testDistanceSquared() {
        assertEquals(27, point1.distanceSquared(point2), "ERROR: Distance Point does not work correctly");
        assertEquals(0, point1.distanceSquared(point1), 0.00001, "ERROR: Distance Point does not work correctly");
    }

    /**
     * Test for the distance method.
     */
    @Test
    void testDistance() {
        assertEquals(3.0, point1.distance(new Point(0, 4, 5)), 0.00001, "ERROR: Distance Point does not work correctly"); // Fixed the expected distance
        assertEquals(0, point1.distance(point1), "ERROR: Distance Point does not work correctly");
    }

}
