package gamelevels;

import biuoop.DrawSurface;
import gamegeometry.Point;
import interfaces.Sprite;

import java.awt.Color;
/**
 * This class creating the background of the DirectHit level.
 */
public class Background1 implements Sprite {
    private Point pointOfTarget;
    private int targetSize;
    /**
     * construct Background1 object from the place of the Target.
     * @param p the target's point.
     */
    public Background1(Point p) {
        this.pointOfTarget = p;
        this.targetSize = 40;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLUE);
        int xOfTarget = (int) this.pointOfTarget.getX();
        int yOfTarget = (int) this.pointOfTarget.getY();
        int a = 30;
        d.drawCircle(xOfTarget, yOfTarget, this.targetSize * 3);
        d.drawCircle(xOfTarget, yOfTarget, this.targetSize * 2);
        d.drawCircle(xOfTarget, yOfTarget, this.targetSize);
        d.drawLine(xOfTarget, 0, xOfTarget, yOfTarget - a);
        d.drawLine(xOfTarget, yOfTarget + a, xOfTarget, yOfTarget + 5 * a);
        d.drawLine(xOfTarget + a, yOfTarget, xOfTarget + 5 * a, yOfTarget);
        d.drawLine(xOfTarget - a, yOfTarget, xOfTarget - 5 * a, yOfTarget);
    }

    @Override
    public void timePassed() {
    }
}
