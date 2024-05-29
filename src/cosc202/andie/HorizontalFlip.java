package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to flip the image horizontally (mirror along the y-axis).
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Evan Ting
 * @version 1.0
 */
public class HorizontalFlip implements ImageOperation, java.io.Serializable {

    /**
     * <p>
     * Flip the image horizontally. This means that everything in the left half 
     * of the photo now appears in the right half and vice versa. 
     * </p>
     * 
     * @param input The image to be flipped.
     * @return The resulting flipped image.
     */
    public BufferedImage apply(BufferedImage input) {
        
        for (int y = 0; y < input.getHeight(); ++y) {
            int width = input.getWidth(); 

            for (int x = 0; x < width / 2; ++x) {
                int argb = input.getRGB(x, y);
                input.setRGB(x, y, input.getRGB(width - (x + 1), y));
                input.setRGB(width - (x + 1), y, argb);
            }
        }
        
        return input;
    }
}