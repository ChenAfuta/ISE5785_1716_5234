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
     * @param point the starting point of the ray
     * @param direction the direction vector of the ray
     */
    public Ray(Point point, Vector direction) {
        if (direction.equals(Vector.ZERO)) {
            throw new IllegalArgumentException("Direction vector cannot be zero");
        }
        this.point = point;
        this.direction = direction.normalize();
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
    public Point getPoint() {
        return point;
    }
    public Point getPoint(double t) {
        return point.add(direction.scale(t));
    }
    public Vector getDirection(){
        return direction;
    }
}
