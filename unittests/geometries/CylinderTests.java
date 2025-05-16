package geometries;

import geometries.Cylinder;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Cylinder} class.
 */
class CylinderTests {

    /**
     * Test for the {@link Cylinder#getHeight()} method.
     * Verifies that the height returned matches the expected value.
     */
    @Test
    void getHeight() {
        // Create a cylinder with a height of 5
        Cylinder cylinder = new Cylinder(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 2, 5);
        // Assert that the height is correct
        assertEquals(5, cylinder.getHeight(), "The height of the cylinder is incorrect");
    }

    /**
     * Test for the {@link Cylinder#getNormal(Point)} method.
     * Verifies the correctness of the normal vector on the surface, bottom base, and top base.
     */
    @Test
    void getNormal() {
        // Create a cylinder with a height of 5 and radius of 2
        Cylinder cylinder = new Cylinder(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 2, 5);

        // Test on the surface - a point on the surface of the cylinder
        Point p1 = new Point(2, 0, 3); // Point on the curved surface
        Vector expectedNormal1 = new Vector(1, 0, 0); // Expected normal vector
        assertEquals(expectedNormal1, cylinder.getNormal(p1), "The normal vector on the surface is incorrect");

        // Test on the bottom base - a point on the bottom base of the cylinder
        Point p2 = new Point(1, 1, 0); // Point on the bottom base
        Vector expectedNormal2 = new Vector(0, 0, -1); // Expected normal vector
        assertEquals(expectedNormal2, cylinder.getNormal(p2), "The normal vector on the bottom base is incorrect");

        // Test on the top base - a point on the top base of the cylinder
        Point p3 = new Point(-1, -1, 5); // Point on the top base
        Vector expectedNormal3 = new Vector(0, 0, 1); // Expected normal vector
        assertEquals(expectedNormal3, cylinder.getNormal(p3), "The normal vector on the top base is incorrect");
    }
}
