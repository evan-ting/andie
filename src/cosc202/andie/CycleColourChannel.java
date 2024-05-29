package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * A class that allows for the colour channel of an image to cycle (swaps around the red, green, and blue colour values).
 * It can cycle the colours in any possible combination.
 * This combination is selected by the user.
 * </p>
 * 
 * @author Charlotte Williams
 * @author Evan Ting
 */
public class CycleColourChannel implements ImageOperation, java.io.Serializable {

    /** This variable represents what way the user has chosen to swap the colour values. */
    private String channels;

    /**
     * <p>
     * Constructor to assign selection the user opted value
     * </p>
     * 
     * @param channels of the input image
     */
    CycleColourChannel(String channels) {
        this.channels = channels;
    }  

    /**
     * <p>
     * Changes the colour channel of an image dependent on user input.
     * </p>
     * @param input the image to apply changes to
     * @return the resulting image after changes
     */
    public BufferedImage apply(BufferedImage input) {
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int argb = input.getRGB(x, y);
                int pixel = ((argb & 0xFF000000) >>> 24) << 24;
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);

                if (channels.equals("GBR")) {
                    pixel |= (b << 16) | (r << 8) | g;
                }
                else if (channels.equals("BRG")) {
                    pixel |= (g << 16) | (b << 8) | r;
                }
                else if (channels.equals("RBG")) {
                    pixel |= (r << 16) | (b << 8) | g;
                }
                else if (channels.equals("GRB")) {
                    pixel |= (g << 16) | (r << 8) | b;
                }
                else {
                    pixel |= (b << 16) | (g << 8) | r;
                }

                input.setRGB(x, y, pixel);
            }
        }

        return input;
    }   
}