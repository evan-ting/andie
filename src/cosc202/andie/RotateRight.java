package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to rotate the image 90 degrees to the right.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Jennifer Puzey
 * @version 1.0
 */
public class RotateRight implements ImageOperation, java.io.Serializable {

    /**
     * <p>
     * Rotate the image to the right 90 degrees.
     * </p>
     * 
     * @param input The image to be rotated.
     * @return The resulting rotated image.
     */
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();
        BufferedImage output = new BufferedImage(height, width, input.getType());
        int[][] pixelArray = new int[width][height];

        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                pixelArray[x][y] = input.getRGB(x, y);
            }
        }

        for (int x = 0; x < width; x ++){
            for (int y = 0; y < height; y++){
                output.setRGB(height - 1 - y, x, pixelArray[x][y]);
            }
        }

        return output;
    }
}