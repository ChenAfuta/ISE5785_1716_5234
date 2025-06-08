package renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.*;
import primitives.*;
import scene.Scene;

class ImageTest {

    private final Scene scene = new Scene("House Scene");
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setRayTracer(scene, RayTracerType.SIMPLE);

    @Test
    void houseScene() {
        // יצירת רצפה
        scene.geometries.add(new Plane(new Point(0, -10, 0), new Vector(0, 1, 0))
                .setEmission(new Color(150, 75, 0)));  // חום לרצפה

        // יצירת קירות
        scene.geometries.add(new Sphere(new Point(-5, -5, 0), 5)
                .setEmission(new Color(255, 200, 150))); // קיר ימני - צבע קרם
        scene.geometries.add(new Sphere(new Point(5, -5, 0), 5)
                .setEmission(new Color(255, 200, 150))); // קיר שמאלי - צבע קרם

        // יצירת גג (משולש אדום) - הגדלנו את המשולש
        scene.geometries.add(new Triangle(new Point(-7, 0, 6), new Point(7, 0, 6), new Point(0, 5, 10))
                .setEmission(new Color(255, 0, 0)));  // גג אדום

        // יצירת גוף גלילי (נחזור לכדור, ללא Cylinder)
        scene.geometries.add(new Sphere(new Point(-10, -5, 0), 2)
                .setEmission(new Color(0, 255, 0)));  // גוף ירוק (כדור במקום גליל)

        // יצירת גוף טיוב (נחזור לכדור, ללא Tube)
        scene.geometries.add(new Sphere(new Point(10, -5, 0), 2)
                .setEmission(new Color(0, 0, 255)));  // גוף כחול (כדור במקום טיוב)

        // יצירת שמש (כדור צהוב)
        scene.geometries.add(new Sphere(new Point(0, 10, 15), 3)
                .setEmission(new Color(255, 255, 0)));  // כדור צהוב - שמש

        // הוספת אור סביבה
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255)));

        // הוספת אור ממוקד
        scene.lights.add(new SpotLight(new Color(1000, 600, 0), new Point(0, 0, 10), new Vector(0, 0, -1))
                .setKl(0.0004).setKq(0.0000006));

        // יצירת מצלמה
        cameraBuilder
                .setLocation(new Point(0, 0, 20))
                .setDirection(Point.ZERO, Vector.AXIS_Y)
                .setVpDistance(100)
                .setVpSize(500, 500)
                .setResolution(500, 500)
                .build()
                .renderImage()
                .writeToImage("HouseWithThreeShapes");
    }
}
