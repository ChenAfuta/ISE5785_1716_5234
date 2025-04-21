package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import primitives.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * The Tube class represents a tube in a 3D space.
 * A tube is defined by a central axis (ray) and a radius.
 */
public class Tube extends RadialGeometry {
    protected final Ray axis;

    /**
     * Constructs a Tube with the specified central axis and radius.
     * @param ray the central axis of the tube
     * @param radius the radius of the tube
     */
    public Tube(Double radius, Ray ray) {
        super(radius);
        this.axis = ray;
    }

    /**
     * Calculates the normal vector to the tube at a given point.
     * @param point the point on the surface of the tube
     * @return the normal vector to the tube at the given point
     */
    public Vector getNormal(Point point) {
        Vector fromAxisOrigin = point.subtract(axis.getPoint());

        // מחשב את ההיטל של הווקטור על כיוון הציר - נותן את המרחק לאורך הציר
        double t = fromAxisOrigin.dotProduct(axis.getDirection());

        // מוצא את הנקודה על הציר הכי קרובה לנקודת החיתוך
        Point closestPointOnAxis = axis.getPoint(t);

        // הנורמל הוא הווקטור בין הנקודה שעל מעטפת הגליל לבין הנקודה הכי קרובה על הציר
        return point.subtract(closestPointOnAxis).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        Vector v = ray.getDirection();
        Vector va = axis.getDirection();

        // בדיקה כללית האם הקרן מקבילה לציר הגליל
        double vVa = v.dotProduct(va);

        // מחשבים את הווקטור האנכי בין כיוון הקרן לציר הגליל
        Vector temp = v.subtract(va.scale(vVa));

        // אם הווקטור האנכי הוא אפס, הקרן מקבילה לציר ואין חיתוך
        if (Util.isZero(temp.lengthSquared())) {
            return null; // הקרן מקבילה לציר ואין חיתוך עם מעטפת
        }

        Vector vPerp = temp;

        Point p0 = ray.getPoint();
        Point pa = axis.getPoint();

        Vector deltaP = p0.subtract(pa);
        double dpVa = deltaP.dotProduct(va);
        Vector dpPerp = deltaP.subtract(va.scale(dpVa));

        double a = vPerp.lengthSquared();
        double b = 2 * vPerp.dotProduct(dpPerp);
        double c = dpPerp.lengthSquared() - radius * radius;

        double discriminant = b * b - 4 * a * c;

        if (discriminant <= 0) {
            return null;
        }

        double sqrtDisc = Math.sqrt(discriminant);
        double t1 = (-b + sqrtDisc) / (2 * a);
        double t2 = (-b - sqrtDisc) / (2 * a);

        List<Point> intersections = new ArrayList<>();
        if (t1 > 0) intersections.add(ray.getPoint(t1));
        if (t2 > 0) intersections.add(ray.getPoint(t2));

        return intersections.isEmpty() ? null : intersections;
    }
}
