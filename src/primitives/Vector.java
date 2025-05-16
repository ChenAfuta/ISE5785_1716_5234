package primitives;

/**
 * The {@link Vector} class represents a vector in a 3D space.
 * It provides methods to perform various vector operations such as addition, scaling, dot product, and cross product.
 */
public final class Vector extends Point {

    /**
     * Constructs a {@link Vector} with the specified x, y, and z coordinates.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param z the z-coordinate
     * @throws IllegalArgumentException if the vector is a zero vector
     */
    public Vector(double x, double y, double z)
    {
        this(new Double3(x, y, z));
    }

    /**
     * Constructs a {@link Vector} with the specified coordinates.
     * @param xyz the coordinates of the vector
     * @throws IllegalArgumentException if the vector is a zero vector
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if (this.xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Vector cannot be zero vector");
        }
    }

    /**
     * Adds the specified vector to this vector and returns the resulting vector.
     * @param other the vector to add
     * @return the resulting vector
     */
    public Vector add(Vector other) {
        return new Vector(this.xyz.add(other.xyz));
    }

    /**
     * Scales this vector by the specified scalar and returns the resulting vector.
     * @param scalar the scalar to scale by
     * @return the resulting vector
     */
    public Vector scale(double scalar) {
        return new Vector(this.xyz.scale(scalar));
    }

    /**
     * Calculates the dot product of this vector and the specified vector.
     * @param other the vector to calculate the dot product with
     * @return the dot product
     */
    public double dotProduct(Vector other) {
        return this.xyz.d1() * other.xyz.d1() +
                this.xyz.d2() * other.xyz.d2() +
                this.xyz.d3() * other.xyz.d3();
    }

    /**
     * Calculates the cross product of this vector and the specified vector.
     * @param other the vector to calculate the cross product with
     * @return the resulting vector
     */
    public Vector crossProduct(Vector other) {
        return new Vector(this.xyz.d2() * other.xyz.d3() - this.xyz.d3() * other.xyz.d2(),
                this.xyz.d3() * other.xyz.d1() - this.xyz.d1() * other.xyz.d3(),
                this.xyz.d1() * other.xyz.d2() - this.xyz.d2() * other.xyz.d1());
    }

    /**
     * Calculates the squared length of this vector.
     * @return the squared length
     */
    public double lengthSquared() {
        return this.xyz.d1() * this.xyz.d1() +
                this.xyz.d2() * this.xyz.d2() +
                this.xyz.d3() * this.xyz.d3();
    }

    /**
     * Calculates the length of this vector.
     * @return the length
     */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * Normalizes this vector and returns the resulting unit vector.
     * @return the resulting unit vector
     */
    public Vector normalize() {

        return new Vector(this.xyz.scale(1.0 / this.length()));
    }

    /**
     * Returns a string representation of this vector.
     * @return a string representation of the vector
     */
    @Override
    public String toString() {
        return "Vector{" + "coordinates=" + xyz + '}';
    }

    /**
     * Compares this vector to the specified object for equality.
     * @param obj the object to compare with
     * @return {@code true} if the objects are equal, {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vector vector)) return false;
        return xyz.equals(vector.xyz);
    }
}
