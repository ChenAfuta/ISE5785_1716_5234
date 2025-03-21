package primitives;
import primitives.Vector;
public class Point {
    protected Double3 coordinates;

    public static final Point ZERO = new Point(new Double3(0, 0, 0));

    public Point(double x, double y, double z) {
        this.coordinates = new Double3(x, y, z);
    }

    public Point(Double3 coordinates) {
        this.coordinates = coordinates;
    }

    public Vector subtract(Point other) {
        return new Vector(this.coordinates.subtract(other.coordinates));
    }
    public Point  add(Vector other){
        return new Vector(this.coordinates.add(other.coordinates));
    }

    public double distanceSquared(Point other) {

        return (this.coordinates.d1()- other.coordinates.d1()) * (this.coordinates.d1() - other.coordinates.d1()) +
                (this.coordinates.d2() - other.coordinates.d2()) * (this.coordinates.d2() - other.coordinates.d2()) +
                (this.coordinates.d3() - other.coordinates.d3()) * (this.coordinates.d3() - other.coordinates.d3());


    }

    public double distance(Point other) {
        return Math.sqrt(this.distanceSquared(other));
    }

    @Override
    public String toString() {
        return "Point{" + "coordinates=" + coordinates + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Point)) return false;
        Point other = (Point) obj;
        return coordinates.equals(other.coordinates);
    }


}
