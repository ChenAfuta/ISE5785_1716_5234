package primitives;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Ray} class.
 * <p>
 * These tests verify the correctness of the {@link Ray#getPoint(double)} method
 * for various values of the parameter {@code t}.
 */
public class RayTests {

    /**
     * Test case for {@link Ray#getPoint(double)} with a positive distance.
     * <p>
     * Verifies that the point returned by the method lies at the correct location
     * in the direction of the ray.
     */
    @Test
    public void testGetPointPositive() {
        Ray ray = new Ray(new Point(1, 2, 3), new Vector(0, 0, 1));
        Point expected = new Point(1, 2, 5);
        assertEquals(expected, ray.getPoint(2), "Ray should return point at distance 2 in direction");
    }

    /**
     * Test case for {@link Ray#getPoint(double)} with a negative distance.
     * <p>
     * Verifies that the method correctly returns a point in the opposite direction
     * of the ray vector.
     */
    @Test
    public void testGetPointNegative() {
        Ray ray = new Ray(new Point(1, 2, 3), new Vector(0, 0, 1));
        Point expected = new Point(1, 2, 1);
        assertEquals(expected, ray.getPoint(-2), "Ray should return point at distance -2 in direction");
    }

    /**
     * Test case for {@link Ray#getPoint(double)} with zero distance.
     * <p>
     * Verifies that the method returns the ray's origin when {@code t = 0}.
     */
    @Test
    public void testGetPointZero() {
        Ray ray = new Ray(new Point(1, 2, 3), new Vector(0, 0, 1));
        Point expected = new Point(1, 2, 3);
        assertEquals(expected, ray.getPoint(0), "Ray should return its origin point for t=0");
    }
}
