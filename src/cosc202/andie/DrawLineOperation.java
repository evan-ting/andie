package cosc202.andie;

import java.awt.*;
import java.awt.image.*;

/**
 * <p>
 * ImageOperation to draw a line in ANDIE.
 * </p>
 *
 * <p>
 * This class simply allows a line to be drawn between the two selected points in ANDIE.
 * </p>
 *
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 *
 * @author Evan Ting
 * @version 1.0
 */
public class DrawLineOperation implements ImageOperation, java.io.Serializable {
    
    /** The colour of the line to be drawn. */
    private String colour;

    /** The x position of one corner of the selected region. */
    private int x1;

    /** The y position of one corner of the selected region. */
    private int y1;

    /** The x position of the diagonal corner of the selected region. */
    private int x2;

    /** The y position of the diagonal corner of the selected region. */
    private int y2;

    /**
     * <p>
     * Construct a DrawLineOperation with the given colour.
     * </p>
     *
     * @param colour The colour of the line to be drawn
     * @param x1 position of one corner of selected region
     * @param y1 position of one corner of selected region
     * @param x2 position of diagonal corner of selected region
     * @param y2 position of diagonal corner of selected region
     */
    DrawLineOperation(String colour, int x1, int y1, int x2, int y2) {
        this.colour = colour;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * <p>
     * Apply a line drawing operation to an image.
     * </p>
     *
     * <p>
     * A line is drawn between the two points that the user selects within ANDIE.
     * </p>
     *
     * @param input The image to draw a line on
     * @return The resulting image with the newly drawn line.
     */
    public BufferedImage apply(BufferedImage input) {
        Graphics2D g2d = input.createGraphics();

        // Draw the line, using the specified colour.
        if (colour.equals(Andie.getText("red"))) {
            g2d.setColor(Color.RED);
        }
        else if (colour.equals(Andie.getText("orange"))) {
            g2d.setColor(Color.ORANGE);
        }
        else if (colour.equals(Andie.getText("yellow"))) {
            g2d.setColor(Color.YELLOW);
        }
        else if (colour.equals(Andie.getText("green"))) {
            g2d.setColor(Color.GREEN);
        }
        else if (colour.equals(Andie.getText("blue"))) {
            g2d.setColor(Color.BLUE);
        }
        else if (colour.equals(Andie.getText("purple"))) {
            g2d.setColor(new Color(255, 0, 255));
        }
        else if (colour.equals(Andie.getText("black"))) {
            g2d.setColor(Color.BLACK);
        } 
        else if (colour.equals(Andie.getText("white"))) {
            g2d.setColor(Color.WHITE);
        }

        g2d.drawLine(x1, y1, x2, y2);
        g2d.dispose();
        return input;
    }
}