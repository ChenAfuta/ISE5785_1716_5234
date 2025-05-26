package renderer;

import geometries.Intersectable;
import primitives.Color;
import primitives.Double3;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * this class represents a simple ray tracer
 */
public class SimpleRayTracer extends RayTracerBase{

    /**
     * constructor gets scene and sets it
     * @param scene
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * Calculates the total color at the intersection point, combining ambient light,
     * emission, and local lighting effects (diffuse + specular).
     * @param intersection the intersection for calculating the color
     * @param ray the viewing ray that hit the geometry
     * @return the resulting color at the intersection point
     */
    private Color calcColor(Intersectable.Intersection intersection, Ray ray) {
        if (!preprocessIntersection(intersection, ray.getDirection()))
            return Color.BLACK;

        Color ambientLightIntensity = scene.ambientLight.getIntensity();
        Double3 attenuationCoefficient = intersection.geometry.getMaterial().kA;

        Color intensity = ambientLightIntensity.scale(attenuationCoefficient);
        return intensity.add(calcColorLocalEffects(intersection));
    }

    @Override
    public Color traceRay(Ray ray) {
        List<Intersectable.Intersection> intersections = scene.geometries.calculateIntersections(ray);

        if (intersections == null)
            return scene.background;
        else {
            Intersectable.Intersection closestPoint = ray.findClosestIntersection(intersections);
            return calcColor(closestPoint, ray);
        }
    }
}
