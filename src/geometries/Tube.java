package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * The {@code Tube} class represents a geometric tube in 3D space.
 * <p>
 * A tube is an infinite cylinder defined by a central axis {@link Ray}
 * and a constant radius. Unlike a finite cylinder, a tube extends infinitely in both directions.
 * </p>
 * The tube inherits the radius field and behavior from {@link RadialGeometry}.
 */
public class Tube extends RadialGeometry {

    /**
     * The central axis (infinite ray) that defines the orientation and location of the tube.
     */
    protected final Ray axis;

    /**
     * Constructs a new {@code Tube} with the given radius and central axis.
     *
     * @param radius the radius of the tube (must be positive)
     * @param ray    the central axis of the tube
     */
    public Tube(Double radius, Ray ray) {
        super(radius);
        this.axis = ray;
    }

    /**
     * Returns the normal vector to the surface of the tube at a given point.
     * <p>
     * This is computed by projecting the point onto the axis and finding the vector
     * perpendicular to the axis direction from the axis to the point.
     *
     * @param point a point on the surface of the tube
     * @return the normalized vector orthogonal to the tube at the given point
     */
    public Vector getNormal(Point point) {
        Vector fromAxisOrigin = point.subtract(axis.getPoint());

        // Project the vector onto the axis direction to find the closest point on the axis
        double t = fromAxisOrigin.dotProduct(axis.getDirection());

        // Find the closest point on the axis to the given point
        Point closestPointOnAxis = axis.getPoint(t);

        // The normal is the vector from the closest point on the axis to the surface point
        return point.subtract(closestPointOnAxis).normalize();
    }

    /**
     * Finds the intersection points of a given ray with the tube.
     * <p>
     * This method is not yet implemented and currently returns {@code null}.
     *
     * @param ray the ray for which to find intersections with the tube
     * @return a list of intersection points, or {@code null} if unimplemented
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Vector rayDirection = ray.getDirection();
        Vector axisDirection = this.axis.getDirection();
        Point rayOrigin = ray.getPoint(0d);

        double dirDotAxis = alignZero(rayDirection.dotProduct(axisDirection)); // (D|V)

        Vector dirMinusDVV;
        if (dirDotAxis == 0d) {
            // that means dir - (D|V)*V = dir, so we can use dir as is
            dirMinusDVV = rayDirection;
        } else {
            // calculate (D|V)*V (DV is not zero)
            Vector dirDV = axisDirection.scale(dirDotAxis);
            try {
                // Subtract the scaled vector from the ray direction
                dirMinusDVV = rayDirection.subtract(dirDV);
            } catch (IllegalArgumentException e1) {
                return null; // if the ray direction is the same as (D|V)*V
            }
        }

        // Calculate the squared length of dir - (D|V)*V
        double A = alignZero(dirMinusDVV.lengthSquared());
        // Calculate the parameter t
        double t = alignZero(Math.sqrt(this.radius * this.radius / A));

        Vector deltaP;
        try {
            // Calculate the vector from the axis origin to the ray origin
            deltaP = rayOrigin.subtract(axis.getPoint(0d));
        } catch (IllegalArgumentException e1) {
            // If the ray starts at the head of the axis
            if (dirDotAxis == 0d) {
                // Return intersection at distance radius if (D|V) is zero
                return t <= 0d ? null : List.of(new GeoPoint(this, ray.getPoint(radius)));
            } else {
                // Return intersection at distance t otherwise
                return t <= 0 ? null : List.of(new GeoPoint(this, ray.getPoint(t)));
            }
        }

        // Calculate the dot product of deltaP and the axis direction
        double deltaP_dot_axis = alignZero(deltaP.dotProduct(axisDirection));
        Vector deltaPMinusDPV;
        if (deltaP_dot_axis == 0d) {
            // If the dot product is zero, use deltaP as is
            deltaPMinusDPV = deltaP;
        } else {
            // Scale the axis direction by the dot product
            Vector deltaP_VV = axisDirection.scale(deltaP_dot_axis);
            try {
                // Subtract the scaled vector from deltaP
                deltaPMinusDPV = deltaP.subtract(deltaP_VV);
            } catch (IllegalArgumentException e1) {
                // Return intersection at distance t if subtraction is not possible
                return t <= 0 ? null : List.of(new GeoPoint(this, ray.getPoint(t)));
            }
        }

        // Calculate the coefficients B and C for the quadratic equation
        double B = 2 * alignZero(dirMinusDVV.dotProduct(deltaPMinusDPV));
        double C = alignZero(deltaPMinusDPV.lengthSquared() - radius * radius);

        // Calculate the discriminant of the quadratic equation
        double discriminant = alignZero(B * B - 4 * A * C);
        if (discriminant <= 0d) {
            // If the discriminant is negative, there are no intersection points
            // if the discriminant is zero, there is one intersection point which is not possible because we intersect
            // infinite ray
            return null;
        }

        // Calculate the square root of the discriminant
        double discriminantSqrt = Math.sqrt(discriminant);

        // Create a list to store potential intersection points
        List<Point> intersections = new LinkedList<>();
        // Calculate the two potential intersection distances
        double t1 = alignZero((-B - discriminantSqrt) / (2 * A));
        double t2 = alignZero((-B + discriminantSqrt) / (2 * A));

        if (t1 > 0d) {
            intersections.add(ray.getPoint(t1));
        }

        if (t2 > 0d) {
            intersections.add(ray.getPoint(t2));
        }

        // Return the list of intersection points, or null if there are none
        return intersections.isEmpty() ? null : intersections.stream().map(p -> new GeoPoint(this, p)).toList();
}
}
