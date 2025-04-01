package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * The Cylinder class represents a cylinder in a 3D space.
 * A cylinder is defined by its height and radius.
 */
public class Cylinder extends RadialGeometry {
    private double height;

    /**
     * Constructs a Cylinder with the specified radius and height.
     * @param radius the radius of the cylinder
     * @param height the height of the cylinder
     */
    public Cylinder(double radius, double height) {
        super(radius);
        this.height = height;
    }

    /**
     * Returns the height of the cylinder.
     * @return the height of the cylinder
     */
    public double getHeight() {
        return height;
    }

    /**
     * Calculates the normal vector to the cylinder at a given point.
     * @param point the point on the surface of the cylinder
     * @return the normal vector to the cylinder at the given point
     */
    @Override
    public Vector getNormal(Point point) {
        // Implementation of the normal calculation
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}