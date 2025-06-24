// renderer/StreetSceneBVHNoThreadTest.java
package renderer;

import org.junit.jupiter.api.Test;
import geometries.*;
import lighting.*;
import primitives.*;
import renderer.sampling.SamplingConfig;
import renderer.sampling.SamplingPattern;
import renderer.sampling.TargetShape;
import scene.Scene;

import java.util.ArrayList;
import java.util.List;

public class StreetSceneBVHNoThreadTest {

    private final Scene scene = new Scene("Warm Street Scene");
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setRayTracer(scene, RayTracerType.SIMPLE);

    @Test
    public void streetScene() {
        List<Intersectable> geometries = new ArrayList<>();

        scene.setBackground(new Color(135, 206, 235));

        geometries.add(new Plane(new Point(0, 0, 0), new Vector(0, 1, 0))
                .setEmission(new Color(45, 90, 45))
                .setMaterial(new Material().setKD(0.8).setKS(0.1).setShininess(10)));

        geometries.add(new Polygon(
                new Point(-25, 0.1, 200),
                new Point(25, 0.1, 200),
                new Point(25, 0.1, -500),
                new Point(-25, 0.1, -500))
                .setEmission(new Color(90, 50, 30))
                .setMaterial(new Material().setKD(0.9).setKS(0.05).setShininess(15)));

        geometries.add(new Polygon(
                new Point(-45, 0.8, 200),
                new Point(-25, 0.8, 200),
                new Point(-25, 0.8, -500),
                new Point(-45, 0.8, -500))
                .setEmission(new Color(90, 50, 30))
                .setMaterial(new Material().setKD(0.7).setKS(0.3).setShininess(40)));

        geometries.add(new Polygon(
                new Point(25, 0.8, 200),
                new Point(45, 0.8, 200),
                new Point(45, 0.8, -500),
                new Point(25, 0.8, -500))
                .setEmission(new Color(90, 50, 30))
                .setMaterial(new Material().setKD(0.7).setKS(0.3).setShininess(40)));

        double[] leftTreeZ = {-80, -120, -160, -200, -240, -280};
        for (double zPos : leftTreeZ) {
            createTree(geometries, new Point(-60, 0, zPos), 1.0 + Math.random() * 0.3);
        }
        double[] rightTreeZ = {-70, -110, -150, -190, -230, -270, -310};
        for (double zPos : rightTreeZ) {
            createTree(geometries, new Point(70, 0, zPos), 0.8 + Math.random() * 0.4);
        }

        Color yellow = new Color(255, 220, 120);
        Color brown = new Color(90, 50, 30);
        Color gray = new Color(100, 100, 100);
        Material wallMaterial = new Material().setKD(0.9).setKS(0.2).setShininess(20);

        double x = 0, y = 0, z = -60;
        double width = 50, height = 20, depth = 25;

        geometries.add(new Polygon(
                new Point(x - width/2, y, z + depth/2),
                new Point(x + width/2, y, z + depth/2),
                new Point(x + width/2, y + height, z + depth/2),
                new Point(x - width/2, y + height, z + depth/2))
                .setEmission(yellow).setMaterial(wallMaterial));

        geometries.add(new Polygon(
                new Point(x - width/2, y, z - depth/2),
                new Point(x + width/2, y, z - depth/2),
                new Point(x + width/2, y + height, z - depth/2),
                new Point(x - width/2, y + height, z - depth/2))
                .setEmission(yellow).setMaterial(wallMaterial));

        geometries.add(new Polygon(
                new Point(x + width/2, y, z + depth/2),
                new Point(x + width/2, y, z - depth/2),
                new Point(x + width/2, y + height, z - depth/2),
                new Point(x + width/2, y + height, z + depth/2))
                .setEmission(yellow).setMaterial(wallMaterial));

        geometries.add(new Polygon(
                new Point(x - width/2, y, z + depth/2),
                new Point(x - width/2, y, z - depth/2),
                new Point(x - width/2, y + height, z - depth/2),
                new Point(x - width/2, y + height, z + depth/2))
                .setEmission(yellow).setMaterial(wallMaterial));

        geometries.add(new Triangle(
                new Point(x - width/2, y + height, z + depth/2),
                new Point(x + width/2, y + height, z + depth/2),
                new Point(x, y + height + 15, z + depth/2))
                .setEmission(new Color(150, 30, 30))
                .setMaterial(new Material().setKD(0.7).setKS(0.3).setShininess(50)));

        geometries.add(new Triangle(
                new Point(x - width/2, y + height, z - depth/2),
                new Point(x + width/2, y + height, z - depth/2),
                new Point(x, y + height + 15, z - depth/2))
                .setEmission(new Color(150, 30, 30))
                .setMaterial(new Material().setKD(0.7).setKS(0.3).setShininess(50)));

        geometries.add(new Polygon(
                new Point(x - width/2, y + height, z - depth/2),
                new Point(x - width/2, y + height, z + depth/2),
                new Point(x, y + height + 15, z + depth/2),
                new Point(x, y + height + 15, z - depth/2))
                .setEmission(new Color(150, 30, 30))
                .setMaterial(new Material().setKD(0.7).setKS(0.3).setShininess(50)));

        geometries.add(new Polygon(
                new Point(x + width/2, y + height, z - depth/2),
                new Point(x + width/2, y + height, z + depth/2),
                new Point(x, y + height + 15, z + depth/2),
                new Point(x, y + height + 15, z - depth/2))
                .setEmission(new Color(150, 30, 30))
                .setMaterial(new Material().setKD(0.7).setKS(0.3).setShininess(50)));

        geometries.add(new Polygon(
                new Point(x - 20, y + 7, z + depth/2 + 0.1),
                new Point(x - 12, y + 7, z + depth/2 + 0.1),
                new Point(x - 12, y + 13, z + depth/2 + 0.1),
                new Point(x - 20, y + 13, z + depth/2 + 0.1))
                .setEmission(gray).setMaterial(new Material().setKD(0.7).setKS(0.2).setShininess(20)));

        geometries.add(new Polygon(
                new Point(x + 12, y + 7, z + depth/2 + 0.1),
                new Point(x + 20, y + 7, z + depth/2 + 0.1),
                new Point(x + 20, y + 13, z + depth/2 + 0.1),
                new Point(x + 12, y + 13, z + depth/2 + 0.1))
                .setEmission(gray).setMaterial(new Material().setKD(0.7).setKS(0.2).setShininess(20)));

        geometries.add(new Polygon(
                new Point(x - 5, y, z + depth/2 + 0.1),
                new Point(x + 5, y, z + depth/2 + 0.1),
                new Point(x + 5, y + 10, z + depth/2 + 0.1),
                new Point(x - 5, y + 10, z + depth/2 + 0.1))
                .setEmission(brown).setMaterial(new Material().setKD(0.7).setKS(0.2).setShininess(20)));

        for (int i = 0; i < 6; i++) {
            double zLeft  = -40 - i * 50;
            double zRight = zLeft + 25;
            createStreetLamp(geometries, new Point(-50, 1.2, zLeft));
            createStreetLamp(geometries, new Point( 55, 1.2, zRight));
        }

        Intersectable bvhRoot = BVHNode.build(geometries);
        scene.setGeometries(new Geometries(bvhRoot));

        scene.setAmbientLight(new AmbientLight(new Color(60, 60, 70).scale(0.8)));
        scene.lights.add(new DirectionalLight(new Color(250, 230, 200), new Vector(-1, -1, -0.5)));
        scene.lights.add(new DirectionalLight(new Color(100, 110, 120), new Vector(1, -0.5, 0.7)));
        scene.lights.add(new PointLight(new Color(255, 220, 180), new Point(0, 150, -150))
                .setKl(0.0001).setKq(0.00001).setRadius(25).setNumSamples(81));

        Camera camera = cameraBuilder
                .setLocation(new Point(-15, 12, 60))
                .setDirection(new Vector(0.1, -0.15, -1), new Vector(0, 1, 0))
                .setVpDistance(150)
                .setVpSize(300, 200)
                .setResolution(800, 600)
                .setMultithreading(-2)
                .setDebugPrint(1.0)
                .build();

        camera.setSamplingConfig(new SamplingConfig(
                81, TargetShape.RECTANGLE, SamplingPattern.GRID
        ));

        long tStart = System.currentTimeMillis();
        camera.renderImage();
        long tEnd = System.currentTimeMillis();
        System.out.printf("Render completed in %.3f seconds.%n", (tEnd - tStart) / 1000.0);

        camera.writeToImage("street1");
    }

