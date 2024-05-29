package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to adjust the brightness and contrast of an image.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Jennifer Puzey
 * @version 1.0
 */
public class BrightnessContrastAdjust implements ImageOperation, java.io.Serializable {
    
    /** The brightness of the image, as a percentage. */
    int brightness;

    /** The contrast of the image, as a percentage. */
    int contrast;

    /**
     * <p>
     * Create a new BrightnessContrastAdjustment operation.
     * </p>
     * 
     * @param brightness The brightness of the image
     * @param contrast The contrast of the image
     */
    BrightnessContrastAdjust(int brightness, int contrast) {
        this.brightness = brightness;
        this.contrast = contrast;
    }

    /**
     * <p>
     * Apply brightness and contrast changes to an image.
     * </p>
     * 
     * @param input The image to be adjusted
     * @return The resulting adjusted image.
     */
    public BufferedImage apply (BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int rgb = input.getRGB(x,y);
                int alpha =(rgb>>>24)& 0xFF;
                int red =(rgb>>16)& 0xFF;
                int green =(rgb>>8)& 0xFF;
                int blue = rgb & 0xFF;

                red = adjust(red, contrast, brightness);
                green = adjust(green, contrast, brightness);
                blue = adjust(blue, contrast, brightness);

                int newRgb = (alpha<<24)|(red<<16)|(green<<8)|blue;

                input.setRGB(x,y,newRgb);
            }
        }

        return input;
    }

    /**
     * <p>
     * Helper method to apply the brightness and contrast changes to each color component
     * of the input image
     * </p>
     * 
     * @param colorComponent from the image to be adjusted
     * @param contrast value by which to adjust the contrast
     * @param brightness value by which to adjust the brightness
     * @return adjusted int
     */
    private int adjust(int colorComponent, int contrast, int brightness) {
        double adjusted = ((1+(double)contrast/100)*(colorComponent-127.5)+127.5)*(1+(double)brightness/100);
        return (int)Math.max(0, Math.min(255, adjusted));
    }   
}