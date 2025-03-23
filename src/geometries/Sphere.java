package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere {
    Point center;
    double radius;
    public Sphere (Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }
    public Vector getNormal(Point point) {
        return null;
    }
}
