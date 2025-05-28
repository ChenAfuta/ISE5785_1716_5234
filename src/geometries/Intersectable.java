package geometries;

import primitives.*;

import java.util.List;



/**
 * The Intersectable interface defines a method for finding intersections between a ray and geometric objects.
 * It is implemented by classes that represent geometric shapes in 3D space.
 */
public abstract interface Intersectable
{
    public static class Intersection
    {
        public final Geometry geometry;
        public final Point point;

        public Intersection(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }
        @Override
        public String toString() {
            return "Intersection{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }

        @Override
        public equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Intersection that = (Intersection) obj;
            return geometry==that.geometry && point.equals(that.point);
        }

    }
    /**
     * Finds the intersections between a ray and the geometric object.
     *
     * @param ray the ray to check for intersections
     * @return a list of intersection points, or an empty list if there are no intersections
     */

    blic final List<Point> findIntersections(Ray ray) {
    var list = calculateIntersections(ray);
    return list == null ? null : list.stream().map(intersection -> intersection.point).toList();


    protected List<Intersection> calculateIntersectionsHelper(Ray ray);
    public final List<Intersection> calculateIntersections(Ray ray);

}
