package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere {
    Point center;
    double radius;

    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Vector getNormal(Point point) {
        // וקטור מהמרכז לנקודה על המשטח של הכדור
        Vector normal = point.subtract(center);  // הפחתת הקואורדינטות של המרכז מהנקודה
        return normal.normalize();  // נורמליזציה של הווקטור (הפיכתו לאורכי 1)
    }
}

