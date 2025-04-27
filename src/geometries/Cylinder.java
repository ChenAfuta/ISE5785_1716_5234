package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import java.util.List;

/**
 * The {@code Cylinder} class represents a finite cylinder in 3D space.
 * A cylinder is defined by a central axis (represented by a ray), a radius, and a height.
 * It extends the {@code Tube} class by limiting the infinite tube to a finite height.
 */
public class Cylinder extends Tube {
    private final double height;

    /**
     * Constructs a {@code Cylinder} with a given axis ray, radius, and height.
     *
     * @param ray    the central axis of the cylinder
     * @param radius the radius of the cylinder
     * @param height the height of the cylinder
     */
    public Cylinder(Ray ray, double radius, double height) {
        super(radius, ray);
        this.height = height;
    }

    /**
     * Returns the height of the cylinder.
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the normal vector to the surface at a given point on the cylinder.
     * The normal is computed based on whether the point is on the bottom base, top base,
     * or on the lateral (side) surface of the cylinder.
     *
     * @param point the point on the surface of the cylinder
     * @return the normal vector at the given point
     */
    @Override
    public Vector getNormal(Point point) {
        Vector dir = axis.getDirection();
        Point p0 = axis.getPoint();

        // Check if point is on the bottom base
        if (point.subtract(p0).dotProduct(dir) == 0) {
            return dir.scale(-1);
        }

        // Compute top base center
        Point top = axis.getPoint(height);

        // Check if point is on the top base
        if (point.subtract(top).dotProduct(dir) == 0) {
            return dir;
        }

        // Otherwise, the point is on the lateral surface
        Point o = axis.getPoint(point.subtract(p0).dotProduct(dir));
        return point.subtract(o).normalize();
    }
}