    private void createTree(List<Intersectable> geometries, Point pos, double scale) {
        double x = pos.getX(), y = pos.getY(), z = pos.getZ();
        Material trunkMaterial = new Material().setKD(0.7).setKS(0.3).setShininess(20);
        Material foliageMaterial = new Material().setKD(0.6).setKS(0.4).setShininess(60);

        geometries.add(new Sphere(new Point(x, y + 1.5 * scale, z), 1.5 * scale)
                .setEmission(new Color(80, 50, 30)).setMaterial(trunkMaterial));
        geometries.add(new Sphere(new Point(x, y + 4.0 * scale, z), 1.2 * scale)
                .setEmission(new Color(90, 60, 40)).setMaterial(trunkMaterial));
        geometries.add(new Sphere(new Point(x, y + 5.0 * scale, z), scale)
                .setEmission(new Color(100, 70, 50)).setMaterial(trunkMaterial));

        geometries.add(new Sphere(new Point(x, y + 10.0 * scale, z), 6.0 * scale)
                .setEmission(new Color(30, 100, 40)).setMaterial(foliageMaterial));
        geometries.add(new Sphere(new Point(x - 2 * scale, y + 8.0 * scale, z - scale), 4.0 * scale)
                .setEmission(new Color(25, 85, 30)).setMaterial(foliageMaterial));
        geometries.add(new Sphere(new Point(x + 3 * scale, y + 9.0 * scale, z + 2 * scale), 3.5 * scale)
                .setEmission(new Color(40, 120, 50)).setMaterial(foliageMaterial));
    }

