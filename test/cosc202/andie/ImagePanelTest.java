package cosc202.andie;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/** 
 * <p>
 * Sample unit tests for the ImagePanel class, as shown in the lab book.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Evan Ting
 */
public class ImagePanelTest {
    
    /** This just a default test to check that JUnit5 is working. */
    @Test
    void initialDummyTest() {}

    /** A test to verify the zoom percentage is set 100 on application startup. */
    @Test 
    void getZoomInitialValue() {
        ImagePanel testPanel = new ImagePanel();
        Assertions.assertEquals(100.0, testPanel.getZoom());
    }

    /** 
     * A test to verify that the zoom percentage is set to 50,
     * if a zoom percentage that is less than 50 is applied.
     */
    @Test 
    void getZoomAftersetZoom() {
        ImagePanel testPanel = new ImagePanel();
        testPanel.setZoom(0.0);
        Assertions.assertFalse(testPanel.getZoom() == 100.0);
        Assertions.assertTrue(testPanel.getZoom() >= 50.0);
    }
}