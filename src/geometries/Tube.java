package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube {
    Double radius;
    Ray ray;
    public Tube (Ray ray, Double radius) {
        this.ray = ray;
        this.radius = radius;
    }
    public Vector getNormal(Point point) {
        return null;
    }
}
