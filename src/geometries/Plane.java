package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import java.util.Collections;

public class Plane implements Intersectable {
    private final Point point;
    private final Vector normal;

    public Plane(Point point, Vector normal) {
        this.point = point;
        this.normal = normal.normalize();
    }

    public Plane(Point p1, Point p2, Point p3) {
        if (p1.equals(p2) || p1.equals(p3) || p2.equals(p3)) {
            throw new IllegalArgumentException("Two or more points are identical");
        }

        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);

        if (v1.normalize().equals(v2.normalize()) || v1.normalize().equals(v2.normalize().scale(-1))) {
            throw new IllegalArgumentException("The points are collinear");
        }

        this.point = p1;
        this.normal = v1.crossProduct(v2).normalize();
    }

    public Vector getNormal(Point point) {
        return this.normal;
    }

    public Vector getNormal() {
        return this.normal;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        Vector rayDir = ray.getDirection();
        Point rayOrigin = ray.getPoint();

        double denominator = normal.dotProduct(rayDir);
        if (Math.abs(denominator) < 1e-10) {
            return null; // הקרן מקבילה למישור - אין חיתוך
        }

        Vector p0Q = point.subtract(rayOrigin);
        double t = normal.dotProduct(p0Q) / denominator;

        if (t <= 0) {
            return null; // נקודת החיתוך נמצאת מאחורי הקרן
        }

        Point intersectionPoint = ray.getPoint(t);
        return Collections.singletonList(intersectionPoint);
    }
}
