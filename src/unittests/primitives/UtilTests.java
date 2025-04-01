package unittests.primitives;

import primitives.Util;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Util class.
 */
class UtilTests {

    /**
     * Test for the isZero method.
     */
    @Test
    void isZero() {
        // Test if 0.0 is considered zero
        assertEquals(true, Util.isZero(0.0), "0.0 should be zero");
        assertEquals(true, Util.isZero(1e-10), "A very small number should be considered as zero");
        assertEquals(false, Util.isZero(0.001), "0.001 should not be considered as zero");
    }

    /**
     * Test for the alignZero method.
     */
    @Test
    void alignZero() {
        // Test that values smaller than ε are turned to 0
        assertEquals(0.0, Util.alignZero(1e-10), "alignZero should return 0");
        assertEquals(0.001, Util.alignZero(0.001), "alignZero should not change a value larger than ε");
        assertEquals(-0.001, Util.alignZero(-0.001), "alignZero should not change a negative value larger than ε");
    }

    /**
     * Test for the compareSign method.
     */
    @Test
    void compareSign() {
        // Test that two positive or negative numbers keep the same sign
        assertEquals(true, Util.compareSign(5, 3), "Two positive numbers should have the same sign");
        assertEquals(true, Util.compareSign(-2, -8), "Two negative numbers should have the same sign");
        assertEquals(false, Util.compareSign(-3, 4), "Numbers with opposite signs should not have the same sign");
        assertEquals(false, Util.compareSign(0, 1), "Zero and positive should not have the same sign");
        assertEquals(false, Util.compareSign(0, -1), "Zero and negative should not have the same sign");
    }

    /**
     * Test for the random method.
     */
    @Test
    void random() {
        // Test that all random numbers are within the range [1, 10]
        for (int i = 0; i < 100; i++) {
            double rand = Util.random(1, 10);
            assertEquals(true, rand >= 1 && rand < 10, "Random number out of range [1,10]");
        }
    }
}
