package unittests.geometries;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class GeometryTests {

    @Test
    void testGetNormal() {
    }
    @Test
    public void testFindIntersectionsGeometry() {
        Geometry geo = new Sphere(1,new Point(0, 0, 3));

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray intersects the geometry (2 points)
        Ray ray1 = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        List<Point> result = geo.findIntersections(ray1);
        assertNotNull(result, "Expected intersections");
        assertEquals(2, result.size(), "Wrong number of points");

        // TC02: Ray misses the geometry
        Ray ray2 = new Ray(new Point(0, 2, 0), new Vector(0, 0, 1));
        assertNull(geo.findIntersections(ray2), "Ray should not intersect");
}
}