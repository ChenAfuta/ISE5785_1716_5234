package geometries;

import geometries.Tube;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Tube class.
 */
class TubeTests {

    /**
     * Test the getNormal method of Tube.
     * The normal at a point on the surface of the tube should be perpendicular to the axis.
     */
    @Test
    void getNormal() {
        // Create a tube with radius 2 and axis along the Z-axis
        Tube tube = new Tube(2.0, new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)));

        // Point on the tube surface, 2 units away from the axis along the X-axis
        Point p = new Point(2, 0, 3);
        Vector expectedNormal = new Vector(1, 0, 0); // Normal should be in the direction away from the axis

        // Verify the normal vector is as expected and has unit length
        assertEquals(expectedNormal, tube.getNormal(p), "The normal vector of the tube is incorrect");
        assertEquals(1, tube.getNormal(p).length(), "The length of the normal vector should be 1");
    }



}