package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Triangle extends Polygon {

    /**
     * Constructor for a triangle using three points.
     * @param a first point
     * @param b second point
     * @param c third point
     */
    public Triangle(Point a, Point b, Point c) {
        super(a, b, c);

    }
    @Override
    public List<Point> findIntersections(Ray ray) {
        // קוד לפי אלגוריתם Möller–Trumbore
        Vector edge1 = vertices.get(1).subtract(vertices.get(0));
        Vector edge2 = vertices.get(2).subtract(vertices.get(0));

        Vector h = ray.getDirection().crossProduct(edge2);
        double a = edge1.dotProduct(h);

        if (a > -0.0001 && a < 0.0001)
            return null; // הקרן מקבילה למישור המשולש

        double f = 1.0 / a;
        Vector s = ray.getPoint().subtract(vertices.get(0));
        double u = f * s.dotProduct(h);

        if (u < 0.0 || u > 1.0)
            return null;

        Vector q = s.crossProduct(edge1);
        double v = f * ray.getDirection().dotProduct(q);

        if (v < 0.0 || u + v > 1.0)
            return null;

        double t = f * edge2.dotProduct(q);

        if (t > 0.0001) {
            Point intersectionPoint = ray.getPoint(t);
            return List.of(intersectionPoint);
        }

        return null; // אין חיתוך
    }


}
