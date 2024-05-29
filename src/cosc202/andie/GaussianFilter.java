package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to apply a Gaussian Blur filter.
 * </p>
 *
 * <p>
 * A Gaussian filter blurs an image producing an effect similar to an out of focus
 * camera lens.
 * </p>
 *
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 *
 * @see java.awt.image.ConvolveOp
 * @author Layton Ford
 * @version 1.0
 */
public class GaussianFilter implements ImageOperation, java.io.Serializable {
    
    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2
     * a 5x5 filter, and so forth.
     */
    private int radius;

    /**
     * <p>
     * Construct a Gaussian filter with the given size.
     * </p>
     *
     * <p>
     * The size of the filter is the 'radius' of the convolution kernel used.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * Larger filters give a stronger blurring effect.
     * </p>
     *
     * @param radius The radius of the newly constructed GaussianFilter
     */
    GaussianFilter(int radius) {
        this.radius = radius;
    }

    /**
     * <p>
     * Construct a Gaussian filter with the default size.
     * </p
     * >
     * <p>
     * By default, a Gaussian filter has radius 1.
     * </p>
     *
     * @see GaussianFilter(int)
     */
    GaussianFilter() {
        this(1);
    }

    /**
     * <p>
     * Apply a Gaussian filter to an image.
     * </p>
     *
     * <p>
     * As with many filters, the Gaussian filter is implemented via convolution.
     * The size of the convolution kernel is specified by the {@link radius}.
     * Larger radii lead to stronger blurring.
     * </p>
     *
     * @param input The image to apply the Gaussian filter to.
     * @return The resulting (blurred) image.
     */
    public BufferedImage apply(BufferedImage input) {
        int size = (2*radius+1);
        float[] array = new float[size * size];
        float sigma = radius / 3.0f;
        float sum = 0;

        int center = size / 2;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                float x = i - center;
                float y = j - center;
                float value = (float)Math.exp(-(x * x + y * y) / (2 * sigma * sigma));
                array[j * size + i] = value;
                sum += value;
            }
        }

        for (int i = 0; i < array.length; i++) {
            array[i] /= sum;
        }

        Kernel kernel = new Kernel(2*radius+1, 2*radius+1, array);
        ConvolutionOperation convOp = new ConvolutionOperation(kernel);
        return convOp.apply(input, false, -1);
    }
}