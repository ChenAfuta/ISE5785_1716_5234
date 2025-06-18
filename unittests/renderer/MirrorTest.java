package renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.*;
import primitives.*;
import scene.Scene;

/**
 * Test class for rendering a scene with a mirror and a sphere.
 * Demonstrates reflection and lighting effects.
 */
public class MirrorTest {
    private final Scene scene = new Scene("Mirror scene");

    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setLocation(new Point(0, 0, 1000))
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setVpDistance(1000)
            .setVpSize(200, 200);

    /**
     * Test rendering a scene with a reflective plane (mirror) and a sphere above it.
     * The scene includes a light source and demonstrates reflection.
     */
    @Test
    public void testSphereAboveMirror() {
        // Add a reflective plane (mirror) to the scene
        scene.geometries.add(
                new Plane(new Point(0, -40, -150), new Vector(0, 1, 0))
                        .setEmission(new Color(200, 200, 200))
                        .setMaterial(new Material()
                                .setKR(1)
                                .setKD(0.2)
                                .setKS(0.4)
                                .setShininess(100))
        );

        // Add a sphere above the mirror
        scene.geometries.add(
                new Sphere(new Point(0, 10, -100), 30)
                        .setEmission(new Color(BLUE))
                        .setMaterial(new Material()
                                .setKR(0.5)
                                .setKD(0.5)
                                .setKS(0.5)
                                .setShininess(100))
        );

        // Add a point light source above the scene
        scene.lights.add(
                new PointLight(new Color(WHITE), new Point(0, 100, 100))
                        .setKl(0.0005)
                        .setKq(0.00005)
        );

        // Render the scene and save the image
        cameraBuilder
                .setResolution(800, 800)
                .setRayTracer(scene, RayTracerType.SIMPLE)
                .build()
                .renderImage()
                .writeToImage("mirrorSphereTest");
    }
}
