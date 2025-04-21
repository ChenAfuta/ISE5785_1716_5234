package unittests.geometries;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class GeometriesTests {

    @Test
    public void testEmptyGeometries() {
        Geometries geometries = new Geometries();
        Ray ray = new Ray(new Point(0, 0, 0), new Vector(1, 0, 0));
        assertNull(geometries.findIntersections(ray), "Expected null for empty geometries list");
    }

    @Test
    public void testNoIntersection() {
        Geometries geometries = new Geometries(
                new Sphere(1, new Point(5, 5, 0)),
                new Plane(new Point(0, 0, 5), new Vector(0, 0, 1)),
                new Triangle(new Point(1, 1, 5), new Point(2, 5, 5), new Point(3, 1, 5))
        );

        // קרן שמכוונת לכיוון שלמטה ומצד, כדי שלא תהיה חפיפה או הקבלה למישור
        Ray ray = new Ray(new Point(0, 0, 0), new Vector(-1, -1, -1));

        assertNull(geometries.findIntersections(ray), "Expected null when no shapes are intersected");
}

    @Test
    public void testOneIntersection() {
        Geometries geometries = new Geometries(
                new Sphere(1, new Point(2, 0, 0)),
                new Plane(new Point(0, 0, 5), new Vector(0, 0, 1)),
                new Triangle(new Point(1, 1, 5), new Point(2, 2, 5), new Point(1, 2, 6))  // המשולש החדש
        );
        Ray ray = new Ray(new Point(0, 0, 0), new Vector(1, 0, 0));
        List<Point> intersections = geometries.findIntersections(ray);
        assertEquals(2, intersections.size(), "Expected 2 points from sphere intersection only");
}

    @Test
    public void testSomeIntersections() {
        Geometries geometries = new Geometries(
                new Sphere(1, new Point(0, 0, 1)),
                new Triangle(new Point(-1, -1, 2), new Point(1, -1, 2), new Point(0, 1, 2))
                // אין מישור – רק המשולש נמצא ב־z=2
        );
        Ray ray = new Ray(new Point(0, 0, -1), new Vector(0, 0, 1));
        List<Point> intersections = geometries.findIntersections(ray);
        assertEquals(3, intersections.size(), "Expected 3 intersections with sphere and triangle");
    }



    @Test
    public void testAllIntersected() {
        Geometries geometries = new Geometries(
                new Sphere(1, new Point(0, 0, 3)),
                new Plane(new Point(0, 0, 2), new Vector(0, 0, 1)),
                new Triangle(new Point(-1, 1, 1), new Point(1, 1, 1), new Point(0, -1, 1))
        );
        Ray ray = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        List<Point> intersections = geometries.findIntersections(ray);
        assertEquals(4, intersections.size(), "Expected 4 intersections with all shapes");
    }
}
