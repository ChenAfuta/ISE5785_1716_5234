package renderer;

import primitives.*;
import scene.Scene;
import geometries.*;
import lighting.*;
import java.util.ArrayList;
import java.util.List;

public class Miniproject1Test{

    public static void main(String[] args) {
        Scene scene = new Scene("Mini-project 1: Soft Shadows - Modified");
        scene.setBackground(new Color(10, 10, 30));
        scene.setAmbientLight(new AmbientLight(new Color(20, 20, 35)));

        scene.lights.add(new PointLight(new Color(600, 500, 400), new Point(-80, 120, 200))
                .setKl(0.0003).setKq(0.0002));
        scene.lights.add(new DirectionalLight(new Color(80, 120, 160), new Vector(1, -0.8, -1)));
        scene.lights.add(new SpotLight(new Color(400, 250, 150), new Point(100, 150, 120), new Vector(-0.6, -1, -0.8))
                .setKl(0.0001).setKq(0.00003));
        scene.lights.add(new PointLight(new Color(180, 180, 120), new Point(50, 100, -20))
                .setKl(0.0002).setKq(0.0001));

        List<Intersectable> geometries = new ArrayList<>();

        geometries.add(new Plane(new Point(0, -50, 0), new Vector(0, 1, 0))
                .setEmission(new Color(50, 60, 40))
                .setMaterial(new Material().setKD(0.9).setKS(0.2).setShininess(50)));
        geometries.add(new Plane(new Point(0, 0, -200), new Vector(0, 0, 1))
                .setEmission(new Color(30, 40, 70))
                .setMaterial(new Material().setKD(0.8).setKS(0.2).setShininess(30)));
        geometries.add(new Plane(new Point(-150, 0, 0), new Vector(1, 0, 0))
                .setEmission(new Color(60, 40, 70))
                .setMaterial(new Material().setKD(0.85).setKS(0.15).setShininess(25)));

        geometries.add(new Sphere(new Point(-50, 0, -30), 28)
                .setEmission(new Color(220, 110, 40))
                .setMaterial(new Material().setKD(0.5).setKS(0.8).setShininess(150)));

        geometries.add(new Sphere(new Point(40, 20, -110), 30)
                .setEmission(new Color(40, 70, 200))
                .setMaterial(new Material().setKD(0.3).setKS(0.7).setKR(0.5).setShininess(200)));

        geometries.add(new Sphere(new Point(80, 5, -70), 22)
                .setEmission(new Color(100, 220, 100))
                .setMaterial(new Material().setKD(0.2).setKS(0.4).setKT(0.6).setShininess(100)));

        geometries.add(new Triangle(new Point(-120, -30, -60), new Point(-80, -30, -60), new Point(-100, 40, -60))
                .setEmission(new Color(220, 60, 180))
                .setMaterial(new Material().setKD(0.7).setKS(0.3).setShininess(50)));

        geometries.add(new Triangle(new Point(90, -30, -50), new Point(130, -30, -50), new Point(110, 25, -50))
                .setEmission(new Color(100, 200, 100))
                .setMaterial(new Material().setKD(0.75).setKS(0.25).setShininess(40)));

        double radius = 20;
        Point center = new Point(20, -49, -10);
        Point[] hexPoints = new Point[6];
        for (int i = 0; i < 6; i++) {
            double angle = Math.PI * i / 3;
            hexPoints[i] = new Point(
                    center.getX() + radius * Math.cos(angle),
                    center.getY(),
                    center.getZ() + radius * Math.sin(angle)
            );
        }
        geometries.add(new Polygon(hexPoints)
                .setEmission(new Color(80, 200, 200))
                .setMaterial(new Material().setKD(0.8).setKS(0.2).setKR(0.1).setShininess(60)));

        double smallRadius = 12;
        Point smallCenter = new Point(-40, -49, 30);
        Point[] smallHex = new Point[6];
        for (int i = 0; i < 6; i++) {
            double angle = Math.PI * i / 3;
            smallHex[i] = new Point(
                    smallCenter.getX() + smallRadius * Math.cos(angle),
                    smallCenter.getY(),
                    smallCenter.getZ() + smallRadius * Math.sin(angle)
            );
        }
        geometries.add(new Polygon(smallHex)
                .setEmission(new Color(240, 240, 80))
                .setMaterial(new Material().setKD(0.7).setKS(0.3).setShininess(45)));

        scene.geometries.add(geometries.toArray(new Intersectable[0]));

        Camera camera = Camera.getBuilder()
                .setLocation(new Point(-100, 100, 250))
                .setDirection(new Point(0, 0, -100), new Vector(0, 1, 0))
                .setVpSize(300, 300)
                .setVpDistance(200)
                .setResolution(1000, 1000)
                .setRayTracer(scene, RayTracerType.SIMPLE)
                .build();

        camera.renderImage().writeToImage("miniproject1_soft_shadows_modified");
    }
}
