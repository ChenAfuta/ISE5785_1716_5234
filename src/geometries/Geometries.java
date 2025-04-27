package geometries;

import java.util.LinkedList;
import java.util.List;
import primitives.*;

/**
 * The {@code Geometries} class represents a collection of intersectable geometric objects.
 * It implements the Composite design pattern, allowing to group several geometries
 * into a single composite object that can also be intersected by a ray.
 */
public class Geometries implements Intersectable {
    private final List<Intersectable> geometries = new LinkedList<>();

    /**
     * Constructs an empty {@code Geometries} collection.
     */
    public Geometries() {
        // Empty constructor - initializes an empty list
    }

    /**
     * Constructs a {@code Geometries} collection initialized with the given geometries.
     *
     * @param geometries one or more {@code Intersectable} objects to add to the collection
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * Adds one or more {@code Intersectable} objects to the collection.
     *
     * @param geometries one or more geometries to add
     */
    public void add(Intersectable... geometries) {
        for (Intersectable geometry : geometries) {
            this.geometries.add(geometry);
        }
    }

    /**
     * Finds all intersection points of the given ray with the geometries in the collection.
     * This method implements the Composite design pattern by iterating over all contained
     * geometries and collecting their intersections.
     *
     * @param ray the ray to intersect with the geometries
     * @return a list of intersection points, or {@code null} if there are no intersections
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersections =null;
        for (Intersectable geometry : geometries) {
            List<Point> geometryIntersections = geometry.findIntersections(ray);
            if (geometryIntersections != null) {
                if (intersections == null) {
                    intersections = new LinkedList<>();
                }
                intersections.addAll(geometryIntersections);
            }
        }
        return intersections.isEmpty()?null:intersections;
    }
}
