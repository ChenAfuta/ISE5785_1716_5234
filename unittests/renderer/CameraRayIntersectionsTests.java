package renderer;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import scene.Scene;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the {@link renderer.Camera} class and {@link geometries.Intersectable} interface.
 * This class tests the intersection calculations between camera rays and various geometries.
 */
public class CameraRayIntersectionsTests {

    /**
     * Asserts the count of intersections between a given {@link renderer.Camera} and an {@link geometries.Intersectable} geometry
     * when rays are cast from the camera to the view plane.
     *
     * @param camera   The {@link renderer.Camera} instance used to cast rays.
     * @param geometry The {@link geometries.Intersectable} geometry to test for intersections.
     * @param expected The expected count of intersections.
     * @param msg      An optional message to be displayed in case of assertion failure.
     */
    private void assertEqualsCountOfIntersections(Camera camera, Intersectable geometry, int expected, String msg) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                var intersections = geometry.findIntersections(camera.constructRay(3, 3, j, i));
                count += intersections == null ? 0 : intersections.size();
            }
        }
        assertEquals(expected, count, msg);
    }

    /**
     * A {@link renderer.Camera.Builder} instance used to configure and build the camera for the tests.
     * The camera is configured with a view plane size of 3x3 and a distance of 1 from the view plane.
     */
    private final Camera.Builder camera = Camera.getBuilder()
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setVpSize(3, 3)
            .setVpDistance(1)
            .setImageWriter(new ImageWriter("test", 1, 1))
            .setRayTracer(new SimpleRayTracer(new Scene("Test")));

    /**
     * Tests the intersection calculations between a {@link renderer.Camera} and {@link geometries.Sphere}.
     */
    @Test
    void CameraSphereIntersections() {
        // TC01: Camera and view plane is before the sphere.
        assertEqualsCountOfIntersections(camera.setLocation(Point.ZERO).build(),
                new Sphere(1, new Point(0, 0, -3)), 2,
                "Wrong number of camera's intersection points in sphere (TC01).");

        // TC02: Camera is before the sphere and view plane is in the sphere (the sphere is big).
        assertEqualsCountOfIntersections(camera.setLocation(new Point(0, 0, 0.5)).build(),
                new Sphere(2.5, new Point(0, 0, -2.5)), 18,
                "Wrong number of camera's intersection points in sphere (TC02).");

        // TC03: Camera is before the sphere and view plane is in the sphere (the sphere is small).
        assertEqualsCountOfIntersections(camera.setLocation(new Point(0, 0, 0.5)).build(),
                new Sphere(2, new Point(0, 0, -2)), 10,
                "Wrong number of camera's intersection points in sphere (TC03).");

        // TC04: Camera and view plane is in the sphere.
        assertEqualsCountOfIntersections(camera.setLocation(new Point(0, 0, 0.5)).build(),
                new Sphere(4, new Point(0, 0, -2)), 9,
                "Wrong number of camera's intersection points in sphere (TC04).");

        // TC05: Camera and view plane is after the sphere.
        assertEqualsCountOfIntersections(camera.setLocation(new Point(0, 0, 0.5)).build(),
                new Sphere(0.5, new Point(0, 0, 1)), 0,
                "Wrong number of camera's intersection points in sphere (TC05).");
    }

    /**
     * Tests the intersection calculations between a {@link renderer.Camera} and {@link geometries.Plane}.
     */
    @Test
    void CameraPlaneIntersections() {
        // TC01: Plane is parallel to Camera and view plane.
        assertEqualsCountOfIntersections(camera.setLocation(new Point(0, 0, 1)).build(),
                new Plane(new Point(0, 5, -2), new Point(1, -3, -2), new Point(5, -3, -2)), 9,
                "Wrong number of camera's intersection points in plane (TC01).");

        // TC02: Plane is not parallel to Camera and view plane.
        assertEqualsCountOfIntersections(camera.setLocation(new Point(0, 0, 1)).build(),
                new Plane(new Point(0, 5, 0), new Point(1, -3, -2), new Point(5, -3, -2)), 9,
                "Wrong number of camera's intersection points in plane (TC02).");

        // TC03: Plane is parallel to Camera and view plane, and the pixels in the bottom row do not create intersections.
        assertEqualsCountOfIntersections(camera.setLocation(new Point(0, 0, 1)).build(),
                new Plane(new Point(0, 0, -2), new Point(0, -2, -4), new Point(-2, 0, -2)), 6,
                "Wrong number of camera's intersection points in plane (TC03).");
    }

    /**
     * Tests the intersection calculations between a {@link renderer.Camera} and {@link geometries.Triangle}.
     */
    @Test
    void CameraTriangleIntersections() {
        // TC01: Triangle is short.
        assertEqualsCountOfIntersections(camera.setLocation(new Point(0, 0, 1)).build(),
                new Triangle(new Point(0, 1, -2), new Point(1, -1, -2), new Point(-1, -1, -2)), 1,
                "Wrong number of camera's intersection points in triangle (TC01).");

        // TC02: Triangle is long.
        assertEqualsCountOfIntersections(camera.setLocation(new Point(0, 0, 1)).build(),
                new Triangle(new Point(0, 20, -2), new Point(1, -1, -2), new Point(-1, -1, -2)), 2,
                "Wrong number of camera's intersection points in triangle (TC02).");
    }
}
