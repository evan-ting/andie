package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to flip the image vertically (mirror along the x-axis).
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Evan Ting
 * @version 1.0
 */
public class VerticalFlip implements ImageOperation, java.io.Serializable {

    /**
     * <p>
     * Flip the image vertically. This means that everything in the top half 
     * of the photo now appears in the bottom half and vice versa. 
     * </p>
     * 
     * @param input The image to be flipped.
     * @return The resulting flipped image.
     */
    public BufferedImage apply(BufferedImage input) {
        for (int x = 0; x < input.getWidth(); ++x) {
            int height = input.getHeight(); 

            for (int y = 0; y < height / 2; ++y) {
                int argb = input.getRGB(x, y);
                input.setRGB(x, y, input.getRGB(x, height - (y + 1)));
                input.setRGB(x, height - (y + 1), argb);
            }
        }
        
        return input;
    }
}