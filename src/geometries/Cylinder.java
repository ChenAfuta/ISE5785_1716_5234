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

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> result = new ArrayList<>();

        List<Point> tubeInters = super.findIntersections(ray);
        if (tubeInters != null) {
            for (Point p : tubeInters) {
                double t = axis.getDirection().dotProduct(p.subtract(axis.getPoint()));
                if (t >= 0 && t <= height) {
                    result.add(p);
                }
            }
        }

        // חישוב חיתוך עם בסיס תחתון
        Vector v = ray.getDirection();
        Point p0 = ray.getPoint();
        Vector dir = axis.getDirection();

        double denom = v.dotProduct(dir);
        if (!primitives.Util.isZero(denom)) {
            double t = axis.getPoint().subtract(p0).dotProduct(dir) / denom;
            if (t >= 0) {
                Point p = ray.getPoint(t);
                if (p.subtract(axis.getPoint()).lengthSquared() <= radius * radius) {
                    result.add(p);
                }
            }

            // חישוב חיתוך עם בסיס עליון
            Point topCenter = axis.getPoint(height);
            t = topCenter.subtract(p0).dotProduct(dir) / denom;
            if (t >= 0) {
                Point p = ray.getPoint(t);
                if (p.subtract(topCenter).lengthSquared() <= radius * radius) {
                    result.add(p);
                }
            }
        }

        return result.isEmpty() ? null : result;
    }
}