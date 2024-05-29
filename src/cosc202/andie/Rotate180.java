package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to rotate the image 180 degrees.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Jennifer Puzey
 * @version 1.0
 */
public class Rotate180 implements ImageOperation, java.io.Serializable {

    /**
     *<p>
     * Rotate the image 180 degrees.
     * </p> 
     * 
     * @param input The image to be rotated.
     * @return The resulting rotated image.
     */
    public BufferedImage apply(BufferedImage input) {
        RotateLeft rotateLeft = new RotateLeft();
        BufferedImage temp = rotateLeft.apply(input); 
        return rotateLeft.apply(temp);
    }
}