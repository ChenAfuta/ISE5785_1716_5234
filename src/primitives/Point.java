package primitives;

import primitives.Vector;

/**
 * Point class represents a point in 3D Cartesian coordinate system.
 */
public class Point {
    protected Double3 coordinates;

    /** Constant representing the origin point (0,0,0) */
    public static final Point ZERO = new Point(new Double3(0, 0, 0));

    /**
     * Constructs a point with the given x, y, and z coordinates.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param z the z-coordinate
     */
    public Point(double x, double y, double z) {
        this.coordinates = new Double3(x, y, z);
    }

    /**
     * Constructs a point with the given coordinates.
     *
     * @param coordinates the coordinates of the point
     */
    public Point(Double3 coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Subtracts another point from this point and returns the resulting vector.
     *
     * @param other the point to subtract
     * @return the resulting vector
     */
    public Vector subtract(Point other) {
        return new Vector(this.coordinates.subtract(other.coordinates));
    }

    /**
     * Adds a vector to this point and returns a new point.
     *
     * @param other the vector to add to this point
     * @return a new point which is the result of adding the given vector to this point
     */
    public Point add(Vector other) {
        return new Point(this.coordinates.add(other.coordinates));
    }

    /**
     * Calculates the squared distance between this point and another point.
     *
     * @param other the other point
     * @return the squared distance between the points
     */
    public double distanceSquared(Point other) {
        return (this.coordinates.d1() - other.coordinates.d1()) * (this.coordinates.d1() - other.coordinates.d1()) +
                (this.coordinates.d2() - other.coordinates.d2()) * (this.coordinates.d2() - other.coordinates.d2()) +
                (this.coordinates.d3() - other.coordinates.d3()) * (this.coordinates.d3() - other.coordinates.d3());
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other the other point
     * @return the distance between the points
     */
    public double distance(Point other) {
        return Math.sqrt(this.distanceSquared(other));
    }

    @Override
    public String toString() {
        return "Point{" + "coordinates=" + coordinates + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Point)) return false;
        Point other = (Point) obj;
        return this.coordinates.equals(other.coordinates);
    }
}