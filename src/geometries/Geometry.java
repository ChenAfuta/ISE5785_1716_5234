package geometries;

import primitives.*;

import java.util.List;

/**
 * The Geometry class is an abstract base class for all geometric objects in a 3D space.
 * It defines a method to calculate the normal vector at a given point on the geometry.
 */
public abstract class Geometry extends Intersectable {

     protected Color emission=Color.BLACK;
    /**
     * Calculates the normal vector to the geometry at a given point.
     * @param point the point on the geometry
     * @return the normal vector to the geometry at the given point
     */
    protected abstract Vector getNormal(Point point);

    public Color getEmission() {
        return emission;
    }
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }
    @Override
    public List<Point> findIntersections(Ray ray){
        return null;
    }
}