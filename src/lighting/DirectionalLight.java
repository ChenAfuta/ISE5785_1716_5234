package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Represents a directional light source with parallel rays, similar to sunlight.
 * The light has constant intensity everywhere.
 * @see lighting.Light
 * @see lighting.LightSource
 */
public class DirectionalLight extends Light implements LightSource {
    /**
     * The direction vector of the light rays (normalized).
     */
    private final Vector direction;

    /**
     * Constructs a directional light source with the specified intensity and direction.
     * @param intensity the color intensity of the light
     * @param direction the direction of the light
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    /**
     * Returns the intensity of the light at a given point.
     * @param p the point in 3D space
     * @return the intensity of the light
     */
    @Override
    public Color getIntensity(Point p) {
        return intensity;
    }

    /**
     * Returns the direction vector of the light.
     * @param p the point in 3D space
     * @return the normalized direction vector
     */
    @Override
    public Vector getL(Point p) {
        return direction;
    }
    /**
     * Returns the distance from the light source to a given point.
     * Since directional light is considered to be infinitely far away, this method returns infinity.
     * @param point the point in 3D space
     * @return the distance from the light source to the point (infinity)
     */
    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }
}
