package geometries;

import primitives.Point;
import primitives.Vector;

public  class Plane {
    Point point;
    Vector normal;
    public Plane(Point point, Vector normal) {
        this.point = point;
        this.normal = normal.normalize();
    }
    public Plane(Point point, Point otherPoint, Point anotherPoint) {
        this.point = point;
        this.normal = null;
    }
    public Vector getNormal(Point point) {
        return this.normal;
    }

}
