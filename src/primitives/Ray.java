package primitives;

/**
 * The Ray class represents a ray in a 3D space.
 * A ray is defined by a starting point and a direction vector.
 */
public final class Ray {
    private final Point point;
    private final Vector direction;

    /**
     * Constructs a Ray with the specified starting point and direction vector.
     * The direction vector is normalized.
     * @param p0 the starting point of the ray
     * @param dir the direction vector of the ray
     */
    public Ray(Point p0, Vector dir) {
        if (dir.equals(Vector.ZERO)) {
            throw new IllegalArgumentException("Direction vector cannot be zero");
        }
        this.point = p0;
        this.direction = dir.normalize();
    }

    /**
     * Returns the starting point of the ray.
     * @return the ray's origin point
     */
    public Point getPoint() {
        return point;
    }

    /**
     * Returns the direction vector of the ray.
     * @return normalized direction vector
     */
    public Vector getDirection() {
        return direction;
    }

    /**
     * Calculates a point on the ray at a given distance t from the origin.
     * If t = 0, returns the origin point.
     * @param t distance from the ray origin
     * @return a Point located t units along the ray's direction
     */
    public Point getPoint(double t) {
        return point.add(direction.scale(t));
    }

    @Override
    public String toString() {
        return "" + point + "" + direction;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Ray ray)) return false;
        return point.equals(ray.point) && direction.equals(ray.direction);
    }
}
