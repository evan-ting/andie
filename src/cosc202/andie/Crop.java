package cosc202.andie;

import java.awt.image.*;
import java.awt.*;

/**
 * <p>
 * ImageOperation to crop the image, based on the user-selected region.
 * </p>
 *
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 *
 * @author Ari Zuo
 * @author Evan Ting
 */
public class Crop implements ImageOperation, java.io.Serializable {
    
    /** The top-left corner of the user-selected region. */
    private Point startPoint;

    /** The bottom-right corner of the user-selected region. */
    private Point endPoint;

    /**
     * <p>
     * Create a new Crop operation.
     * </p>
     * 
     * @param startPoint The top-left corner of the user-selected region
     * @param endPoint The bottom-right corner of the user-selected region
     * 
     */    
    Crop(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    /**
     * <p>
     * Crop the image, based on the user-selected region.
     * </p>
     * 
     * @param input The image to be cropped
     * @return The resulting cropped image
     */
    public BufferedImage apply(BufferedImage input) {
        Rectangle selection = new Rectangle(startPoint);
        selection.add(endPoint);
        return cropImage(input, selection);
    }

    /**
     * <p>
     * A helper method that crops the specified image and returns the result, 
     * based on the given selection region.
     * </p>
     * 
     * @param image The image to be cropped
     * @param selection The user-selected region that will be retained after cropping
     * @return The cropped image
     */
    private BufferedImage cropImage(BufferedImage image, Rectangle selection) {
        int width = (int) selection.getWidth();
        int height = (int) selection.getHeight();

        BufferedImage croppedImage = new BufferedImage(width, height, image.getType());
        Graphics2D g = croppedImage.createGraphics();
        g.drawImage(image.getSubimage((int) selection.getX(), (int) selection.getY(), width, height), 0, 0, null);
        g.dispose();
        return croppedImage;
    }
}