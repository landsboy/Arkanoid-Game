package gamegeometry;

/** public class gameGeometry.Point.
 * This class making a point and could to check the distance between him and anouther point
 * and if it is equal to another point.
 */
public class Point {
    private double x;
    private double y;
    /**
     * constructor.
     * @param x the x value of this point.
     * @param y the y value of this point.
    */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * this method measures the distance from this point to another point.
     * @param other - another point.
     * @return the distance of this point from the other point.
     */
    public double distance(Point other) {
        double distance;
        distance = Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
        return  distance;
    }
    /**
     * this method checks if two points are the same.
     * @param other another point.
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return (Math.abs(this.x - other.x) < 0.00001 && Math.abs(this.y - other.y) < 0.00001);
    }
    /**
     * this method returns the x value of this point.
     * @return the x value of this point.
     */
    public double getX() {
        return this.x;
    }
    /**
     * this method returns the y value of this point.
     * @return the y value of this point.
     */
    public double getY() {
        return this.y;
    }
}