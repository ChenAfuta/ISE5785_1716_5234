package unittests.geometries;

import geometries.Cylinder;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Cylinder class.
 */
public class CylinderTests {

    // נקודה, וקטור המשמשים בבדיקות
    private final Point p0 = new Point(0, 0, 0);
    private final Vector v0 = new Vector(0, 0, 1); // וקטור בכיוון ציר ה-Z
    private final Vector v1 = new Vector(1, 0, 0); // וקטור בכיוון ציר ה-X

    // יצירת גליל
    private final Cylinder cylinder = new Cylinder(new Ray(p0, v0), 2, 5); // גליל עם רדיוס 2 וגובה 5

    @Test
    public void testFindIntersections() {
        // קביעת נקודות חיתוך צפויות

        // ============ Equivalence Partitions Tests ==============

        // TC01: קרן מחוץ לגליל, לא חותכת אותו
        Ray ray1 = new Ray(new Point(3, 0, 0), new Vector(0, 1, 0)); // קרן ב-X לא חותכת את הגליל
        assertNull(cylinder.findIntersections(ray1), "Ray outside cylinder");

        // TC02: קרן חותכת את הגליל בשני נקודות
        Ray ray2 = new Ray(new Point(3, 0, 2), new Vector(-1, 0, 0)); // קרן חותכת את הגליל בשני נקודות
        List<Point> expected2 = List.of(new Point(2, 0, 2), new Point(2, 0, 0)); // נקודות חיתוך צפויות
        List<Point> result2 = cylinder.findIntersections(ray2);
        assertNotNull(result2, "Intersections list should not be empty");
        assertEquals(2, result2.size(), "Wrong number of intersection points");
        assertEquals(expected2, result2, "Intersection points incorrect");

        // TC03: קרן מתחילה בתוך הגליל, חותכת בנקודה אחת
        Ray ray3 = new Ray(new Point(1, 0, 1), new Vector(0, 0, 1)); // קרן בתוך הגליל, חותכת בנקודה אחת
        List<Point> expected3 = List.of(new Point(1, 0, 3)); // נקודת חיתוך צפויה
        List<Point> result3 = cylinder.findIntersections(ray3);
        assertNotNull(result3, "Intersections list should not be empty");
        assertEquals(1, result3.size(), "Wrong number of intersection points");
        assertEquals(expected3, result3, "Intersection point incorrect");

        // TC04: קרן מקבילה לציר הגליל, לא חותכת
        Ray ray4 = new Ray(new Point(0, 0, 0), new Vector(1, 1, 0)); // קרן מקבילה לציר ה-Z, לא חותכת את הגליל
        assertNull(cylinder.findIntersections(ray4), "Parallel ray should not intersect");

        // ============= Boundary Value Analysis ==============

        // Boundary test case for ray tangent to the cylinder
        Ray ray5 = new Ray(new Point(0, 2, 0), new Vector(0, 0, 1)); // קרן מישורית מגע על קצה הגליל
        List<Point> expected5 = List.of(new Point(0, 2, 0)); // נקודת מגע אחת צפויה
        List<Point> result5 = cylinder.findIntersections(ray5);
        assertNotNull(result5, "Tangent should intersect once");
        assertEquals(1, result5.size(), "Should have exactly one intersection");

        // Boundary test case where the ray starts from the edge of the cylinder
        Ray ray6 = new Ray(new Point(2, 0, 0), new Vector(0, 0, 1)); // קרן שנפלטת מקצה הגליל
        List<Point> expected6 = List.of(new Point(2, 0, 5)); // חותכת רק בקצה העליון
        List<Point> result6 = cylinder.findIntersections(ray6);
        assertNotNull(result6, "Ray from edge should intersect at the top");
        assertEquals(1, result6.size(), "Should have exactly one intersection");
    }
}



