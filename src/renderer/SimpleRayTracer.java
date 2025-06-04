package renderer;

import geometries.Intersectable.Intersection;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * SimpleRayTracer class is an enhanced implementation of RayTracerBase.
 * It supports reflection, transparency, and global effects using recursion.
 */
public class SimpleRayTracer extends RayTracerBase {
    // הקבוע DELTA הוסר מכאן - הוא נמצא עכשיו במחלקת Ray
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K = Double3.ONE;

    /**
     * Constructs a new SimpleRayTracer with the given scene.
     * @param scene the scene that will be rendered using this ray tracer
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * Recursively calculates the total color at the intersection point.
     * This method includes local lighting effects (diffuse and specular)
     * as well as global effects (reflection and transparency).
     *
     * @param intersection the intersection point with a geometry
     * @param level recursion depth remaining
     * @param k attenuation coefficient
     * @return the computed color at the intersection
     */
    private Color calcColor(Intersection intersection, int level, Double3 k) {
        if (level == 0 || k.lowerThan(MIN_CALC_COLOR_K))
            return intersection.geometry.getEmission();

        Color ambient = scene.ambientLight.getIntensity()
                .scale(intersection.geometry.getMaterial().kA);

        Color localColor = calcColorLocalEffects(intersection);
        Color globalColor = calcGlobalEffects(intersection, level, k);

        return intersection.geometry.getEmission()
                .add(ambient)
                .add(localColor)
                .add(globalColor);
    }

    /**
     * Overload of calcColor to initialize recursion parameters.
     *
     * @param intersection the intersection point
     * @param ray the ray that caused the intersection
     * @return the computed color at the intersection
     */
    private Color calcColor(Intersection intersection, Ray ray) {
        if (!preprocessIntersection(intersection, ray.getDirection()))
            return Color.BLACK;

        return calcColor(intersection, MAX_CALC_COLOR_LEVEL, INITIAL_K);
    }

    /**
     * Constructs a reflection ray based on the law of reflection.
     * Uses the new Ray constructor with point shifting to avoid self-intersection.
     *
     * @param intersection the intersection point
     * @return the reflected ray
     */
    private Ray constructReflectedRay(Intersection intersection) {
        Vector v = intersection.v;
        Vector n = intersection.normal;
        double vn = v.dotProduct(n);
        Vector r = v.subtract(n.scale(2 * vn));

        // שימוש בבנאי החדש - הזזת הנקודה לאורך הנורמל
        return new Ray(intersection.point, r, n);
    }

    /**
     * Constructs a transparency (refracted) ray assuming direction stays unchanged.
     * Uses the new Ray constructor with point shifting to avoid intersection artifacts.
     *
     * @param intersection the intersection point
     * @return the refracted ray
     */
    private Ray constructRefractedRay(Intersection intersection) {
        // שימוש בבנאי החדש - הזזת הנקודה לאורך כיוון הקרן
        return new Ray(intersection.point, intersection.v, intersection.v);
    }

    /**
     * Calculates the specular reflection component at the intersection point
     * based on the Phong reflection model.
     * @param intersection the intersection data including vectors and material
     * @return the specular reflection as a Double3 coefficient
     */
    private Double3 calcSpecular(Intersection intersection) {
        Vector r = intersection.l.subtract(intersection.normal.scale(2 * intersection.lNormal));
        double vr = -1 * intersection.v.dotProduct(r);

        return intersection.material.kS.scale(Math.pow(Math.max(0, vr), intersection.material.nSh));
    }

    /**
     * Calculates the diffuse reflection component at the intersection point
     * based on the Phong reflection model.
     * @param intersection the intersection data including normal and material
     * @return the diffuse reflection as a Double3 coefficient
     */
    private Double3 calcDiffusive(Intersection intersection) {
        return intersection.material.kD.scale(Math.abs(intersection.lNormal));
    }

    /**
     * Calculates the local lighting effects at a given intersection point.
     * This includes the object's emission and contributions from all light sources
     * that affect the point (diffuse and specular reflections).
     * @param intersection the intersection point between a ray and a geometry
     * @return the resulting color from local light effects at the intersection
     */
    private Color calcColorLocalEffects(Intersection intersection) {
        Color color = intersection.geometry.getEmission();
        for (LightSource lightSource : scene.lights) {
            // also checks if sign(lNormal) == sign(vNormal)) and if the intersection is unshaded
            if (!setLightSource(intersection, lightSource) || !unshaded(intersection))
                continue;

            Color iL = lightSource.getIntensity(intersection.point);
            color = color
                    .add(iL.scale(calcDiffusive(intersection)
                            .add(calcSpecular(intersection))));
        }
        return color;
    }

