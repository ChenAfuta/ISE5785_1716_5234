package unittests.primitives;

import primitives.Double3;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Double3 class.
 */
class Double3Tests {

    /**
     * Test for the add method.
     */
    @Test
    void add() {
        Double3 d1 = new Double3(1.0, 2.0, 3.0);
        Double3 d2 = new Double3(3.0, 4.0, 5.0);
        assertEquals(new Double3(4.0, 6.0, 8.0), d1.add(d2), "The addition operation is incorrect");
    }

    /**
     * Test for the subtract method.
     */
    @Test
    void subtract() {
        Double3 d1 = new Double3(5.0, 7.0, 9.0);
        Double3 d2 = new Double3(2.0, 3.0, 4.0);
        assertEquals(new Double3(3.0, 4.0, 5.0), d1.subtract(d2), "The subtraction operation is incorrect");
    }

    /**
     * Test for the scale method.
     */
    @Test
    void scale() {
        Double3 d = new Double3(1.0, -2.0, 3.0);
        assertEquals(new Double3(2.0, -4.0, 6.0), d.scale(2), "The scalar multiplication is incorrect");
    }

    /**
     * Test for the reduce method.
     */
    @Test
    void reduce() {
        Double3 d = new Double3(6.0, -8.0, 10.0);
        assertEquals(new Double3(3.0, -4.0, 5.0), d.reduce(2), "The scalar division is incorrect");
    }

    /**
     * Test for the product method.
     */
    @Test
    void product() {
        Double3 d1 = new Double3(1.0, 2.0, 3.0);
        Double3 d2 = new Double3(2.0, 3.0, 4.0);
        assertEquals(new Double3(2.0, 6.0, 12.0), d1.product(d2), "The component-wise multiplication is incorrect");
    }

    /**
     * Test for the lowerThan method.
     */
    @Test
    void lowerThan() {
        Double3 d = new Double3(1.0, 2.0, 3.0);
        assertTrue(d.lowerThan(4.0), "lowerThan test failed");
        assertFalse(d.lowerThan(2.0), "lowerThan test failed");
    }

    /**
     * Test for the d1 method.
     */
    @Test
    void d1() {
        Double3 d = new Double3(1.0, 2.0, 3.0);
        assertEquals(1.0, d.d1(), "d1() does not return the first component correctly");
    }

    /**
     * Test for the d2 method.
     */
    @Test
    void d2() {
        Double3 d = new Double3(1.0, 2.0, 3.0);
        assertEquals(2.0, d.d2(), "d2() does not return the second component correctly");
    }

    /**
     * Test for the d3 method.
     */
    @Test
    void d3() {
        Double3 d = new Double3(1.0, 2.0, 3.0);
        assertEquals(3.0, d.d3(), "d3() does not return the third component correctly");
    }
}
