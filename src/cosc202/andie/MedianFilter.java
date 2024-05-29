package cosc202.andie;

import java.awt.image.*;
import java.util.*;

/**
 * <p>
 * ImageOperation to apply a Median filter.
 * </p>
 * 
 * <p>
 * A Median filter blurs an image by replacing each pixel by the median rgb of the
 * pixels in a surrounding neighbourhood.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Evan Ting
 * @version 1.0
 */
public class MedianFilter implements ImageOperation, java.io.Serializable {
    
    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a 5x5 filter, and so forth.
     */
    private int radius;

    /**
     * <p>
     * Construct a Median filter with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is determined by the radius of the surrounding neighbourhood.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * </p>
     * 
     * @param radius The radius of the newly constructed MedianFilter
     */
    MedianFilter(int radius) {
        this.radius = radius;    
    }

    /**
     * <p>
     * Apply a Median filter to an image.
     * </p>
     * 
     * <p>
     * The Median filter is implemented by replacing each pixel with the median rgb value
     * of the surrounding neighbourhood.
     * The size of the surrounding neighbourhood is specified by the {@link radius}.  
     * </p>
     * 
     * @param input The image to apply the Median filter to.
     * @return The resulting (blurred) image.
     */
    public BufferedImage apply(BufferedImage input) {
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        int side = 2 * radius + 1; 
        int height = input.getHeight();
        int width = input.getWidth();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int[] r = new int[side * side];
                int[] g = new int[side * side];
                int[] b = new int[side * side];
                int count = 0;

                // Retrieve the colour channel values for each pixel in the local neighbourhood.
                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dx = -radius; dx <= radius; dx++) {

                        // Out of bounds pixels are assumed to have the same value as the nearest edge pixel. 
                        int pixelX = Math.min(Math.max(x + dx, 0), width - 1);
                        int pixelY = Math.min(Math.max(y + dy, 0), height - 1);
                        int pixel = input.getRGB(pixelX, pixelY);
                        
                        r[count] = (pixel & 0x00FF0000) >> 16;
                        g[count] = (pixel & 0x0000FF00) >> 8;
                        b[count] = pixel & 0x000000FF;
                        count++;
                    }
                }

                // Compose the 'median pixel'.
                Arrays.sort(r);
                Arrays.sort(g);
                Arrays.sort(b);

                int a = (input.getRGB(x, y) & 0xFF000000) >>> 24;
                int mid = r.length / 2;
                int argb = (a << 24) | (r[mid] << 16) | (g[mid] << 8) | b[mid]; 
                output.setRGB(x, y, argb);
            }
        }

        return output;
    }
}