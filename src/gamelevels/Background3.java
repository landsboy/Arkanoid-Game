package gamelevels;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;
/**
 * This class draw's the background of the Green 3 level.
 */
public class Background3 implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        //The background color:
        d.setColor(new Color(0, 153, 0));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        //Creating the building
        d.setColor(new Color(51, 51, 51).brighter());
        d.fillRectangle(145, 200, 10, 240);
        d.setColor(new Color(51, 51, 51));
        d.fillRectangle(135, 410, 30, 40);

        //Creating the light on the building:
        d.setColor(Color.ORANGE);
        d.fillCircle(150, 200, 15);
        d.setColor(Color.RED);
        d.fillCircle(150, 200, 10);
        d.setColor(Color.WHITE);
        d.fillCircle(150, 200, 5);

        //Creating the build's floors:
        d.setColor(new Color(51, 51, 51).darker());
        d.fillRectangle(100, 450, 100, 150);
        d.setColor(Color.WHITE);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(110 + 18 * i, 460 + 30 * j, 10, 25);
            }
        }
    }

    @Override
    public void timePassed() {
    }
}
