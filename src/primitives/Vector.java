package primitives;

public final class Vector extends Point {

    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (coordinates.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Vector cannot be zero vector");
        }
    }

    public Vector(Double3 xyz) {
        super(xyz);
        if (coordinates.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Vector cannot be zero vector");
        }
    }

    public Vector add(Vector other) {
        return new Vector(this.coordinates.add(other.coordinates));
    }

    public Vector scale(double scalar) {
        return new Vector(this.coordinates.scale(scalar));
    }

    public double dotProduct(Vector other) {
return this.coordinates.d1() * other.coordinates.d1() +
                this.coordinates.d2() * other.coordinates.d2() +
                this.coordinates.d3() * other.coordinates.d3();

    }

    public Vector crossProduct(Vector other) {
return new Vector(this.coordinates.d2() * other.coordinates.d3() - this.coordinates.d3() * other.coordinates.d2(),
                this.coordinates.d3() * other.coordinates.d1() - this.coordinates.d1() * other.coordinates.d3(),
                this.coordinates.d1() * other.coordinates.d2() - this.coordinates.d2() * other.coordinates.d1());
    }

    public double lengthSquared() {
     return this.coordinates.d1() * this.coordinates.d1() +
                this.coordinates.d2() * this.coordinates.d2() +
                this.coordinates.d3() * this.coordinates.d3();
    }

    public double length() {
      return Math.sqrt(this.lengthSquared());
    }

    public Vector normalize() {
       return new Vector(this.coordinates.scale(1.0 / this.length()));
    }

    @Override
    public String toString() {
        return "Vector{" + "coordinates=" + coordinates + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vector)) return false;
        Vector other = (Vector) obj;
        return coordinates.equals(other.coordinates);
    }


}
