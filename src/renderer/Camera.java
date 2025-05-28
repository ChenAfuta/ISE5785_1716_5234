package renderer;

import primitives.*;
import scene.Scene;

import java.util.MissingResourceException;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Camera class represents a camera in 3D space.
 * It is responsible for constructing rays through pixels and rendering images.
 * It uses a builder pattern for initialization.
 * @see primitives.Point
 * @see primitives.Vector
 * @see primitives.Ray
 * @see scene.Scene
 */
public class Camera implements Cloneable {
    /**
     * The location of the camera in 3D space.
     */
    private Point p0;
    /**
     * The direction the camera is pointing to.
     */
    private Vector vTo;
    /**
     * The direction up from the camera.
     */
    private Vector vUp;
    /**
     * The direction right from the camera.
     */
    private Vector vRight;

    /**
     * The distance between the camera and the view plane.
     */
    private double distance = 0;
    /**
     * The width of the view plane.
     */
    private double width = 0;
    /**
     * The height of the view plane.
     */
    private double height = 0;

    /**
     * The image writer used to write the final rendered image to a file.
     * @see renderer.ImageWriter
     */
    private ImageWriter imageWriter;
    /**
     * The ray tracer calculates the color of each pixel by tracing rays through the scene.
     * @see renderer.RayTracerBase
     */
    private RayTracerBase rayTracer;
    /**
     * The number of pixels across (horizontal resolution).
     */
    private int nX = 1;
    /**
     * The number of pixels along the length (vertical resolution).
     */
    private int nY = 1;

    /**
     * Private constructor for the Camera class.
     * Use the {@link Builder} to create an instance.
     */
    private Camera() {}

    /**
     * Returns a new instance of the Camera builder.
     * @return the camera builder
     * @see Builder
     */
    public static Builder getBuilder() {
        return new Builder();
    }

    /**
     * Constructs a ray from the camera through a specific pixel.
     * @param nX the number of columns of pixels
     * @param nY the number of rows of pixels
     * @param j the pixel's column number
     * @param i the pixel's row number
     * @return the ray from the camera to the middle of the pixel
     * @see primitives.Ray
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        // Calculate the center point of the view plane
        Point pC = p0.add(vTo.scale(distance));

        // Calculate the size of each pixel (height and width)
        double rY = height / nY;
        double rX = width / nX;

        // Calculate the vertical and horizontal offset from the center to pixel (i, j)
        // Minus because y starts at the top of the matrix and continues opposite the vUp vector
        double yI = -(i - (nY - 1) / 2d) * rY;
        double xJ = (j - (nX - 1) / 2d) * rX;

        // Start at the center of the view plane
        Point pIJ = pC;
        // If xJ is zero than no need to move on the horizontal axis
        if (!isZero(xJ))
            pIJ = pIJ.add(vRight.scale(xJ));
        // If yI is zero than no need to move on the vertical axis
        if (!isZero(yI))
            pIJ = pIJ.add(vUp.scale(yI));

        // Return the ray that starts at camera and goes through the center of the pixel
        return new Ray(p0, pIJ.subtract(p0));
    }

    /**
     * Renders the image by tracing rays through all pixels.
     * @return the camera instance
     */
    public Camera renderImage() {
        for(int i=0; i < nX; i++)
            for(int j=0; j < nY; j++)
                castRay(i,j);
        return this;
    }

    /**
     * Prints a grid on the image.
     * @param interval the number of pixels in the width/height of the grid square
     * @param color the color of the grid
     * @return the camera instance
     * @see primitives.Color
     */
    public Camera printGrid(int interval, Color color) {
        for(int i=0; i < nX; i+=interval)
            for(int j=0; j < nY; j++)
                imageWriter.writePixel(j, i, color);
        for(int i=0; i < nX; i++)
            for(int j=0; j < nY; j+=interval)
                imageWriter.writePixel(j, i, color);
        return this;
    }

    /**
     * Writes the rendered image to a file.
     * @param imageName the name of the PNG file
     * @return the camera instance
     * @see renderer.ImageWriter#writeToImage(String)
     */
    public Camera writeToImage(String imageName) {
        imageWriter.writeToImage(imageName);
        return this;
    }

    /**
     * Casts a ray through a specific pixel and colors it.
     * @param i the pixel's row number
     * @param j the pixel's column number
     */
    private void castRay(int i,  int j) {
        Ray ray = constructRay(nX, nY, j, i);
        Color color = rayTracer.traceRay(ray);
        this.imageWriter.writePixel(j, i, color);
    }

    /**
     * Builder class for constructing a Camera instance.
     */
    public static class Builder {
        /**
         * The Camera instance being built.
         */
        private final Camera camera = new Camera();

        /**
         * Sets the location of the camera.
         * @param p0 the location of the camera
         * @return the builder instance
         * @see primitives.Point
         */
        public Builder setLocation(Point p0) {
            camera.p0 = p0;
            return this;
        }

        /**
         * Sets the direction of the camera using orthogonal vectors.
         * @param vTo the direction the camera is pointing
         * @param vUp the direction up from the camera
         * @return the builder instance
         * @throws IllegalArgumentException if vTo and vUp are not orthogonal
         * @see primitives.Vector
         */
        public Builder setDirection(Vector vTo, Vector vUp) {
            if (!isZero(vTo.dotProduct(vUp)))
                throw new IllegalArgumentException("vTo isn't orthogonal to vUp");

            camera.vTo = vTo.normalize();
            camera.vUp = vUp.normalize();
            return this;
        }

