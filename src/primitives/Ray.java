package primitives;

/**
 * The Ray class represents a ray in a 3D space.
 * A ray is defined by a starting point and a direction vector.
 */
public final class Ray {
    private final Point p0;
    private final Vector dir;

    /**
     * Constructs a Ray with the specified starting point and direction vector.
     * The direction vector is normalized.
     * @param p0 the starting point of the ray
     * @param dir the direction vector of the ray
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    @Override
    public String toString() {
        return "" + p0 + "" + dir;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Ray ray)) return false;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }
}