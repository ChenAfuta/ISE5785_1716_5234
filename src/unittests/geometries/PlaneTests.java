package unittests.geometries;

import org.junit.jupiter.api.Test;

class PlaneTests {

    @Test
    void PlaneConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: בנאי תקין עם שלוש נקודות שונות
        Point p1 = new Point(1, 0, 0);
        Point p2 = new Point(0, 1, 0);
        Point p3 = new Point(0, 0, 1);

        Plane plane = new Plane(p1, p2, p3);

        // חישוב שני וקטורים שנוצרים מהנקודות
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);

        // בדיקת מאונכות
        Vector normal = plane.getNormal(p1);
        assertEquals(0, normal.dotProduct(v1), "Normal is not perpendicular to v1");
        assertEquals(0, normal.dotProduct(v2), "Normal is not perpendicular to v2");

        // בדיקת אורך הנורמל
        assertEquals(1, normal.length(), "Normal vector is not normalized");

        // ============== Boundary Value Tests ==============

        // TC02: שתי נקודות זהות (ראשונה ושנייה) - יש לזרוק IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> new Plane(p1, p1, p3));

        // TC03: שתי נקודות זהות (ראשונה ושלישית) - יש לזרוק IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> new Plane(p1, p2, p1));

        // TC04: שתי נקודות זהות (שנייה ושלישית) - יש לזרוק IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> new Plane(p1, p2, p2));

        // TC05: שלוש נקודות זהות - יש לזרוק IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> new Plane(p1, p1, p1));

        // TC06: שלוש נקודות על אותו ישר - יש לזרוק IllegalArgumentException
        Point p4 = new Point(2, 0, 0); // בקו עם p1 ו-p2
        assertThrows(IllegalArgumentException.class, () -> new Plane(p1, p2, p4));
    }
    @Test
    void testGetNormal() {

    }
}