    /**
     * Computes global effects (reflection or transparency).
     * It finds the closest intersection of the secondary ray and calls calcColor recursively.
     *
     * @param ray secondary ray (reflection or transparency)
     * @param level recursion depth remaining
     * @param k attenuation coefficient
     * @return computed global effect color
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 k) {
        Intersection closestIntersection = findClosestIntersection(ray);
        if (closestIntersection == null)
            return scene.background;

        // חיוני! נאתחל את פרטי החיתוך כמו כיוון וקטורים
        if (!preprocessIntersection(closestIntersection, ray.getDirection()))
            return Color.BLACK;

        return calcColor(closestIntersection, level - 1, k);
    }


    /**
     * Calculates the sum of global effects (reflection and transparency).
     * Calls calcGlobalEffect for each secondary ray.
     *
     * @param intersection intersection point with the geometry
     * @param level recursion depth remaining
     * @param k attenuation coefficient
     * @return the combined global effect color
     */
    private Color calcGlobalEffects(Intersection intersection, int level, Double3 k) {
        Color reflectionColor = Color.BLACK;
        Color refractionColor = Color.BLACK;

        Double3 kR = intersection.geometry.getMaterial().kR;
        if (!kR.equals(Double3.ZERO)) {
            Ray reflectedRay = constructReflectedRay(intersection);
            Double3 newK = k.product(kR);
            if (!newK.lowerThan(MIN_CALC_COLOR_K))
                reflectionColor = calcGlobalEffect(reflectedRay, level, newK);
        }

        Double3 kT = intersection.geometry.getMaterial().kT;
        if (!kT.equals(Double3.ZERO)) {
            Ray refractedRay = constructRefractedRay(intersection);
            Double3 newK = k.product(kT);
            if (!newK.lowerThan(MIN_CALC_COLOR_K))
                refractionColor = calcGlobalEffect(refractedRay, level, newK);
        }

        return reflectionColor.add(refractionColor);
    }

    /**
     * Finds the closest intersection of a ray with the scene geometries.
     *
     * @param ray the ray to check
     * @return the closest intersection point, or null if no intersection
     */
    private Intersection findClosestIntersection(Ray ray) {
        List<Intersection> intersections = scene.geometries.calculateIntersections(ray);
        if (intersections == null)
            return null;

        Intersection closest = null;
        double minDistance = Double.MAX_VALUE;
        for (Intersection inter : intersections) {
            double distance = inter.point.distance(ray.getPoint(0));
            if (distance < minDistance) {
                minDistance = distance;
                closest = inter;
            }
        }
        return closest;
    }

    /**
     * Determines whether the intersection point is unshaded.
     * Uses the new Ray constructor with point shifting.
     * Only objects with a transparency coefficient lower than MIN_CALC_COLOR_K cause shadows.
     *
     * @param intersection intersection point and lighting data
     * @return true if unshaded, false if blocked by opaque objects
     */
    /**
     * Determines whether the intersection point is unshaded.
     * Checks if the closest intersection along the shadow ray blocks the light.
     *
     * @param intersection the intersection point and lighting data
     * @return true if unshaded, false otherwise
     */
    private boolean unshaded(Intersection intersection) {
        Ray shadowRay = new Ray(intersection.point, intersection.l.scale(-1), intersection.normal);

        Intersection closestIntersection = findClosestIntersection(shadowRay);

        // If there's no blocking object, the point is unshaded
        return closestIntersection == null || !(closestIntersection.geometry.getMaterial().kT.lowerThan(MIN_CALC_COLOR_K));
}

    /**
     * Initializes intersection properties such as direction and normal.
     *
     * @param intersection intersection data
     * @param rayDirection ray direction vector
     * @return true if dot product is non-zero, false otherwise
     */
    public boolean preprocessIntersection(Intersection intersection, Vector rayDirection) {
        intersection.v = rayDirection;
        intersection.normal = intersection.geometry.getNormal(intersection.point);
        intersection.vNormal = intersection.v.dotProduct(intersection.normal);

        return !isZero(intersection.vNormal);
    }

    /**
     * Sets the light source properties in the intersection data.
     *
     * @param intersection intersection point data
     * @param light light source
     * @return true if light affects the point, false otherwise
     */
    public boolean setLightSource(Intersection intersection, LightSource light) {
        intersection.light = light;
        intersection.l = light.getL(intersection.point);
        intersection.lNormal = intersection.l.dotProduct(intersection.normal);

        return alignZero(intersection.lNormal * intersection.vNormal) > 0;
    }

    /**
     * Traces the ray and computes the closest intersection's color.
     *
     * @param ray the ray to trace
     * @return computed color or background if no intersection
     */
    @Override
    public Color traceRay(Ray ray) {
        Intersection closestIntersection = findClosestIntersection(ray);
        if (closestIntersection == null)
            return scene.background;
        return calcColor(closestIntersection, ray);
    }
}