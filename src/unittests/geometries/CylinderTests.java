package unittests.geometries;

import geometries.Cylinder;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Cylinder class.
 */
class CylinderTests {

    /**
     * Test for getting the height of the cylinder.
     */
    @Test
    void getHeight() {
        Cylinder cylinder = new Cylinder(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 2, 5);
        assertEquals(5, cylinder.getHeight(), "The height of the cylinder is incorrect");
    }

    /**
     * Test for getting the normal vector of the cylinder at a given point.
     */
    @Test
    void getNormal() {
        Cylinder cylinder = new Cylinder(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 2, 5);

        // Test on the surface - a point on the surface of the cylinder
        Point p1 = new Point(2, 0, 3);
        Vector expectedNormal1 = new Vector(1, 0, 0);
        assertEquals(expectedNormal1, cylinder.getNormal(p1), "The normal vector on the surface is incorrect");

        // Test on the bottom base - a point on the bottom base of the cylinder
        Point p2 = new Point(1, 1, 0);
        Vector expectedNormal2 = new Vector(0, 0, -1);
        assertEquals(expectedNormal2, cylinder.getNormal(p2), "The normal vector on the bottom base is incorrect");

        // Test on the top base - a point on the top base of the cylinder
        Point p3 = new Point(-1, -1, 5);
        Vector expectedNormal3 = new Vector(0, 0, 1);
        assertEquals(expectedNormal3, cylinder.getNormal(p3), "The normal vector on the top base is incorrect");
    }
    @Test
    public void testFindIntersectionsCylinder() {
        Cylinder cylinder = new Cylinder(new Ray(Point.ZERO, new Vector(0, 0, 1)), 1.0, 3.0);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray intersects the cylinder (2 points)
        Ray ray1 = new Ray(new Point(2, 0, 1), new Vector(-1, 0, 0));
        List<Point> result = cylinder.findIntersections(ray1);
        assertNotNull(result, "Expected 2 intersection points");
        assertEquals(2, result.size(), "Wrong number of points");

        // TC02: Ray is outside and parallel to axis (0 points)
        Ray ray2 = new Ray(new Point(2, 0, 0), new Vector(0, 0, 1));
        assertNull(cylinder.findIntersections(ray2), "Parallel and outside - must return null");
}
}
