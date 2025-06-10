package renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.*;
import primitives.*;
import scene.Scene;

/**
 * Test class for rendering a scene with ten shapes and reflection.
 * The scene includes various geometries, lighting, and materials to create a realistic image.
 */
public class TenShapesWithReflection {
    /**
     * The scene to be rendered.
     */
    private final Scene scene = new Scene("Test scene");

    /**
     * Camera builder for the tests with multiple shapes.
     * Configures the camera's location, direction, view plane distance, size, and ray tracer type.
     */
    private final Camera.Builder camera = Camera.getBuilder()
            .setLocation(new Point(0, 0, 1000))  // Camera location
            .setDirection(Point.ZERO, Vector.AXIS_Y)  // Camera direction
            .setVpDistance(1000)  // View plane distance
            .setVpSize(200, 200)  // View plane size
            .setRayTracer(scene, RayTracerType.SIMPLE);  // Ray tracer configuration

    /**
     * Test method to create and render a scene with ten shapes.
     * The scene includes a house, tree, ground, sky, sun, and lighting.
     */
    @Test
    public void createSceneWithTenShapes() {
        // Set ambient light for the scene
        scene.setAmbientLight(new AmbientLight(new Color(25, 25, 25)));
        scene.background = new Color(135, 206, 235); // Light blue background

        // Add geometries to the scene (house, door, windows, roof, etc.)
        scene.geometries.add(
                // Base of the house
                new Polygon(new Point(-50, -40, -100), new Point(50, -40, -100), new Point(50, 0, -100), new Point(-50, 0, -100))
                        .setEmission(new Color(RED))
                        .setMaterial(new Material().setKD(new Double3(0.5)).setKS(new Double3(0.5)).setShininess(30)),
                // Roof of the house
                new Triangle(new Point(-60, 0, -100), new Point(60, 0, -100), new Point(0, 50, -100))
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(new Double3(0.5)).setKS(new Double3(0.5)).setShininess(30)),
                // Door of the house
                new Polygon(new Point(-10, -40, -99), new Point(10, -40, -99), new Point(10, -15, -99), new Point(-10, -15, -99))
                        .setEmission(new Color(165, 42, 42)) // Brown color for the door
                        .setMaterial(new Material().setKD(new Double3(0.5)).setKS(new Double3(0.5)).setShininess(30)),
                // Windows
                new Polygon(new Point(-40, -10, -99), new Point(-30, -10, -99), new Point(-30, 0, -99), new Point(-40, 0, -99))
                        .setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKD(new Double3(0.5)).setKS(new Double3(0.5)).setShininess(30)),
                new Polygon(new Point(30, -10, -99), new Point(40, -10, -99), new Point(40, 0, -99), new Point(30, 0, -99))
                        .setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKD(new Double3(0.5)).setKS(new Double3(0.5)).setShininess(30)),
                // Chimney
                new Polygon(new Point(20, 20, -100), new Point(30, 20, -100), new Point(30, 40, -100), new Point(20, 40, -100))
                        .setEmission(new Color(DARK_GRAY))
                        .setMaterial(new Material().setKD(new Double3(0.5)).setKS(new Double3(0.5)).setShininess(30)),
                new Triangle(new Point(15, 40, -100), new Point(35, 40, -100), new Point(25, 50, -100))
                        .setEmission(new Color(DARK_GRAY))
                        .setMaterial(new Material().setKD(new Double3(0.5)).setKS(new Double3(0.5)).setShininess(30)),
                // Ground
                new Plane(new Point(0, -40, -150), new Vector(0, 1, 0))
                        .setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKR(1).setKD(new Double3(0.5)).setKS(new Double3(0.5)).setShininess(30)),
                // Tree trunk
                new Polygon(new Point(-70, -40, -100), new Point(-65, -40, -100), new Point(-65, 10, -100), new Point(-70, 10, -100))
                        .setEmission(new Color(ORANGE))
                        .setMaterial(new Material().setKD(new Double3(0.5)).setKS(new Double3(0.5)).setShininess(30)),
                // Tree leaves
                new Sphere(new Point(-67.5, 20, -100), 10)
                        .setEmission(new Color(GREEN))
                        .setMaterial(new Material().setKD(new Double3(0.5)).setKS(new Double3(0.5)).setShininess(30)),
                // Sky background
                new Plane(new Point(0, 0, -150), new Vector(0, 0, 1))
                        .setEmission(new Color(BLUE)) // Blue sky color
                        .setMaterial(new Material().setKD(new Double3(0.5)).setKS(new Double3(0.5)).setShininess(30).setKR(0).setKT(0)),
                // Sun
                new Sphere(new Point(50, 100, -100), 20)
                        .setEmission(new Color(YELLOW)) // Yellow sun color
                        .setMaterial(new Material().setKD(new Double3(0.5)).setKS(new Double3(0.5)).setShininess(30).setKR(0).setKT(0))
        );

        // Add lighting to the scene
        scene.lights.add(new SpotLight(new Color(WHITE), new Point(195, 195, 50), new Vector(-0, -0, -1))
                .setKl(0.00001).setKq(0.000005));

        // Configure the camera and render the image
        camera
                .setResolution(800, 800)  // Set resolution
                .build()
                .renderImage()
                .writeToImage("TenShapesWithReflection");  // Save the image with the appropriate name
    }
}
