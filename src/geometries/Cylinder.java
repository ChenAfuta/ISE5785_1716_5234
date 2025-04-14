package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

public class Cylinder extends Tube {
    Double height;

    public Cylinder(Ray ray, double radius, double height) {
        super(radius, ray);
        this.height = height;
    }

    public Double getHeight() {
        return height;
    }

    @Override
    public Vector getNormal(Point point) {
        Vector dir = getRay().getDirection();
        Point p0 = getRay().getPoint();

        // בדיקה אם הנקודה נמצאת על הבסיס התחתון
        if (point.subtract(p0).dotProduct(dir) == 0) {
            return dir.scale(-1); // הנורמל לכיוון השלילי של הציר
        }

        // בדיקה אם הנקודה נמצאת על הבסיס העליון
        Point topBase = p0.add(dir.scale(height));
        if (point.subtract(topBase).dotProduct(dir) == 0) {
            return dir; // הנורמל לכיוון החיובי של הציר
        }

        // חישוב נורמל למעטפת
        double projection = point.subtract(p0).dotProduct(dir);
        Point o = p0.add(dir.scale(projection)); // הנקודה הקרובה ביותר על הציר

        Vector normal = point.subtract(o);
        if (normal.length() == 0) { // בדיקה אם קיבלנו וקטור אפס
            throw new IllegalArgumentException("Point is on the cylinder axis, normal is undefined.");
        }

        return normal.normalize();
    }

    private Ray getRay() {
        return axis;
    }

}

