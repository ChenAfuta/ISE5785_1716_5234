package primitives;

/**
 * Vector class represents a vector in 3D Cartesian coordinate system.
 */
public final class Vector extends Point {

    /**
     * Constructs a vector with the given x, y, and z coordinates.
     * Throws an IllegalArgumentException if the vector is a zero vector.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param z the z-coordinate
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (coordinates.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Vector cannot be zero vector");
        }
    }

    /**
     * Constructs a vector with the given coordinates.
     * Throws an IllegalArgumentException if the vector is a zero vector.
     *
     * @param xyz the coordinates of the vector
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if (coordinates.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Vector cannot be zero vector");
        }
    }

    /**
     * Adds another vector to this vector and returns the resulting vector.
     *
     * @param other the vector to add
     * @return the resulting vector
     */
    public Vector add(Vector other) {
        return new Vector(this.coordinates.add(other.coordinates));
    }

    /**
     * Scales this vector by a scalar and returns the resulting vector.
     *
     * @param scalar the scalar to scale by
     * @return the resulting vector
     */
    public Vector scale(double scalar) {
        return new Vector(this.coordinates.scale(scalar));
    }

    /**
     * Calculates the dot product of this vector and another vector.
     *
     * @param other the other vector
     * @return the dot product
     */
    public double dotProduct(Vector other) {
        return this.coordinates.d1() * other.coordinates.d1() +
                this.coordinates.d2() * other.coordinates.d2() +
                this.coordinates.d3() * other.coordinates.d3();
    }

    /**
     * Calculates the cross product of this vector and another vector.
     *
     * @param other the other vector
     * @return the resulting vector
     */
    public Vector crossProduct(Vector other) {
        return new Vector(this.coordinates.d2() * other.coordinates.d3() - this.coordinates.d3() * other.coordinates.d2(),
                this.coordinates.d3() * other.coordinates.d1() - this.coordinates.d1() * other.coordinates.d3(),
                this.coordinates.d1() * other.coordinates.d2() - this.coordinates.d2() * other.coordinates.d1());
    }

    /**
     * Calculates the squared length of this vector.
     *
     * @return the squared length
     */
    public double lengthSquared() {
        return this.coordinates.d1() * this.coordinates.d1() +
                this.coordinates.d2() * this.coordinates.d2() +
                this.coordinates.d3() * this.coordinates.d3();
    }

    /**
     * Calculates the length of this vector.
     *
     * @return the length
     */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * Normalizes this vector and returns the resulting unit vector.
     *
     * @return the resulting unit vector
     */
    public Vector normalize() {
        return new Vector(this.coordinates.scale(1.0 / this.length()));
    }

    @Override
    public String toString() {
        return "Vector{" + "coordinates=" + coordinates + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vector)) return false;
        Vector other = (Vector) obj;
        return coordinates.equals(other.coordinates);
    }
}