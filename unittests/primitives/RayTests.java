package primitives;

import org.junit.jupiter.api.Test;
import primitives.*;
import static org.junit.jupiter.api.Assertions.*;

public class RayTests {

    @Test
    public void testGetPointPositive() {
        Ray ray = new Ray(new Point(1, 2, 3), new Vector(0, 0, 1));
        Point expected = new Point(1, 2, 5);
        assertEquals(expected, ray.getPoint(2), "Ray should return point at distance 2 in direction");
    }

    @Test
    public void testGetPointNegative() {
        Ray ray = new Ray(new Point(1, 2, 3), new Vector(0, 0, 1));
        Point expected = new Point(1, 2, 1);
        assertEquals(expected, ray.getPoint(-2), "Ray should return point at distance -2 in direction");
    }

    @Test
    public void testGetPointZero() {
        Ray ray = new Ray(new Point(1, 2, 3), new Vector(0, 0, 1));
        Point expected = new Point(1, 2, 3);
        assertEquals(expected, ray.getPoint(0), "Ray should return its origin point for t=0");
    }
}
