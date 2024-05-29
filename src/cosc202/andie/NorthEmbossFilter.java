package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * The North Emboss Filter.
 * </p>
 *
 * <p>
 * The Emboss Filter that presses the image North.
 * </p>
 *
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 *
 * @author Layton Ford
 * @version 1.0
 */
public class NorthEmbossFilter implements ImageOperation, java.io.Serializable {

    /**
     * <p>
     * Apply a North Emboss Filter.
     * </p>
     *
     * <p>
     * An Emboss Filter that presses the image North.
     * </p>
     *
     * @param input The image to apply the Emboss Filter to.
     * @return The resulting image.
     */
    public BufferedImage apply(BufferedImage input) {
        float[] array = {
            0,  -1f,  0,
            0 , 0, 0,
            0 ,  +1f,  0
        };

        Kernel kernel = new Kernel(3, 3, array);
        ConvolutionOperation convOp = new ConvolutionOperation(kernel);
        return convOp.apply(input, true, 1);
    }
}