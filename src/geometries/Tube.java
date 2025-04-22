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
        return null; // Not implemented
    }
}
