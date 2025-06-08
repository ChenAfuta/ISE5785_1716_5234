package renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.*;
import primitives.*;
import scene.Scene;

public class TenShapesWithReflection {
    private final Scene scene = new Scene("Test scene");

    /** Camera builder for the tests with multiple shapes */
    private final Camera.Builder camera = Camera.getBuilder()
            .setLocation(new Point(0, 0, 1000))  // מיקום המצלמה
            .setDirection(Point.ZERO, Vector.AXIS_Y)  // כיוון המצלמה
            .setVpDistance(1000)  // מרחק המצלמה
            .setVpSize(200, 200)  // גודל המצלמה
            .setRayTracer(scene, RayTracerType.SIMPLE);  // הגדרת RayTracer

    @Test
    public void createSceneWithTenShapes() {
        // הגדרת אור סביבה
        scene.setAmbientLight(new AmbientLight(new Color(25, 25, 25)));

        // הוספת הצורות לסצנה (בית, דלת, חלונות, גג וכו')
        scene.geometries.add(
                // בסיס הבית
                new Polygon(new Point(-50, -40, -100), new Point(50, -40, -100), new Point(50, 0, -100), new Point(-50, 0, -100))
                        .setEmission(new Color(RED))
                        .setMaterial(new Material().setKD(new Double3(0.5)).setKS(new Double3(0.5)).setShininess(30)),
                // גג הבית
                new Triangle(new Point(-60, 0, -100), new Point(60, 0, -100), new Point(0, 50, -100))
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(new Double3(0.5)).setKS(new Double3(0.5)).setShininess(30)),
                // דלת הבית
                new Polygon(new Point(-10, -40, -99), new Point(10, -40, -99), new Point(10, -15, -99), new Point(-10, -15, -99))
                        .setEmission(new Color(165, 42, 42)) // צבע חום לדלת
                        .setMaterial(new Material().setKD(new Double3(0.5)).setKS(new Double3(0.5)).setShininess(30)),
                // חלונות
                new Polygon(new Point(-40, -10, -99), new Point(-30, -10, -99), new Point(-30, 0, -99), new Point(-40, 0, -99))
                        .setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKD(new Double3(0.5)).setKS(new Double3(0.5)).setShininess(30)),
                new Polygon(new Point(30, -10, -99), new Point(40, -10, -99), new Point(40, 0, -99), new Point(30, 0, -99))
                        .setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKD(new Double3(0.5)).setKS(new Double3(0.5)).setShininess(30)),
                // ארובה
                new Polygon(new Point(20, 20, -100), new Point(30, 20, -100), new Point(30, 40, -100), new Point(20, 40, -100))
                        .setEmission(new Color(DARK_GRAY))
                        .setMaterial(new Material().setKD(new Double3(0.5)).setKS(new Double3(0.5)).setShininess(30)),
                new Triangle(new Point(15, 40, -100), new Point(35, 40, -100), new Point(25, 50, -100))
                        .setEmission(new Color(DARK_GRAY))
                        .setMaterial(new Material().setKD(new Double3(0.5)).setKS(new Double3(0.5)).setShininess(30)),
                // אדמה
                new Plane(new Point(0, -40, -150), new Vector(0, 1, 0))
                        .setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKR(1).setKD(new Double3(0.5)).setKS(new Double3(0.5)).setShininess(30)),
                // גזע עץ
                new Polygon(new Point(-70, -40, -100), new Point(-65, -40, -100), new Point(-65, 10, -100), new Point(-70, 10, -100))
                        .setEmission(new Color(ORANGE))
                        .setMaterial(new Material().setKD(new Double3(0.5)).setKS(new Double3(0.5)).setShininess(30)),
                // עלי עץ
                new Sphere(new Point(-67.5, 20, -100), 10)
                        .setEmission(new Color(GREEN))
                        .setMaterial(new Material().setKD(new Double3(0.5)).setKS(new Double3(0.5)).setShininess(30)),
                // רקע (שמיים)
                new Plane(new Point(0, 0, -150), new Vector(0, 0, 1))
                        .setEmission(new Color(BLUE)) // צבע שמיים כחול
                        .setMaterial(new Material().setKD(new Double3(0.5)).setKS(new Double3(0.5)).setShininess(30).setKR(0).setKT(0)),
                // שמש
                new Sphere(new Point(50, 100, -100), 20)
                        .setEmission(new Color(YELLOW)) // צבע שמש צהוב
                        .setMaterial(new Material().setKD(new Double3(0.5)).setKS(new Double3(0.5)).setShininess(30).setKR(0).setKT(0))
        );

        // הוספת תאורה לסצנה
        scene.lights.add(new SpotLight(new Color(WHITE), new Point(195, 195, 50), new Vector(-0, -0, -1))
                .setKl(0.00001).setKq(0.000005));

        // הגדרת המצלמה ויצירת התמונה
        camera
                .setResolution(800, 800)  // הגדרת הרזולוציה
                .build()
                .renderImage()
                .writeToImage("TenShapesWithReflection");  // שמירת התמונה בשם המתאים
    }
}
