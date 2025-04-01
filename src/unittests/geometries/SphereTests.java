package unittests.geometries;

import geometries.Sphere;
import primitives.Point;
import primitives.Vector;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Sphere class.
 */
class SphereTests {

    /**
     * Test for getting the normal vector of the sphere at a given point.
     */
    @Test
    void getNormal() {
        // Create a Sphere with center at (0,0,0) and a radius of 5
        Sphere sphere = new Sphere(new Point(0, 0, 0), 5);

        // Point on the surface of the sphere
        Point p = new Point(5, 0, 0);  // On the X-axis

        // The normal vector will be a vector from the center to the point (the direction from the center to the point)
        Vector expectedNormal = new Vector(1, 0, 0); // A vector crossing the X-axis

        // Check if the returned normal is correct
        assertEquals(expectedNormal, sphere.getNormal(p), "The normal vector of the sphere is incorrect");
        assertEquals(1, sphere.getNormal(p).length(), "The length of the normal vector should be 1");
    }
}
