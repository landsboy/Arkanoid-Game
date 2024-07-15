package gamegeometry;

import java.util.List;
/**
 * This class making a line.
 */
public class Line {
    private static final double EPSILON = 0.0001;
    private Point start;
    private Point end;

    /**
     * constructor that get two points from the user.
     *
     * @param start the first point of the line.
     * @param end   the second point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor from four coordinates.
     *
     * @param x1 the x value of the first point of the line.
     * @param y1 the y value of the first point of the line.
     * @param x2 the x value of the second point of the line.
     * @param y2 the y value of the second point of the line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * this method measures the length of this line.
     *
     * @return the length of this line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * this method calculates the middle point of this line.
     *
     * @return the middle point of this line.
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * this method returns the start point of the line.
     *
     * @return the start point
     */
    public Point start() {
        return this.start;
    }

    /**
     * this method returns the end point of the line.
     *
     * @return the end point
     */
    public Point end() {
        return this.end;
    }

    /**
     * this method checks if line contain a given point.
     *
     * @param point - the point to check.
     * @return true if this point is in the line, false otherwise.
     */
    public boolean lineContainPoint(Point point) {
        if (point == null) {
            return false;
        }
        double maxY = Math.max(this.start.getY(), this.end.getY());
        double minY = Math.min(this.start.getY(), this.end.getY());
        if (Math.abs(this.start.getX() - this.end.getX()) < EPSILON
                && Math.abs(this.start.getX() - point.getX()) < EPSILON) { // infinity m
            return (point.getY() >= minY  && point.getY() <= maxY);
        }
        //else if the doesn't have infinity m:
        double mLineA = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        double bLineA = this.start.getY() - (mLineA * this.start.getX());
        double maxX = Math.max(this.start.getX(), this.end.getX());
        double minX = Math.min(this.start.getX(), this.end.getX());
        double yPoint = (mLineA * point.getX()) + bLineA;
        return (Math.abs(point.getY() - yPoint) < EPSILON)
                && (point.getX() >= minX && point.getX() <= maxX && point.getY() >= minY && point.getY() <= maxY);
    }

