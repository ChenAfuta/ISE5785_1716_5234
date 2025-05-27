package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * The Geometry class is an abstract base class for all geometric objects in a 3D space.
 * It defines a method to calculate the normal vector at a given point on the geometry.
 */
public abstract class Geometry extends Intersectable {
    /**
     * The shining color
     */
    protected Color emission = Color.BLACK;

    /**
     * The material of the geometry
     */
    private Material material = new Material();

    /**
     * Calculates the normal vector to the geometric object at the given point.
     * @param p the point on the surface of the geometric object where the normal is to be calculated
     * @return the normal vector to the geometric object at the given point.
     */
    public abstract Vector getNormal(Point p);

    /**
     * Getter for emission
     * @return the emission
     */
    public Color getEmission() {
        return this.emission;
    }

    /**
     * Set the emission color of the geometry
     * @param emission the emission color to set
     * @return the geometry
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * Getter method for the material of the geometry
     * @return the material of the geometry
     */
    public Material getMaterial() {
        return this.material;
    }

    /**
     * Setter method for the material of the geometry
     * @param material the material of the geometry
     * @return the geometry
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }


}