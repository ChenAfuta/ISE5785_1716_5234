package renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.*;
import primitives.*;
import scene.Scene;

/**
 * בדיקת השתקפות פשוטה: כדור מעל מראה
 */
public class MirrorTest {
    private final Scene scene = new Scene("Mirror scene");

    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setLocation(new Point(0, 0, 1000))
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setVpDistance(1000)
            .setVpSize(200, 200);

    @Test
    public void testSphereAboveMirror() {
        // מראה - מישור משקף
        scene.geometries.add(
                new Plane(new Point(0, -40, -150), new Vector(0, 1, 0))
                        .setEmission(new Color(200, 200, 200))
                        .setMaterial(new Material()
                                .setKR(1)
                                .setKD(0.2)
                                .setKS(0.4)
                                .setShininess(100))
        );

        // כדור מעל המראה
        scene.geometries.add(
                new Sphere(new Point(0, 10, -100), 30)
                        .setEmission(new Color(BLUE))
                        .setMaterial(new Material()
                                .setKR(0.5)
                                .setKD(0.5)
                                .setKS(0.5)
                                .setShininess(100))
        );

        // אור ברור מלמעלה
        scene.lights.add(
                new PointLight(new Color(WHITE), new Point(0, 100, 100))
                        .setKl(0.0005)
                        .setKq(0.00005)
        );

        // רנדר
        cameraBuilder
                .setResolution(800, 800)
                .setRayTracer(scene, RayTracerType.SIMPLE)
                .build()
                .renderImage()
                .writeToImage("mirrorSphereTest");
    }
}
