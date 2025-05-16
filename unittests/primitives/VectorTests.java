package primitives;

import org.junit.jupiter.api.Test;
import primitives.Vector;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link primitives.Vector} class.
 * This class tests various operations on vectors such as addition, scaling, dot product, cross product, and normalization.
 */
class VectorTests {

    /**
     * Test method for {@link primitives.Vector#add(Vector)}.
     * Verifies that the addition of two vectors produces the correct result.
     */
    @Test
    void add() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(2, 3, 4);
        Vector result = v1.add(v2);
        assertEquals(new Vector(3, 5, 7), result, "Vector addition failed");
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     * Verifies that scaling a vector by a scalar produces the correct result.
     */
    @Test
    void scale() {
        Vector v = new Vector(1, 2, 3);
        Vector result = v.scale(2);
        assertEquals(new Vector(2, 4, 6), result, "Vector scaling failed");
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(Vector)}.
     * Verifies that the dot product of two vectors is calculated correctly.
     */
    @Test
    void dotProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(4, -5, 6);
        double result = v1.dotProduct(v2);
        assertEquals(12, result, "Dot product calculation failed");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(Vector)}.
     * Verifies that the cross product of two vectors is calculated correctly.
     */
    @Test
    void crossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(4, 5, 6);
        Vector result = v1.crossProduct(v2);
        assertEquals(new Vector(-3, 6, -3), result, "Cross product calculation failed");
    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared()}.
     * Verifies that the squared length of a vector is calculated correctly.
     */
    @Test
    void lengthSquared() {
        Vector v = new Vector(1, 2, 2);
        double result = v.lengthSquared();
        assertEquals(9, result, "Length squared calculation failed");
    }

    /**
     * Test method for {@link primitives.Vector#length()}.
     * Verifies that the length of a vector is calculated correctly.
     */
    @Test
    void length() {
        Vector v = new Vector(0, 3, 4);
        double result = v.length();
        assertEquals(5, result, "Length calculation failed");
    }

    /**
     * Test method for {@link primitives.Vector#normalize()}.
     * Verifies that a vector is normalized correctly.
     */
    @Test
    void normalize() {
        Vector v = new Vector(0, 3, 4);
        Vector result = v.normalize();
        assertEquals(new Vector(0, 0.6, 0.8), result, "Normalization failed");
    }
}
