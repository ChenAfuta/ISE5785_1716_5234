package renderer;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

import primitives.Color;

/**
 * Handles the creation and writing of images based on a pixel color matrix.
 * Responsible for managing image-related parameters such as resolution and size.
 */
final class ImageWriter {
    /**
     * Horizontal resolution of the image - number of pixels in a row.
     */
    private final int nX;

    /**
     * Vertical resolution of the image - number of pixels in a column.
     */
    private final int nY;

    /**
     * Directory path for the image file generation - relative to the user directory.
     */
    private static final String FOLDER_PATH = System.getProperty("user.dir") + "/images";

    /**
     * Image generation buffer (the matrix of the pixels).
     */
    private final BufferedImage image;

    /**
     * Constructs an ImageWriter with the specified resolution.
     * @param nX the number of pixels in width
     * @param nY the number of pixels in height
     */
    ImageWriter(int nX, int nY) {
        this.nX = nX;
        this.nY = nY;
        image = new BufferedImage(nX, nY, BufferedImage.TYPE_INT_RGB);
    }

    /**
     * Returns the vertical resolution of the view plane.
     * @return the number of vertical pixels
     */
    int nY() {
        return nY;
    }

    /**
     * Returns the horizontal resolution of the view plane.
     * @return the number of horizontal pixels
     */
    int nX() {
        return nX;
    }

    /**
     * Writes the image to a PNG file in the specified directory.
     * @param imageName the name of the PNG file
     */
    void writeToImage(String imageName) {
        try {
            File file = new File(FOLDER_PATH + '/' + imageName + ".png");
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            throw new IllegalStateException("I/O error - may be missing directory " + FOLDER_PATH, e);
        }
    }

    /**
     * Writes a color to a specific pixel in the pixel color matrix.
     * @param xIndex the X-axis index of the pixel
     * @param yIndex the Y-axis index of the pixel
     * @param color the color to set for the pixel
     */
    void writePixel(int xIndex, int yIndex, Color color) {
        image.setRGB(xIndex, yIndex, color.getColor().getRGB());
    }
}
