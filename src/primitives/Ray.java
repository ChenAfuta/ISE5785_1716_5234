package primitives;

import geometries.Intersectable;

import java.util.List;

import static primitives.Util.isZero;
import geometries.Intersectable.Intersection;

/**
 * The {@code Ray} class represents a ray in a 3D space.
 * <p>
 * A ray is defined by a starting point and a direction vector.
 */
public final class Ray {

    /**
     * The starting point of the ray.
     */
    private final Point head;

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
        this.head = p0;
        this.direction = dir.normalize();
    }

    /**
     * Returns the starting point of the ray.
     *
     * @return the ray's origin point
     */
    public Point getHead() {
        return head;
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
        if (isZero(t))
            return head;
        return head.add(direction.scale(t));
    }

    /**
     * Returns a string representation of the ray.
     *
     * @return a string containing the ray's origin and direction
     */
    @Override
    public String toString() {
        return "" + head + "" + direction;
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
        return head.equals(ray.head) && direction.equals(ray.direction);
    }

    /**
     * Calling to a function that find the closest intersection to the head of the ray.
     * When this method calls that function - list of intersections is made, the geometry is null.
     * @param points list of points for check the closest to the head of the ray.
     * @return the closest point to the head of the ray from the list of points, or null if none exist.
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestIntersection(
                points.stream().map(p -> new Intersectable.Intersection(null, p)).toList()).point;
    }

    /**
     * Calculating which of the intersections in a given list is the closest to the head of the ray.
     * If the list is null or empty, returns null.
     * @param intersections list of intersections.
     * @return the closest intersection to the head of the ray from the list of intersections, or null if none exist.
     */
    public Intersectable.Intersection findClosestIntersection(List<Intersectable.Intersection> intersections) {
        // if the list is not initialized, there is no intersection that is the closest - return null.
        if (intersections == null)
            return null;

        // initialized to null - if the list is empty will stay null.
        Intersectable.Intersection closestIntersection = null;

        for(Intersectable.Intersection intersection : intersections) {
            // if this is the first intersection - change the intersection,
            // or it's closer than the closest intersection for now, so this is the new closest intersection.
            if (closestIntersection == null ||
                    p.distanceSquared(intersection.point) < p.distanceSquared(closestIntersection.point))
                closestIntersection = intersection;
        }

        return closestIntersection;
    }
    /**
     * Finds the closest point to the ray's origin from a list of points.
     *
     * @param points a list of {@link Point} objects
     * @return the closest point to the ray's origin, or {@code null} if the list is empty or {@code null}
     */
    public Point findClosestPoint(List<Point> points) {
        if (points == null)
            return null;

        Point closest = points.get(0);
        double maxDistance = closest.distanceSquared(head);

        for (int i = 1; i < points.size(); i += 1) {
            double distance = points.get(i).distanceSquared(head);
            if (distance < maxDistance) {
                closest = points.get(i);
                maxDistance = distance;
            }
        }

        return closest;
    }
}