    /**
     * this method checks if this line Intersecting with another line.
     *
     * @param other - the another line to check about.
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        //gameGeometry.Point check = intersectionWith(other);
        return (intersectionWith(other) != null);
    }


    /**
     * this method calculating intersection point of two lines.
     *
     * @param other the other line to check intersection with.
     * @return the intersection point if the lines intersect, null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (this.start.equals(this.end) && other.start.equals(other.end)) { //The two lines are actually points.
            if (this.start.equals(other.start)) {
                return new Point(this.start.getX(), this.start.getY());
            }
            return null;
        }
        if (this.start.equals(this.end)) { //Just our line is actually a dot.
                if (other.lineContainPoint(this.start)) {
                    return new Point(this.start.getX(), this.start.getY());
                }
                return null;
        }
        if (other.start.equals(other.end)) { //The other line is actually a dot.
                if (this.lineContainPoint(other.start)) {
                    return new Point(other.start.getX(), other.start.getY());
                }
                return null;
        }

            if (Math.abs(this.start.getX() - this.end.getX()) < EPSILON
                    && Math.abs(other.start.getX() - other.end.getX()) < EPSILON) { //Both with infinity m
                if (Math.abs(this.start.getX() - other.start.getX()) < EPSILON) { // they are in the same x index
                    if (Math.abs(this.start.getY() - other.start.getY()) < EPSILON) {
                        if (this.end.distance(other.end) > this.end.distance(this.start)) {
                            //The distance of the two points is largest so the lines go in opposite directions
                            return new Point(this.start.getX(), this.start.getY());
                        }
                        return null;
                    }
                    if (Math.abs(this.start.getY() - other.end.getY()) < EPSILON) {
                        if (this.end.distance(other.start) > this.end.distance(this.start)) { //same as above
                            return new Point(this.start.getX(), this.start.getY());
                        }
                        return null;
                    }
                    if (Math.abs(this.end.getY() - other.start.getY()) < EPSILON) {
                        if (this.start.distance(other.end) > this.start.distance(this.end)) { //same as above
                            return new Point(this.start.getX(), this.end.getY());
                        }
                        return null;
                    }
                    if (Math.abs(this.end.getY() - other.end.getY()) < EPSILON) {
                        if (this.start.distance(other.start) > this.start.distance(this.end)) { //same as above
                            return new Point(this.start.getX(), this.end.getY());
                        }
                        return null;
                    }
                } else { // they aren't in the same x index
                    return null;
                }
            }
            if (Math.abs(this.start.getX() - this.end.getX()) < EPSILON) { // Just this line with infinity m
                double mLineB = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
                double bLineB = other.end.getY() - (mLineB * other.end.getX());
                double intersectionYPoint = mLineB * this.start.getX() + bLineB;
                Point intersectionPoint = new Point(this.start.getX(), intersectionYPoint);
                    if (other.lineContainPoint(intersectionPoint) && this.lineContainPoint(intersectionPoint)) {
                        return intersectionPoint;
                    }
                return null;
            }

            if (Math.abs(other.start.getX() - other.end.getX()) < EPSILON) { // Just other line with infinity m
                double mLineA = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
                double bLineA = this.start.getY() - (mLineA * this.start.getX());
                double intersectionYPoint = mLineA * other.start.getX() + bLineA;
                Point intersectionPoint = new Point(other.start.getX(), intersectionYPoint);
                    if (other.lineContainPoint(intersectionPoint) && this.lineContainPoint(intersectionPoint)) {
                        return intersectionPoint;
                    }
                return null;
            }

            //No one is with infinity m.
            double mLineA = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
            double bLineA = this.start.getY() - (mLineA * this.start.getX());
            double mLineB = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
            double bLineB = other.start.getY() - (mLineB * other.start.getX());

            if (mLineA == mLineB) { //The lines are parallel
                return null;
            }

            double intersectionXPoint = (bLineB - bLineA) / (mLineA - mLineB);
            double intersectionYPoint = (mLineA * intersectionXPoint) + bLineA;
        if ((intersectionXPoint >= this.start.getX() && intersectionXPoint <= this.end.getX())) {
            if (intersectionXPoint >= other.start.getX() && intersectionXPoint <= other.end.getX()
                    || intersectionXPoint <= other.start.getX() && intersectionXPoint >= other.end.getX()) {
                return new Point(intersectionXPoint, intersectionYPoint);
            }
        }
        if ((intersectionXPoint <= this.start.getX() && intersectionXPoint >= this.end.getX())) {
            if (intersectionXPoint >= other.start.getX() && intersectionXPoint <= other.end.getX()
                    || intersectionXPoint <= other.start.getX() && intersectionXPoint >= other.end.getX()) {
                return new Point(intersectionXPoint, intersectionYPoint);
            }
        }
        return null;
    }

        /**
         * this method checks if two lines are equal.
         * @param other - another line.
         * @return true if the lines are equal, false otherwise.
         */
        public boolean equals(Line other) {
          return ((this.start.equals(other.start) && this.end.equals(other.end))
                  || (this.start.equals(other.end) && this.end.equals(other.start)));
        }


    /**
     * this method checks If this line intersects with a given rectangle, if yes -
     * return the closest intersection point to the start of the line. if no - return null.
     * @param rect the given rectangle.
     * @return the closest intersection point to the start of the line,
     * null if there isn't any.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
       if (intersectionPoints.isEmpty()) {
           return null;
       } else if (intersectionPoints.size() == 1) {
           return intersectionPoints.get(0);
       } else {
           Point closestIntersection = intersectionPoints.get(0);
           double closestDistance = this.start.distance(intersectionPoints.get(0));
           for (int i = 1; i < intersectionPoints.size(); i++) {
               if (this.start.distance(intersectionPoints.get(i)) < closestDistance) {
                   closestIntersection = intersectionPoints.get(i);
                   closestDistance = this.start.distance(intersectionPoints.get(i));
               }
           }
           return closestIntersection;
       }
    }
}
