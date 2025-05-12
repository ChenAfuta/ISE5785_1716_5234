package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

/**
 * The {@code Scene} class represents a 3D scene for rendering.
 * <p>
 * A scene includes a name, background color, ambient lighting, and a collection of geometries.
 * It provides a fluent interface (builder-style) for setting its properties.
 */
public class Scene {

    /** The name of the scene. */
    public String name = "";

    /** The background color of the scene. Defaults to black. */
    public Color background = Color.BLACK;

    /** The ambient light of the scene. Defaults to no ambient light. */
    public AmbientLight ambientLight = AmbientLight.NONE;

    /** The collection of geometries contained in the scene. */
    public Geometries geometries = new Geometries();

    /**
     * Constructs a new {@code Scene} with the specified name.
     *
     * @param name the name of the scene
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * Sets the background color of the scene.
     *
     * @param background the background color
     * @return the current {@code Scene} instance (for method chaining)
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Sets the ambient light of the scene.
     *
     * @param ambientLight the ambient light
     * @return the current {@code Scene} instance (for method chaining)
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Sets the geometries contained in the scene.
     *
     * @param geometries the geometries to include
     * @return the current {@code Scene} instance (for method chaining)
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}
