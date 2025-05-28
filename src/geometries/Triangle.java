package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * The {@code Triangle} class represents a triangle in 3D space.
 * <p>
 * A triangle is a special case of a polygon with exactly three vertices.
 * It extends the {@link Polygon} class and inherits its properties and methods.
 */

public class Triangle extends Polygon {

    /**
     * Constructs a triangle defined by three vertices.
     *
     * @param a the first vertex
     * @param b the second vertex
     * @param c the third vertex
     */
    public Triangle(Point a, Point b, Point c) {
        super(a, b, c);
    }

    @Override
    protected List<Intersection> calculateIntersectionsHelper(Ray ray) {
        // test the intersections with triangle’s plane
        final var intersections = plane.findIntersections(ray);
        if (intersections == null)
            return null;

        // Point that represents the ray's head
        final Point rayPoint = ray.getPoint(0);
        // Vector that represents the ray's axis
        final Vector rayVector = ray.getDirection();

        // vector1, vector2, vector3 can't be the ZERO Vector because it happens only if rayPoint = P1/P2/P3,
        // which means the ray begins at the plane and there are no intersections with the plane at all,
        // so we would have exit this method already because of the first condition
        final Vector vector1 = vertices.get(0).subtract(rayPoint);
        final Vector vector2 = vertices.get(1).subtract(rayPoint);
        final Vector vector3 = vertices.get(2).subtract(rayPoint);

        // normal1, normal2, normal3 can't be the ZERO Vector because it happens only if:
        // vector1 and vector2 or vector2 and vector3 or vector3 and vector1
        // are on the same line, which means rayPoint is on one of the triangle's edges,
        // which means the ray begins at the plane and there are no intersections with the plane at all,
        // so we would have exit this method already because of the first condition
        final Vector normal1 = vector1.crossProduct(vector2).normalize();
        final Vector normal2 = vector2.crossProduct(vector3).normalize();
        final Vector normal3 = vector3.crossProduct(vector1).normalize();

        final double s1 = alignZero(rayVector.dotProduct(normal1));
        final double s2 = alignZero(rayVector.dotProduct(normal2));
        final double s3 = alignZero(rayVector.dotProduct(normal3));

        // the point is inside the triangle only if s1, s2 and s3 have the same sign and none of them is 0
        if ((s1>0 && s2>0 && s3>0) || (s1<0 && s2<0 && s3<0)) {
            Point intersectionPoint = intersections.getFirst();
            return List.of(new Intersection(this, intersectionPoint));
        }

        return null;
    }
}
