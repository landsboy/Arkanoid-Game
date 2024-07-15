package gamegeometry;

import java.util.ArrayList;
import java.util.List;
/**
 * this class making a rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private Point downRight;
    private Line[] rectangleSidesArray;
    private double width;
    private double height;

    /**
     * this method constructs a new rectangle with location, width and height.
     * @param upperLeft the rectangle upper left point.
     * @param width the rectangle's width
     * @param height the rectangle's width
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.downRight =  new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.rectangleSidesArray = new Line[4];
        rectangleSidesArray[0] = new Line(this.upperLeft,
                new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY()));
        rectangleSidesArray[1] = new Line(new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY()),
                this.downRight);
        rectangleSidesArray[2] = new Line(new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height),
                this.downRight);
        rectangleSidesArray[3] = new Line(this.upperLeft,
                new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height));
    }
    /**
     * this method returns a (possibly empty) List of intersection points with a given line and this rectangle.
     * @param  line a given line.
     * @return List of intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPointsArray = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (this.rectangleSidesArray[i].isIntersecting(line)) {
                intersectionPointsArray.add(this.rectangleSidesArray[i].intersectionWith(line));
            }
        }
        return intersectionPointsArray;
    }
    /**
     * this method returns the width of the rectangle.
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * this method returns the height of the rectangle.
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * this method returns the rectangle's UpSide line.
     * @return the rectangle's UpSide line.
     */
    public Line getUpSide() {
        return this.rectangleSidesArray[0];
    }
    /**
     * this method returns the rectangle's RightSide line.
     * @return the rectangle's RightSide line.
     */
    public Line getRightSide() {
        return this.rectangleSidesArray[1];
    }
    /**
     * this method returns the rectangle's DownSide line.
     * @return the rectangle's DownSide line.
     */
    public Line getDownSide() {
        return this.rectangleSidesArray[2];
    }
    /**
     * this method returns the rectangle's LeftSide line.
     * @return the rectangle's LeftSide line.
     */
    public Line getLeftSide() {
        return this.rectangleSidesArray[3];
    }
    /**
     * this method returns the upper-left point of the rectangle.
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
       return this.upperLeft;
    }
    /**
     * this method returns the down-right point of the rectangle.
     * @return the down-right  point of the rectangle.
     */
    public Point getDownRight() {
        return this.downRight;
    }
    /**
     * this method returns the down-Left point of the rectangle.
     * @return the down-Left  point of the rectangle.
     */
    public Point getDownLeft() {
        return new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight());
    }
    /**
     * this method returns the Upper-right point of the rectangle.
     * @return the Upper-right  point of the rectangle.
     */
    public Point getUpperRight() {
        return new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY());
    }
}