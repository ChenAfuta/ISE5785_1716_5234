package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Tringle extends Polygon {
   public  tringle();
    public List<Point> findInstrsections(Ray ray)
    {
        Vector edge= getPoints().get(1).subtract(getPoints().get(0));
        Vector edge2= getPoints().get(2).subtract(getPoints().get(0));
        Vector h=ray.getPoint().subtract(getPoints().get(0));


    }
}
