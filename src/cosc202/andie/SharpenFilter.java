package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to apply a Sharpen filter.
 * </p>
 *
 * <p>
 * A Sharpen filter sharpens an image by making each pixel more like its neighbours
 * it enhances the differences between neighbouring values.
 * </p>
 *
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 *
 * @author Layton Ford
 * @version 1.0
 */
public class SharpenFilter implements ImageOperation, java.io.Serializable {
    
    /**
     * <p>
     * Apply a Sharpen filter to an image.
     * </p>
     *
     * <p>
     * A Sharpen filter sharpens an image by making each pixel more like its neighbours
     * it enhances the differences between neighbouring values.
     * </p>
     *
     * @param input The image to apply the Sharpen filter to.
     * @return The resulting (sharpen) image.
     */
    public BufferedImage apply(BufferedImage input) {
        float[] array = {
            0    , -0.5f, 0,
            -0.5f, 3    , -0.5f,
            0    , -0.5f, 0
        };

        Kernel kernel = new Kernel(3, 3, array);
        ConvolutionOperation convOp = new ConvolutionOperation(kernel);
        return convOp.apply(input, false, -1);
    }
}