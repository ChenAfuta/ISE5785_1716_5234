package primitives;

/**
 * Ray class represents a ray in 3D space, defined by a starting point and a direction vector.
 */
public final class Ray {
    private final Point p0;
    private final Vector dir;

    /**
     * Constructs a ray with the given starting point and direction vector.
     * The direction vector is normalized.
     *
     * @param p0 the starting point of the ray
     * @param dir the direction vector of the ray
     */
    public Ray(Point p0, Vector dir) {
        if (dir.equals(Vector.ZERO)) {
            throw new IllegalArgumentException("Direction vector cannot be zero");
        }
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    @Override
    public String toString() {
        return "Ray{" + "p0=" + p0 + ", dir=" + dir + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Ray)) return false;
        Ray other = (Ray) obj;
        return p0.equals(other.p0) && dir.equals(other.dir);
    }

    public Point getP0(){
         return p0;
    }
    public Vector getDir(){
        return dir;
    }
}
