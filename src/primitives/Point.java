package primitives;

/**
 * The {@code Point} class represents a point in a 3D space.
 * It provides methods for operations such as addition, subtraction, and distance calculation.
 */
public class Point {
    /** The 3D coordinates of the point. */
    protected final Double3 xyz;

    /** A constant representing the origin point (0,0,0). */
    public static final Point ZERO = new Point(new Double3(0, 0, 0));

    /**
     * Constructs a {@code Point} with the specified x, y, and z coordinates.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param z the z-coordinate
     */
    public Point(double x, double y, double z) {
        this.xyz = new Double3(x, y, z);
    }

    /**
     * Constructs a {@code Point} with the specified {@link primitives.Double3} coordinates.
     * @param xyz the 3D coordinates of the point
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * Subtracts the specified {@code Point} from this point and returns the resulting {@link primitives.Vector}.
     * @param other the point to subtract
     * @return the resulting vector
     */
    public Vector subtract(Point other) {
        return new Vector(this.xyz.subtract(other.xyz));
    }

    /**
     * Adds the specified {@link primitives.Vector} to this point and returns the resulting {@code Point}.
     * @param other the vector to add
     * @return the resulting point
     */
    public Point add(Vector other) {
        return new Point(this.xyz.add(other.xyz));
    }

    /**
     * Calculates the squared distance between this point and the specified {@code Point}.
     * @param other the point to calculate the distance to
     * @return the squared distance
     */
    public double distanceSquared(Point other) {
        return (this.xyz.d1() - other.xyz.d1()) * (this.xyz.d1() - other.xyz.d1()) +
                (this.xyz.d2() - other.xyz.d2()) * (this.xyz.d2() - other.xyz.d2()) +
                (this.xyz.d3() - other.xyz.d3()) * (this.xyz.d3() - other.xyz.d3());
    }

    /**
     * Calculates the distance between this point and the specified {@code Point}.
     * @param other the point to calculate the distance to
     * @return the distance
     */
    public double distance(Point other) {
        return Math.sqrt(this.distanceSquared(other));
    }

    /**
     * Returns a string representation of the point.
     * @return a string representing the point
     */
    @Override
    public String toString() {
        return "" + xyz;
    }

    /**
     * Compares this point to the specified object for equality.
     * @param obj the object to compare with
     * @return {@code true} if the objects are equal; {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Point point)) return false;
        return xyz.equals(point.xyz);
    }
}
