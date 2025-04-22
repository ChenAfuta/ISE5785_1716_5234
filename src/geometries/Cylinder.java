package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

public class Cylinder extends Tube {
    private final double height;

    public Cylinder(Ray ray, double radius, double height) {
        super(radius, ray);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public Vector getNormal(Point point) {
        Vector dir = axis.getDirection();
        Point p0 = axis.getPoint();

        if (point.subtract(p0).dotProduct(dir) == 0) {
            return dir.scale(-1);
        }

        Point top = axis.getPoint(height);
        if (point.subtract(top).dotProduct(dir) == 0) {
            return dir;
        }

        Point o = axis.getPoint(point.subtract(p0).dotProduct(dir));
        return point.subtract(o).normalize();
    }


}