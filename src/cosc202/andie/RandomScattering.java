package cosc202.andie;

import java.awt.image.*;
import java.awt.*;
import java.util.*;

/**
 * <p>
 * Actions provided by the View menu.
 * </p>
 * 
 * <p>
 * Image operation that takes a user selected radius and randomly scatters all the pixels. 
 * Achieved by replacing each pixel with a random pixel in the radius.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Charlotte Williams
 * @version 1.0
 */
public class RandomScattering implements ImageOperation, java.io.Serializable{
    
    /* User selected radius for selecting random pixels from */
    int radius;

    /**
     * <p>
     * Creates a RandomScattering operation based on a user selected radius size
     * </p>
     * 
     * @param radius to replace pixels from within
     */
    RandomScattering(int radius) {
        this.radius = radius;
    }

    /**
     * <p>
     * Applies random scattering to an image
     * </p>
     * 
     * @param input The image to be altered
     * @return The resulting image with block averaging
     */
    public BufferedImage apply(BufferedImage input) {
        for (int i = 0; i < input.getHeight(); i ++) {
            for (int j = 0; j < input.getWidth(); j ++) {
                int randHeight = getRandomHeight(i - radius, i + radius, input);
                int randWidth = getRandomWidth(j - radius, j + radius, input);
                Color col = new Color(input.getRGB(randWidth, randHeight), true);
                input.setRGB(j, i, col.getRGB());
            }
        }

        return input;
    }

    /**
     * <p>
     * Method to get a random height that is within the specified radius and bounds of the image
     * </p> 
     * 
     * @param min minimum random value that can be returned
     * @param max maximum random value that can be returned
     * @return random value between min and max (inclusive)
     */
    public int getRandomHeight(int min, int max, BufferedImage input) {
        Random rand = new Random();
        int test = rand.ints(min, max + 1).findFirst().getAsInt();
        if (test < 0) test = 0 + rand.nextInt(radius + 1);
        if (test > input.getHeight() - 1) test = (input.getHeight() -1) - rand.nextInt(radius + 1);
        return test;
    }

    /**
     * <p>
     * Method to get a random width that is within the specified radius and bounds of the image
     * </p>
     * 
     * @param min minimum random value that can be returned
     * @param max maximum random value that can be returned
     * @return random value between min and max (inclusive)
     */
    public int getRandomWidth(int min, int max, BufferedImage input) {
        Random rand = new Random();
        int test = rand.ints(min, max + 1).findFirst().getAsInt();
        if (test < 0) test = 0 + rand.nextInt(radius + 1);
        if (test > input.getWidth() - 1) test = (input.getWidth() - 1) - rand.nextInt(radius + 1);
        return test;
    }
}