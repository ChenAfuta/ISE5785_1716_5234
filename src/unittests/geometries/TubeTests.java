package unittests.geometries;

import geometries.Tube;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Tube class.
 */
class TubeTests {

    /**
     * Test for getting the normal vector of the tube at a given point.
     */
    @Test
    void getNormal() {
        // Create a Tube with a vector axis
        Tube tube = new Tube(2.0, new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)));

        // Point on the surface (Z-axis, 2 units away from the center)
        Point p = new Point(2, 0, 3);
        Vector expectedNormal = new Vector(1, 0, 0); // A vector crossing the axis (perpendicular to the tube's center)

        // Check if the normal is correct
        assertEquals(expectedNormal, tube.getNormal(p), "The normal vector of the tube is incorrect");
        assertEquals(1, tube.getNormal(p).length(), "The length of the normal vector should be 1");
    }
}
