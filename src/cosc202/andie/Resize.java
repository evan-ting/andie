package cosc202.andie;

import java.awt.*;
import java.awt.image.*;

/**
 * <p>
 * ImageOperation to resize an image based on a percentage scale.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Ari Zuo
 * @version 1.0
 */
public class Resize implements ImageOperation, java.io.Serializable {

    /** 
     * <p>
     * The scale for the size of the new image, relative to its original size. 
     * This is expressed as a percentage. 
     * </p>
     * 
     */
    private double scale;

    /**
     * <p>
     * Create a new resize operation.
     * </p>
     * 
     * @param scale A user-selected scale for resizing the image.
     */
    Resize(double scale) {
        this.scale = scale;
    }

    /**
     *<p>
     * Resize the image based on input scale.
     * </p> 
     * 
     * @param input The image to be resized.
     * @return The resulting resized image.
     */
    public BufferedImage apply(BufferedImage input) {
        int newWidth = (int) (input.getWidth() * scale / 100);
        int newHeight = (int) (input.getHeight() * scale / 100);
        BufferedImage output = new BufferedImage(newWidth, newHeight, input.getType());
        Graphics2D G1 = output.createGraphics();
        
        G1.drawImage(input, 0, 0, newWidth, newHeight, null);
        G1.dispose();

        return output;            
    }
}