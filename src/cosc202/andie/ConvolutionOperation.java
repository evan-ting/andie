package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * A Convolution operation to create the filters
 * </p>
 *
 * <p>
 * A Convolution Operation used by the filters in the ANDIE class that extends the
 * filter all the way to the edge of the image.
 * </p>
 *
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 *
 * @author Layton Ford
 * @author Evan Ting
 * @version 1.0
 */

public class ConvolutionOperation implements java.io.Serializable {

    /** The kernel to used for the convolution. */
    private Kernel kernel;

    /**
     * <p>
     * The constructor for the ConvolutionOperation class
     * </p>
     *
     * @param kernel The kernel used in the convolution operation.
     */
    public ConvolutionOperation(Kernel kernel) {
        this.kernel = kernel;
    }

    /**
     * <p>
     * Apply a Convolution Operation that extends to the edge of the image. The {@code weightSum} parameter
     * is only used if the {@code isEdgeDetectionFilter} parameter is set to {@code true}.
     * </p>
     *
     * @param input The image to apply the convolution to.
     * @param isEdgeDetectionFilter Whether the current filter being applied is an edge detection filter or not
     * @param weightSum The sum of all positive weights in the current kernel
     * @return The output image, after applying the convolution
     */
    public BufferedImage apply(BufferedImage input, boolean isEdgeDetectionFilter, int weightSum) {
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        int kernelRadius = kernel.getWidth() / 2;
        int height = input.getHeight();
        int width = input.getWidth();
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                float sumR = 0;
                float sumG = 0;
                float sumB = 0;

                // Apply the kernel weights on the local neighbourhood, for each colour channel.
                for (int dy = -kernelRadius; dy <= kernelRadius; dy++) {
                    for (int dx = -kernelRadius; dx <= kernelRadius; dx++) {
                    
                        // Out of bounds pixels are assumed to have the same value as the nearest edge pixel. 
                        int pixelX = Math.min(Math.max(x + dx, 0), width - 1);
                        int pixelY = Math.min(Math.max(y + dy, 0), height - 1);
                        int pixel = input.getRGB(pixelX, pixelY);
                        float kernelValue = kernel.getKernelData(null)[(dy + kernelRadius) * kernel.getWidth() + (dx + kernelRadius)];

                        sumR += ((pixel & 0x00FF0000) >> 16) * kernelValue;
                        sumG += ((pixel & 0x0000FF00) >> 8) * kernelValue;
                        sumB +=  (pixel & 0x000000FF) * kernelValue;
                    }
                }

                // Rescale/Clip the channel values, and pack them back into a pixel.
                int a = (input.getRGB(x, y) & 0xFF000000) >>> 24;
                int r = -1;
                int g = -1;
                int b = -1;

                if (isEdgeDetectionFilter) {
                    r = (int)(sumR / (weightSum * 2)) + 127;
                    g = (int)(sumG / (weightSum * 2)) + 127;
                    b = (int)(sumB / (weightSum * 2)) + 127;
                }
                else {
                    r = Math.max(0, Math.min((int) sumR, 255));
                    g = Math.max(0, Math.min((int) sumG, 255));
                    b = Math.max(0, Math.min((int) sumB, 255));
                }
                
                int argb = (a << 24) | (r << 16) | (g << 8) | b;
                output.setRGB(x, y, argb);
            }
        }

        return output;
    }
}