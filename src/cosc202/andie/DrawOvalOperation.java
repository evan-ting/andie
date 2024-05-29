package cosc202.andie;

import java.awt.*;
import java.awt.image.*;

/**
 * <p>
 * ImageOperation to draw a circle/oval in ANDIE.
 * </p>
 *
 * <p>
 * This class simply allows a circle/oval to be drawn over the selected region
 * of the image in ANDIE.
 * </p>
 *
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 *
 * @author Evan Ting
 * @version 1.0
 */
public class DrawOvalOperation implements ImageOperation, java.io.Serializable {

    /** The colour of the oval/circle to be drawn. */
    private String colour;

    /** The fill type of the oval/circle to be drawn. */
    private String fillType;

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
     * Construct a DrawOvalOperation with the given colour.
     * </p>
     *
     * @param colour The colour of the oval/circle to be drawn
     * @param fillType of the oval/circle to be drawn
     * @param x1 position of one corner of selected region
     * @param y1 position of one corner of selected region
     * @param x2 position of diagonal corner of selected region
     * @param y2 position of diagonal corner of selected region
     */
    DrawOvalOperation(String colour, String fillType, int x1, int y1, int x2, int y2) {
        this.colour = colour;
        this.fillType = fillType;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * <p>
     * Apply an oval drawing operation to an image.
     * </p>
     *
     * <p>
     * A circle/oval is drawn over the selected region of the image in ANDIE.
     * </p>
     *
     * @param input The image to draw a circle/oval on
     * @return The resulting image with the newly drawn circle/oval.
     */
    public BufferedImage apply(BufferedImage input) {   
        Graphics2D g2d = input.createGraphics();
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int width = Math.abs(x2 - x1);
        int height = Math.abs(y2 - y1);

        // Draw the oval/circle, using the specified colour.
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
    
        // Draw the oval/circle, using the specified fill type.
        if (fillType.equals(Andie.getText("solidFill"))) {
            g2d.fillOval(x, y, width, height);
        }
        else if (fillType.equals(Andie.getText("outlineFill"))) {
            g2d.drawOval(x, y, width, height);
        }

        g2d.dispose();
        return input;
    }

}