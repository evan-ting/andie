package cosc202.andie;

import java.awt.image.*;
import java.awt.*;

/** 
 * <p>
 * An image operation that inverts the colour of an image.
 * </p>
 * 
 * @author Charlotte Williams
 * @version 1.0
 */
public class InvertColour implements ImageOperation, java.io.Serializable {

    /**
     * <p>
     * Invert colour of an image.
     * </p>
     * 
     * <p>
     * Finds the RGB values of the all original pixels. Then finds 255 - the original 
     * values in order to find the corresponding inverted RGB values.
     * Creates a new colour from these inverted values and applies it to the image.
     * </p>
     * 
     * @param input The image to be colour inverted
     * @return The resulting colour inverted image.
     */
    public BufferedImage apply(BufferedImage input) {
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int argb = input.getRGB(x, y);
                Color col = new Color(argb, true);
                col = new Color(255 - col.getRed(), 255 - col.getGreen(), 255 - col.getBlue(), col.getAlpha());
                
                input.setRGB(x, y, col.getRGB());
            }
        }
        
        return input;
    }   
}