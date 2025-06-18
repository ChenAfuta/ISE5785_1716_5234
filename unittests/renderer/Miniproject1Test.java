package renderer;

import primitives.*;
import scene.Scene;
import geometries.*;
import lighting.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates a scene with soft shadows and various geometries.
 * The scene includes a house, a cube, a triangular prism, spheres, and lighting effects.
 * The rendered image is saved to a file.
 */
public class Miniproject1Test {

    /**
     * Main method to create and render the scene.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scene scene = new Scene("Mini-project 1: Soft Shadows - Modified");
        scene.setBackground(new Color(10, 10, 30));
        scene.setAmbientLight(new AmbientLight(new Color(40, 40, 60)));

        // Lights
        scene.lights.add(new PointLight(new Color(800, 600, 500), new Point(60, 150, 100))
                .setKl(0.0002).setKq(0.0001));
        scene.lights.add(new DirectionalLight(new Color(300, 300, 250), new Vector(-1, -1, -0.8)));

        List<Intersectable> geometries = new ArrayList<>();

        // Floor and walls
        geometries.add(new Plane(new Point(0, -50, 0), new Vector(0, 1, 0))
                .setEmission(new Color(80, 70, 60))
                .setMaterial(new Material().setKD(0.9).setKS(0.3).setShininess(100).setKR(0.3)));
        geometries.add(new Plane(new Point(0, 0, -300), new Vector(0, 0, 1))
                .setEmission(new Color(30, 30, 40))
                .setMaterial(new Material().setKD(0.6).setKS(0.2).setShininess(60)));
        geometries.add(new Plane(new Point(-150, 0, 0), new Vector(1, 0, 0))
                .setEmission(new Color(60, 50, 70))
                .setMaterial(new Material().setKD(0.7).setKS(0.3).setShininess(70)));

        // House
        double baseSize = 60;
        double baseHeight = 60;
        double baseY = -50;
        double baseZ = -100;
        Color baseColor = new Color(60, 160, 120);
        Material houseMat = new Material().setKD(0.65).setKS(0.25).setShininess(70).setKR(0.15);

        Point p1 = new Point(-baseSize, baseY, baseZ - baseSize);
        Point p2 = new Point(baseSize, baseY, baseZ - baseSize);
        Point p3 = new Point(baseSize, baseY, baseZ + baseSize);
        Point p4 = new Point(-baseSize, baseY, baseZ + baseSize);
        Point p5 = new Point(-baseSize, baseY + baseHeight, baseZ - baseSize);
        Point p6 = new Point(baseSize, baseY + baseHeight, baseZ - baseSize);
        Point p7 = new Point(baseSize, baseY + baseHeight, baseZ + baseSize);
        Point p8 = new Point(-baseSize, baseY + baseHeight, baseZ + baseSize);

        geometries.add(new Polygon(p1, p2, p3, p4).setEmission(baseColor).setMaterial(houseMat));
        geometries.add(new Polygon(p5, p6, p7, p8).setEmission(baseColor).setMaterial(houseMat));
        geometries.add(new Polygon(p1, p2, p6, p5).setEmission(baseColor).setMaterial(houseMat));
        geometries.add(new Polygon(p2, p3, p7, p6).setEmission(baseColor).setMaterial(houseMat));
        geometries.add(new Polygon(p3, p4, p8, p7).setEmission(baseColor).setMaterial(houseMat));
        geometries.add(new Polygon(p4, p1, p5, p8).setEmission(baseColor).setMaterial(houseMat));

        // Roof
        Point roofTop = new Point(0, baseY + baseHeight + 40, baseZ);
        Color roofColor = new Color(200, 100, 60);
        Material roofMat = new Material().setKD(0.6).setKS(0.4).setShininess(100);
        geometries.add(new Triangle(p5, p6, roofTop).setEmission(roofColor).setMaterial(roofMat));
        geometries.add(new Triangle(p6, p7, roofTop).setEmission(roofColor).setMaterial(roofMat));
        geometries.add(new Triangle(p7, p8, roofTop).setEmission(roofColor).setMaterial(roofMat));
        geometries.add(new Triangle(p8, p5, roofTop).setEmission(roofColor).setMaterial(roofMat));

        // Cube shape
        Point c1 = new Point(-90, -50, 20);
        Point c2 = new Point(-70, -50, 20);
        Point c3 = new Point(-70, -30, 20);
        Point c4 = new Point(-90, -30, 20);
        Point c5 = new Point(-90, -50, 40);
        Point c6 = new Point(-70, -50, 40);
        Point c7 = new Point(-70, -30, 40);
        Point c8 = new Point(-90, -30, 40);

        Color cubeColor = new Color(255, 120, 180);
        Material cubeMat = new Material().setKD(0.6).setKS(0.3).setShininess(70).setKR(0.2);

        geometries.add(new Polygon(c1, c2, c3, c4).setEmission(cubeColor).setMaterial(cubeMat));
        geometries.add(new Polygon(c5, c6, c7, c8).setEmission(cubeColor).setMaterial(cubeMat));
        geometries.add(new Polygon(c1, c2, c6, c5).setEmission(cubeColor).setMaterial(cubeMat));
        geometries.add(new Polygon(c2, c3, c7, c6).setEmission(cubeColor).setMaterial(cubeMat));
        geometries.add(new Polygon(c3, c4, c8, c7).setEmission(cubeColor).setMaterial(cubeMat));
        geometries.add(new Polygon(c4, c1, c5, c8).setEmission(cubeColor).setMaterial(cubeMat));

        // Triangular prism
        Point t1 = new Point(60, -50, 60);
        Point t2 = new Point(80, -50, 60);
        Point t3 = new Point(70, -30, 60);
        Point t4 = new Point(60, -50, 80);
        Point t5 = new Point(80, -50, 80);
        Point t6 = new Point(70, -30, 80);
        Color triColor = new Color(100, 255, 200);
        Material triMat = new Material().setKD(0.5).setKS(0.3).setShininess(60).setKT(0.3);

        geometries.add(new Triangle(t1, t2, t3).setEmission(triColor).setMaterial(triMat));
        geometries.add(new Triangle(t4, t5, t6).setEmission(triColor).setMaterial(triMat));
        geometries.add(new Polygon(t1, t2, t5, t4).setEmission(triColor).setMaterial(triMat));
        geometries.add(new Polygon(t2, t3, t6, t5).setEmission(triColor).setMaterial(triMat));
        geometries.add(new Polygon(t3, t1, t4, t6).setEmission(triColor).setMaterial(triMat));

        // Spheres near the house
        geometries.add(new Sphere(new Point(-30, -20, -80), 20)
                .setEmission(new Color(230, 80, 220))
                .setMaterial(new Material().setKD(0.5).setKS(0.5).setShininess(100)));

        geometries.add(new Sphere(new Point(30, -20, -90), 25)
                .setEmission(new Color(220, 240, 255))
                .setMaterial(new Material().setKD(0.4).setKS(0.5).setKT(0.7).setShininess(200)));

        // Extra two spheres from original version
        geometries.add(new Sphere(new Point(-100, -20, -90), 20)
                .setEmission(new Color(250, 100, 160))
                .setMaterial(new Material().setKD(0.6).setKS(0.4).setShininess(80)));

        geometries.add(new Sphere(new Point(100, -25, -50), 20)
                .setEmission(new Color(80, 200, 200))
                .setMaterial(new Material().setKD(0.4).setKS(0.4).setKT(0.5).setKR(0.4).setShininess(100)));

        scene.geometries.add(geometries.toArray(new Intersectable[0]));

        Camera camera = Camera.getBuilder()
                .setLocation(new Point(-100, 120, 250))
                .setDirection(new Point(0, 0, -100), new Vector(0, 1, 0))
                .setVpSize(300, 300)
                .setVpDistance(200)
                .setResolution(1000, 1000)
                .setRayTracer(scene, RayTracerType.SIMPLE)
                .build();

        camera.renderImage().writeToImage("miniproject1_soft_shadows_modified");
    }
}
