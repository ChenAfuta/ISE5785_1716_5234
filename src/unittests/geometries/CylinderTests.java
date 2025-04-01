package unittests.geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CylinderTests {

    @Test
    void testGetHeight() {
        assertEquals(5, new Cylinder(5, 2, new Point3D(0, 0, 0), new Vector(0, 0, 1)).getHeight());

    }

    @Test
    void testGetNormal() {

    }
}