    private void createStreetLamp(List<Intersectable> geometries, Point pos) {
        double x = pos.getX(), y = pos.getY(), z = pos.getZ();
        geometries.add(new Sphere(new Point(x, y - 0.3, z), 0.8)
                .setEmission(new Color(40, 40, 45))
                .setMaterial(new Material().setKD(0.7).setKS(0.3).setShininess(40)));

        geometries.add(new Polygon(
                new Point(x - 0.8, y + 0.01, z + 0.8),
                new Point(x + 0.8, y + 0.01, z + 0.8),
                new Point(x + 0.8, y + 0.01, z - 0.8),
                new Point(x - 0.8, y + 0.01, z - 0.8))
                .setEmission(new Color(35, 35, 40))
                .setMaterial(new Material().setKD(0.7).setKS(0.3).setShininess(40)));

        for (double h = 0.5; h <= 7.0; h += 0.4) {
            geometries.add(new Sphere(new Point(x, y + h, z), 0.35)
                    .setEmission(new Color(30, 30, 35))
                    .setMaterial(new Material().setKD(0.6).setKS(0.4).setShininess(60)));
        }

        double dir = x < 0 ? +1.0 : -1.0;
        for (double off = 0.4; off <= 2.4; off += 0.4) {
            geometries.add(new Sphere(new Point(x + dir * off, y + 7, z), 0.28)
                    .setEmission(new Color(30, 30, 35))
                    .setMaterial(new Material().setKD(0.6).setKS(0.4).setShininess(60)));
        }

        geometries.add(new Sphere(new Point(x + dir * 2.4, y + 6.8, z), 0.25)
                .setEmission(new Color(40, 40, 45))
                .setMaterial(new Material().setKD(0.7).setKS(0.3).setShininess(40)));
        geometries.add(new Sphere(new Point(x + dir * 2.4, y + 6.5, z), 0.25)
                .setEmission(new Color(40, 40, 45))
                .setMaterial(new Material().setKD(0.7).setKS(0.3).setShininess(40)));
        geometries.add(new Sphere(new Point(x + dir * 2.4, y + 6.0, z), 1.1)
                .setEmission(new Color(25, 25, 30))
                .setMaterial(new Material().setKD(0.8).setKS(0.2).setShininess(30)));
        geometries.add(new Sphere(new Point(x + dir * 2.4, y + 6.0, z), 1.5)
                .setEmission(new Color(255, 230, 180))
                .setMaterial(new Material().setKD(0.7).setKS(0.3).setShininess(10)));
    }
}
