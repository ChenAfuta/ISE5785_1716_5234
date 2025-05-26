package renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.AmbientLight;
import primitives.*;
import scene.Scene;

/**
 * Unit tests for rendering scenes using the {@link renderer.Camera} and {@link renderer.ImageWriter}.
 * This class tests the rendering of basic 3D models into images.
 */
public class RenderTests {

   /** 
    * The {@link scene.Scene} instance used for the rendering tests.
    */
   private final Scene scene = new Scene("Test scene");

   /** 
    * The {@link renderer.Camera.Builder} instance used to configure and build the camera for the tests.
    */
   private final Camera.Builder camera = Camera.getBuilder()
      .setRayTracer(new SimpleRayTracer(scene))
      .setLocation(Point.ZERO).setDirection(new Point(0, 0, -1), Vector.AXIS_Y)
      .setVpDistance(100)
      .setVpSize(500, 500);

   /**
    * Produces a scene with a basic 3D model and renders it into a PNG image with a grid.
    */
   @Test
   public void renderTwoColorTest() {
      scene.geometries.add(new Sphere(50d, new Point(0, 0, -100)),
                           new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100)), // up
                           // left
                           new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100),
                                        new Point(-100, -100, -100)), // down
                           // left
                           new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))); // down
      scene.setAmbientLight(new AmbientLight(new Color(255, 191, 191), Double3.ONE))
         .setBackground(new Color(75, 127, 90));

      // right
      camera
         .setImageWriter(new ImageWriter("base render test", 1000, 1000))
         .build()
         .renderImage()
         .printGrid(100, new Color(YELLOW))
         .writeToImage();
   }
   // For stage 6 - please disregard in stage 5
   /**
    * Produce a scene with basic 3D model - including individual lights of the
    * bodies and render it into a png image with a grid
    */
   @Test
   void renderMultiColorTest() {
      Scene scene = new Scene("Multi color").setAmbientLight(new AmbientLight(new Color(51, 51, 51)));
      scene.geometries //
              .add(// center
                      new Sphere(new Point(0, 0, -100), 50),
                      // up left
                      new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100)) //
                              .setEmission(new Color(GREEN)),
                      // down left
                      new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100)) //
                              .setEmission(new Color(RED)),
                      // down right
                      new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100)) //
                              .setEmission(new Color(BLUE)));

      camera //
              .setRayTracer(scene, RayTracerType.SIMPLE) //
              .setResolution(1000, 1000) //
              .build() //
              .renderImage() //
              .printGrid(100, new Color(WHITE)) //
              .writeToImage("color render test");
   }

//   /** Test for XML based scene - for bonus */
//   @Test
//   public void basicRenderXml() {
//      // enter XML file name and parse from XML file into scene object
//      // using the code you added in appropriate packages
//      // ...
//      // NB: unit tests is not the correct place to put XML parsing code
//
//      camera
//         .setImageWriter(new ImageWriter("xml render test", 1000, 1000))
//         .build()
//         .renderImage()
//         .printGrid(100, new Color(YELLOW))
//         .writeToImage();
//   }

}
