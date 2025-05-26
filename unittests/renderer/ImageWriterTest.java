package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

/**
 * Unit test for the {@link ImageWriter} class.
 * This test creates a simple image with a red grid on a teal background,
 * and writes it to a file named "test1".
 */
class ImageWriterTest {

    /** 
     * The {@link renderer.ImageWriter} instance used for creating the test image.
     */
    ImageWriter writer = new ImageWriter("test1", 800, 500);

    /**
     * Test method for {@link ImageWriter#writePixel(int, int, primitives.Color)} and
     * {@link ImageWriter#writeToImage()}.
     * <p>
     * This test draws a grid every 50 pixels in red over a background of RGB(0, 127.5, 127.5),
     * and writes the image to the file system.
     */
    @Test
    void imageWriterTest() {
        for (int i = 0; i < 500; i += 1) {
            for (int j = 0; j < 800; j += 1) {
                if (i % 50 == 0 || j % 50 == 0)
                    writer.writePixel(j, i, new Color(255, 0, 0)); // red grid lines
                else
                    writer.writePixel(j, i, new Color(0, 127.5, 127.5)); // teal background
            }
        }
        writer.writeToImage(); // writes the image to disk
    }
}
