package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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
        Vector v = axis.getDirection(); // כיוון הגליל
        Point p0 = axis.getPoint();     // נקודת התחלה של הציר
        Point p = ray.getPoint();      // נקודת התחלה של הקרן
        Vector dir = ray.getDirection(); // כיוון הקרן

        Vector deltaP = p.subtract(p0); // וקטור מהציר לקרן

        Vector vDotDir;
        double vDotDirScalar = dir.dotProduct(v);
        if (vDotDirScalar == 0) {
            vDotDir = new Vector(1, 0, 0).crossProduct(v); // כל וקטור שאינו v יתן וקטור תקף
        } else {
            vDotDir = v.scale(vDotDirScalar);
        }
        Vector d = dir.subtract(vDotDir);


        Vector vDotDeltaP;
        double vDotDeltaPScalar = deltaP.dotProduct(v);
        if (vDotDeltaPScalar == 0) {
            vDotDeltaP = new Vector(1, 0, 0).crossProduct(v);
        } else {
            vDotDeltaP = v.scale(vDotDeltaPScalar);
        }
        Vector delta = deltaP.subtract(vDotDeltaP);


        double a = d.lengthSquared();
        double b = 2 * d.dotProduct(delta);
        double c = delta.lengthSquared() - radius * radius;

        double discriminant = b * b - 4 * a * c;

        if (discriminant <= 0) {
            return null;
        }

        double sqrtDiscriminant = Math.sqrt(discriminant);
        if (a == 0) {
            return null; // הקרן מקבילה לציר הגליל – אין חיתוך עם השטח הצדדי
        }
        double t1 = (-b + sqrtDiscriminant) / (2 * a);
        double t2 = (-b - sqrtDiscriminant) / (2 * a);

        List<Point> result = new ArrayList<>();

        if (t1 > 0) result.add(ray.getPoint(t1));
        if (t2 > 0) result.add(ray.getPoint(t2));

        return result.isEmpty() ? null : result;
    }

}







