package cosc202.andie;

import java.awt.image.*;
import java.awt.*;

/**
 * <p>
 * A Block Averaging Operation to be applied to an image
 * </p>
 *
 * <p>
 * Image operation that takes a user selected block size and averages the colour on a regular grid
 * with the average pixel value within that region.
 * Gives the appearance of pixelating the image.
 * </p>
 *
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 *
 * @author Charlotte Williams
 * @version 1.0
 */
public class BlockAveraging implements ImageOperation, java.io.Serializable {

    /** The width of the block to be averaged, in pixels. */
    private int blockHeight;

    /** The height of the block to be averaged, in pixels. */
    private int blockWidth;

    /**
     * <p>
     * Creates a BlockAveraging operation that takes user selected blockWidth and blockHeight
     * </p>
     * 
     * @param blockHeight The height of the block to be averaged, in pixels.
     * @param blockWidth The width of the block to be averaged, in pixels.
     */
    BlockAveraging(int blockHeight, int blockWidth) {
        this.blockHeight = blockHeight;
        this.blockWidth = blockWidth;
    }

    /**
     * <p>
     * Creates a default BlockAveraging operation, default height and width is 1, therefore no visual effect
     * </p>
     */
    BlockAveraging() {
        this.blockHeight = 1;
        this.blockWidth = 1;
    }

    /**
     * <p>
     * Apply block averaging to an image
     * </p>
     * 
     * <p>
     * This method was adapted from user Thibaut Mottet to suit the andie application https://stackoverflow.com/questions/15777821/how-can-i-pixelate-a-jpg-with-java
     * License: https://creativecommons.org/licenses/by-sa/3.0/ used under the CC BY-SA 3.0 license
     * </p>
     * 
     * @param input The image to be altered
     * @return The resulting image with block averaging
     */
    public BufferedImage apply(BufferedImage input) {
        for (int i = 0; i < input.getHeight(); i += blockHeight) {
            for (int j = 0; j < input.getWidth(); j += blockWidth) {
                BufferedImage subImage = getSubImage(input, j, i, blockHeight, blockWidth);
                Color averageCol = getAverageColour(subImage);

                for (int ii = i; (ii < i + blockHeight) && (ii < input.getHeight()); ii ++) {
                    for (int jj = j; (jj < j + blockWidth) && (jj < input.getWidth()); jj ++) {
                        input.setRGB(jj, ii, averageCol.getRGB());
                    }
                }
            }
        }
        return input;
    }

    /**
     * <p>
     * Method to retrieve part of an image
     * Used to get one block at a time so then can find the average colour using getAverageColour(input).
     * Handles cases where x and y are out of bounds
     * </p>
     * 
     * <p>
     * This method was sourced from user Thibaut Mottet https://stackoverflow.com/questions/15777821/how-can-i-pixelate-a-jpg-with-java
     * License: https://creativecommons.org/licenses/by-sa/3.0/ used under the CC BY-SA 3.0 license
     * </p>
     * 
     * @param input image to be block averaged
     * @param x starting horizontal point
     * @param y starting vertical point
     * @param blockHeight height of block / sub image, in pixels
     * @param blockWidth width of block / sub image, in pixels
     * @return block sub-image
     */
    public static BufferedImage getSubImage(BufferedImage input, int x, int y, int blockHeight, int blockWidth) {
        if(x < 0) x = 0;
        if (y < 0) y = 0;
        if (x > input.getWidth()) x = input.getWidth();
        if (y > input.getHeight()) y = input.getHeight();
        if ((x + blockWidth) > input.getWidth()) blockWidth = input.getWidth() - x;
        if ((y + blockHeight) > input.getHeight()) blockHeight = input.getHeight() - y;
        return input.getSubimage(x, y, blockWidth, blockHeight);
    }

    /**
     * <p>
     * Finds the average colour of all pixels by averaging the red, green and blue values
     * </p>
     * 
     * <p>
     * This method was adapted from user Ceilvia C https://stackoverflow.com/questions/15777821/how-can-i-pixelate-a-jpg-with-java
     * License: https://creativecommons.org/licenses/by-sa/4.0/ used under the CC BY-SA 4.0 license
     * </p>
     * 
     * @param input the block subimage 
     * @return Average colour of block
     */
    public static Color getAverageColour(BufferedImage input) {
        int red = 0;
        int green = 0;
        int blue = 0;
        int count = 0;
        Color col = new Color(input.getRGB(0, 0), true);

        for (int i = 0; i < input.getWidth(); i ++) {
            for (int j = 0; j < input.getHeight(); j ++) {
                col = new Color(input.getRGB(i, j), true);
                red += col.getRed();
                green += col.getGreen();
                blue += col.getBlue();
                count ++;
            }
        }
        
        return new Color(red/count, green/count, blue/count, col.getAlpha());
    }
}