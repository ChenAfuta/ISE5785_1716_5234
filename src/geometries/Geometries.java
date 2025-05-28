package geometries;

import java.util.LinkedList;
import java.util.List;
import primitives.*;

/**
 * The {@code Geometries} class represents a collection of intersectable geometric objects.
 * It implements the Composite design pattern, allowing to group several geometries
 * into a single composite object that can also be intersected by a ray.
 */
public class Geometries extends Intersectable {
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

    @Override
    protected List<Intersection>  calculateIntersectionsHelper(Ray ray) {
        // List that contains all the intersections
        List<Intersection> intersections = null;

        // Loop that go threw all the geometries and find the intersections
        for (Intersectable geometry : geometries) {
            var geometryIntersections = geometry.calculateIntersections(ray);
            if (geometryIntersections != null)
                if (intersections == null)
                    intersections = new LinkedList<>(geometryIntersections);
                else
                    intersections.addAll(geometryIntersections);
        }
        return intersections;
    }

}
