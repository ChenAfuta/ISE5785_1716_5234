package unittests.primitives;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Ray class.
 */
class

RayTests {

    /**
     * Test for the Ray constructor.
     */
    @Test
    void RayConstructor() {
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point(1, 0, 0);
        Vector v1 = new Vector(0, 1, 0);
        Ray ray1 = new Ray(p1, v1);
        assertEquals(p1, ray1.getP0(), "Ray constructor failed");
        assertEquals(v1, ray1.getDir(), "Ray constructor failed");

        // ============== Boundary Value Tests ==============
        // Test if the vector is zero
        assertThrows(IllegalArgumentException.class, () -> new Ray(p1, new Vector(0, 0, 0)), "Ray constructor should throw exception for zero vector");
    }
}
