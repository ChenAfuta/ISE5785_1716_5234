package unittests.primitives;
import primitives.Vector;
import primitive.Double3;
import static org.junit.jupiter.api.Assertions.*;
import primitives.Vector;

class VectorTest {

    @org.junit.jupiter.api.Test
    void vectorConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0), "ERROR: Vector constructor does not throw an exception when creating a zero vector");
    }
    @org.junit.jupiter.api.Test
    void vectorConstructor2() {
        assertThrows(IllegalArgumentException.class, () -> new Vector(new Double3(0, 0, 0)), "ERROR: Vector constructor does not throw an exception when creating a zero vector");
}
    @org.junit.jupiter.api.Test
    void add() {
        assertEquals(new Vector(2, 4, 6), new Vector(1, 2, 3).add(new Vector(1, 2, 3)), "ERROR: Vector + Vector does not work correctly");
    }

    @org.junit.jupiter.api.Test
    void scale() {
        assertEquals(new Vector(2, 4, 6), new Vector(1, 2, 3).scale(2), "ERROR: Vector * scalar does not work correctly");
    }

    @org.junit.jupiter.api.Test
    void dotProduct() {
        assertEquals(32, new Vector(1, 2, 3).dotProduct(new Vector(4, 5, 6)), "ERROR: Vector * Vector does not work correctly");
    }

    @org.junit.jupiter.api.Test
    void crossProduct() {
        assertEquals(new Vector(-3, 6, -3), new Vector(1, 2, 3).crossProduct(new Vector(4, 5, 6)), "ERROR: Vector x Vector does not work correctly");
    }

    @org.junit.jupiter.api.Test
    void lengthSquared() {
        assertEquals(14, new Vector(1, 2, 3).lengthSquared(), "ERROR: lengthSquared() wrong value");
    }

    @org.junit.jupiter.api.Test
    void length() {
        assertEquals(Math.sqrt(14), new Vector(1, 2, 3).length(), "ERROR: length() wrong value");
    }

    @org.junit.jupiter.api.Test
    void normalize() {
        assertEquals(new Vector(1 / Math.sqrt(14), 2 / Math.sqrt(14), 3 / Math.sqrt(14)), new Vector(1, 2, 3).normalize(), "ERROR: normalize() wrong value");
}

}