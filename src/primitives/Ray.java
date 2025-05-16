package primitives;
import java.util.List;

/**
 * The {@code Ray} class represents a ray in a 3D space.
 * <p>
 * A ray is defined by a starting point and a direction vector.
 */
public final class Ray {

    /**
     * The starting point of the ray.
     */
    private final Point point;

    /**
     * The direction vector of the ray, normalized.
     */
    private final Vector direction;

    /**
     * Constructs a {@code Ray} with the specified starting point and direction vector.
     * <p>
     * The direction vector is normalized during construction.
     *
     * @param p0  the starting point of the ray
     * @param dir the direction vector of the ray
     * @throws IllegalArgumentException if the direction vector is zero
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
     *
     * @return the ray's origin point
     */
    public Point getPoint() {
        return point;
    }

    /**
     * Returns the direction vector of the ray.
     *
     * @return the normalized direction vector
     */
    public Vector getDirection() {
        return direction;
    }

    /**
     * Calculates a point on the ray at a given distance {@code t} from the origin.
     * <p>
     * If {@code t = 0}, returns the origin point.
     *
     * @param t the distance from the ray origin
     * @return a {@link Point} located {@code t} units along the ray's direction
     */
    public Point getPoint(double t) {
        return point.add(direction.scale(t));
    }

    /**
     * Returns a string representation of the ray.
     *
     * @return a string containing the ray's origin and direction
     */
    @Override
    public String toString() {
        return "" + point + "" + direction;
    }

    /**
     * Compares this ray to another object for equality.
     * <p>
     * Two rays are considered equal if their origin points and direction vectors are equal.
     *
     * @param obj the object to compare with
     * @return {@code true} if the object is a {@code Ray} with the same origin and direction, {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Ray ray)) return false;
        return point.equals(ray.point) && direction.equals(ray.direction);
    }

    /**
     * Finds the closest point to the ray's origin from a list of points.
     *
     * @param points a list of {@link Point} objects
     * @return the closest point to the ray's origin, or {@code null} if the list is empty or {@code null}
     */
    public Point findClosestPoint(List<Point> points) {
        if (points == null || points.isEmpty())
            return null;

        Point closest = points.get(0);
        for (int i = 1; i < points.size(); i += 1)
            if (points.get(i).distanceSquared(point) < closest.distanceSquared(point))
                closest = points.get(i);

        return closest;
    }
}
