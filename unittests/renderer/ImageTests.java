package renderer;

import static java.awt.Color.*;

import geometries.*;
import lighting.*;
import primitives.*;
import scene.Scene;
import org.junit.jupiter.api.Test;

/**
 * Custom image test to render a simple house using basic geometries.
 */
public class ImageTests {

    @Test
    public void simpleHouse() {
        Scene scene = new Scene("House scene");

        // גוף הבית – ריבוע
        scene.geometries.add(new Polygon(
                new Point(-50, -50, -100),
                new Point(50, -50, -100),
                new Point(50, 50, -100),
                new Point(-50, 50, -100))
                .setEmission(new Color(150, 75, 0))
                .setMaterial(new Material().setKD(0.6).setKS(0.3).setShininess(100))
        );

        // גג – משולש
        scene.geometries.add(new Triangle(
                new Point(-60, 50, -100),
                new Point(60, 50, -100),
                new Point(0, 90, -100))
                .setEmission(new Color(120, 0, 0))
                .setMaterial(new Material().setKD(0.5).setKS(0.4).setShininess(120))
        );

        // חלון – מלבן קטן
        scene.geometries.add(new Polygon(
                new Point(-20, -10, -99.9),
                new Point(0, -10, -99.9),
                new Point(0, 10, -99.9),
                new Point(-20, 10, -99.9))
                .setEmission(new Color(0, 200, 255))
                .setMaterial(new Material().setKT(0.6))
        );

        // דלת – מלבן אחר
        scene.geometries.add(new Polygon(
                new Point(10, -50, -99.9),
                new Point(30, -50, -99.9),
                new Point(30, -10, -99.9),
                new Point(10, -10, -99.9))
                .setEmission(new Color(100, 50, 0))
                .setMaterial(new Material().setKD(0.6).setKS(0.2).setShininess(100))
        );

        // אור
        scene.lights.add(new SpotLight(new Color(1000, 800, 500),
                new Point(100, 100, 200), new Vector(-1, -1, -2))
                .setKl(0.0004).setKq(0.0000006));

        // מצלמה
        Camera camera = Camera.getBuilder()
                .setLocation(new Point(0, 0, 200))
                .setDirection(Point.ZERO, new Vector(0, 0, -1))
                .setVpSize(150, 150)
                .setVpDistance(100)
                .setResolution(500, 500)
                .setRayTracer(scene, RayTracerType.SIMPLE)
                .build();

        camera.renderImage().writeToImage("simpleHouse");
    }
}