        /**
         * Sets the direction of the camera using a target point and an up vector.
         * @param targetPoint the target point the camera is aiming at
         * @param vUp the direction up from the camera
         * @return the builder instance
         * @see primitives.Point
         * @see primitives.Vector
         */
        public Builder setDirection(Point targetPoint, Vector vUp) {
            camera.vTo = targetPoint.subtract(camera.p0);
            camera.vRight = camera.vTo.crossProduct(vUp);
            camera.vUp = camera.vRight.crossProduct(camera.vTo);

            camera.vTo = camera.vTo.normalize();
            camera.vRight = camera.vRight.normalize();
            camera.vUp = camera.vUp.normalize();
            return this;
        }

        /**
         * Sets the direction of the camera using a target point.
         * Defaults to using the Y-axis as the up vector.
         * @param targetPoint the target point the camera is aiming at
         * @return the builder instance
         * @see primitives.Point
         */
        public Builder setDirection(Point targetPoint) {
            return setDirection(targetPoint, Vector.AXIS_Y);
        }

        /**
         * Sets the size of the view plane.
         * @param width the width of the view plane
         * @param height the height of the view plane
         * @return the builder instance
         * @throws IllegalArgumentException if width or height is non-positive
         */
        public Builder setVpSize(double width, double height) {
            if (alignZero(width) <= 0 || alignZero(height) <= 0)
                throw new IllegalArgumentException("width and height must be positive");

            camera.width = width;
            camera.height = height;
            return this;
        }

        /**
         * Sets the distance between the camera and the view plane.
         * @param distance the distance to the view plane
         * @return the builder instance
         * @throws IllegalArgumentException if distance is non-positive
         */
        public Builder setVpDistance(double distance) {
            if (alignZero(distance) <= 0)
                throw new IllegalArgumentException("distance must be positive");

            camera.distance = distance;
            return this;
        }

        /**
         * Sets the resolution of the view plane.
         * @param nX the number of pixels across (horizontal resolution)
         * @param nY the number of pixels along the length (vertical resolution)
         * @return the builder instance
         */
        public Builder setResolution(int nX, int nY) {
            camera.nX = nX;
            camera.nY = nY;
            return this;
        }

        /**
         * Builds and returns the Camera instance.
         * Validates all required fields before returning.
         * @return the constructed Camera instance
         * @throws MissingResourceException if any required field is missing
         */
        public Camera build() {
            final String className = "Camera";
            final String description = "Missing Render Data:";

            if (camera.p0 == null)
                throw new MissingResourceException(description, className, "p0");
            if (camera.vTo == null)
                throw new MissingResourceException(description, className, "vTo");
            if (camera.vUp == null)
                throw new MissingResourceException(description, className, "vUp");

            if (camera.distance == 0)
                throw new MissingResourceException(description, className, "distance");
            if (camera.width == 0)
                throw new MissingResourceException(description, className, "width");
            if (camera.height == 0)
                throw new MissingResourceException(description, className, "height");

            if (camera.nX <= 0)
                throw new MissingResourceException(description, className, "nX");
            if (camera.nY <= 0)
                throw new MissingResourceException(description, className, "nY");

            camera.imageWriter = new ImageWriter(camera.nX, camera.nY);

            if (camera.rayTracer == null)
                camera.rayTracer = new SimpleRayTracer(null);

            if (camera.vRight == null) {
                camera.vRight = camera.vTo.crossProduct(camera.vUp);
                camera.vRight = camera.vRight.normalize();
            }

            if (!isZero(camera.vTo.length() - 1) ||
                    !isZero(camera.vUp.length() - 1) ||
                    !isZero(camera.vRight.length() - 1))
                throw new IllegalArgumentException("vTo, vUp, vRight must be normalized");

            double x = camera.vTo.dotProduct(camera.vUp);
            double y = camera.vTo.dotProduct(camera.vRight);
            double z = camera.vUp.dotProduct(camera.vUp);

            if (!isZero(camera.vTo.dotProduct(camera.vUp)) ||
                    !isZero(camera.vTo.dotProduct(camera.vRight)) ||
                    !isZero(camera.vUp.dotProduct(camera.vRight)))
                throw new IllegalArgumentException("vTo, vUp, vRight must be orthogonal");

            if (alignZero(camera.distance) <= 0)
                throw new IllegalArgumentException("distance must be positive");
            if (alignZero(camera.width) <= 0 || alignZero(camera.height) <= 0)
                throw new IllegalArgumentException("width and height must be positive");

            try {
                return (Camera)camera.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * Sets the ray tracer for the camera.
         * @param scene the scene to be rendered
         * @param rayTracerType the type of ray tracer to use
         * @return the builder instance
         * @see renderer.RayTracerType
         */
        public Builder setRayTracer(Scene scene, RayTracerType rayTracerType) {
            if (rayTracerType == RayTracerType.SIMPLE)
                camera.rayTracer = new SimpleRayTracer(scene);
            else
                camera.rayTracer = null;
            return this;
        }
    }
}

