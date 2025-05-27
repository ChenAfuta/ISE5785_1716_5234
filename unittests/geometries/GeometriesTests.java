package geometries;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Unit tests for the {@link Geometries} class, specifically its intersection functionality.
 */
public class GeometriesTests {

    /**
     * Test for an empty geometries list.
     * Verifies that calling {@code findIntersections} on an empty collection returns {@code null}.
     */
    @Test
    public void testEmptyGeometries() {
        Geometries geometries = new Geometries();
        Ray ray = new Ray(new Point(0, 0, 0), new Vector(1, 0, 0));
        assertNull(geometries.findIntersections(ray), "Expected null for empty geometries list");
    }

    /**
     * Test when no geometry is intersected.
     * Ensures that the method returns {@code null} when the ray does not intersect any of the shapes.
     */
    @Test
    public void testNoIntersection() {
        Geometries geometries = new Geometries(
                new Sphere(new Point(5, 5, 0), 1),
                new Plane(new Point(0, 0, 5), new Vector(0, 0, 1)),
                new Triangle(new Point(1, 1, 5), new Point(2, 5, 5), new Point(3, 1, 5))
        );


        // A ray pointing diagonally downward and sideways to avoid intersection
        Ray ray = new Ray(new Point(0, 0, 0), new Vector(-1, -1, -1));

        assertNull(geometries.findIntersections(ray), "Expected null when no shapes are intersected");
    }

    /**
     * Test when exactly one geometry is intersected.
     * In this case, the ray intersects only with the sphere.
     * Verifies that the number of intersection points is exactly 2.
     */
    @Test
    public void testOneIntersection() {
        Geometries geometries = new Geometries(
                new Sphere( new Point(2, 0, 0),1),
                new Plane(new Point(0, 0, 5), new Vector(0, 0, 1)),
                new Triangle(new Point(1, 1, 5), new Point(2, 2, 5), new Point(1, 2, 6))  // Off-plane triangle
        );
        Ray ray = new Ray(new Point(0, 0, 0), new Vector(1, 0, 0));
        List<Point> intersections = geometries.findIntersections(ray);
        assertEquals(2, intersections.size(), "Expected 2 points from sphere intersection only");
    }

    /**
     * Test when the ray intersects with some of the geometries.
     * Here, the ray intersects a sphere and a triangle.
     * Verifies that all intersection points (3 in total) are found.
     */
    @Test
    public void testSomeIntersections() {
        Geometries geometries = new Geometries(
                new Sphere( new Point(0, 0, 1),1),
                new Triangle(new Point(-1, -1, 2), new Point(1, -1, 2), new Point(0, 1, 2))
        );
        Ray ray = new Ray(new Point(0, 0, -1), new Vector(0, 0, 1));
        List<Point> intersections = geometries.findIntersections(ray);
        assertEquals(3, intersections.size(), "Expected 3 intersections with sphere and triangle");
    }

    /**
     * Test when all geometries are intersected by the ray.
     * The ray intersects a sphere (2 points), a plane (1 point), and a triangle (1 point).
     * Verifies that all 4 intersection points are found.
     */
    @Test
    public void testAllIntersected() {
        Geometries geometries = new Geometries(
                new Sphere( new Point(0, 0, 3),1),
                new Plane(new Point(0, 0, 2), new Vector(0, 0, 1)),
                new Triangle(new Point(-1, 1, 1), new Point(1, 1, 1), new Point(0, -1, 1))
        );
        Ray ray = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        List<Point> intersections = geometries.findIntersections(ray);
        assertEquals(4, intersections.size(), "Expected 4 intersections with all shapes");
}
}