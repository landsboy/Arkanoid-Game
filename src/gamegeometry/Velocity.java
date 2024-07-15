package gamegeometry;

/**
 * this class defines a Velocity by specifying the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * construct gameGeometry.Velocity object from..
     * @param dx the change in location on the x axes.
     * @param dy the change in location on the y axes.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * This method construct gameGeometry.Velocity object from..
     * @param angle the angle of the vector.
     * @param speed the length of the vector.
     * @return gameGeometry.Velocity object.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
            double dx = speed * Math.sin(angle * Math.PI / 180);
            double dy = -speed * Math.cos(angle * Math.PI / 180);
            return new Velocity(dx, dy);
    }
    /**
     * this method returns..
     * @return the "speed" of this gameGeometry.Velocity.
     */
    public double getSpeed() {
        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }

    /**
     * this method returns..
     * @return the velocity's change in the x axis.
     */
    public double getX() {
        return this.dx;
    }
    /**
     * this method returns..
     * @return the velocity's change in the y axis.
     */
    public double getY() {
        return this.dy;
    }
    /**
     * this method set a new velocity value in the x axis.
     * @param newX the new x value.
     */
    public void setX(double newX) {
        this.dx = newX;
    }
    /**
     * this method set a new velocity value in the y axis.
     * * @param newY the new y value.
     */
    public void setY(double newY) {
        this.dy = newY;
    }
    /**
     * this method moves a point.
     * @param p a point with position (x,y).
     * @return a new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}