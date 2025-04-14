package geometries;

import java.util.LinkedList;
import java.util.List;
import primitives.*;

public class Geometries implements Intersectable {
    private final List<Intersectable> geometries = new LinkedList<>();

    // בנאי ברירת מחדל
    public Geometries() {
        // לא נדרש קוד כאן
    }

    // בנאי עם פרמטרים - משתמש במתודת add
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    // מתודת הוספת גופים לאגד
    public void add(Intersectable... geometries) {
        for (Intersectable geometry : geometries) {
            this.geometries.add(geometry);
        }
    }

    // מימוש מתודת findIntersections לפי Composite pattern
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersections = null;
        for (Intersectable geometry : geometries) {
            List<Point> geometryIntersections = geometry.findIntersections(ray);
            if (geometryIntersections != null) {
                if (intersections == null) {
                    intersections = new LinkedList<>();
                }
                intersections.addAll(geometryIntersections);
            }
        }
        return intersections;
    }
}
