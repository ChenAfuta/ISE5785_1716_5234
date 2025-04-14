package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

public interface Intersectable {
    public List<Point> findInstrsections(Ray ray);
}
