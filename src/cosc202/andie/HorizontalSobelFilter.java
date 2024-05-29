package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * The Horizontal Sobel Filter.
 * </p>
 *
 * <p>
 * The Sobel Filter that presses the image horizontally.
 * </p>
 *
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 *
 * @author Layton Ford
 * @version 1.0
 */
public class HorizontalSobelFilter implements ImageOperation, java.io.Serializable {
    
    /**
     * <p>
     * Apply a Horizontal Sobel Filter.
     * </p>
     *
     * <p>
     * An Sobel Filter that presses the image horizontally.
     * </p>
     *
     * @param input The image to apply the Sobel Filter to.
     * @return The resulting image.
     */
    public BufferedImage apply(BufferedImage input) {
        float[] array = {
            -0.5f,  0,  +0.5f,
            -1f,    0,  +1f,
            -0.5f,  0, +0.5f
        };

        Kernel kernel = new Kernel(3, 3, array);
        ConvolutionOperation convOp = new ConvolutionOperation(kernel);
        return convOp.apply(input, true, 2);
    }
}