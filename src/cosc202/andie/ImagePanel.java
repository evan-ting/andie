package cosc202.andie;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * UI display element for {@link EditableImage}s.
 * </p>
 * 
 * <p>
 * This class extends {@link JPanel} to allow for rendering of an image, as well as zooming
 * in and out. 
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @author Ari Zuo
 * @author Evan Ting
 * @version 1.0
 */
public class ImagePanel extends JPanel {
     
    /** The starting point, when the mouse is pressed. */
    private Point startPoint = new Point(-1, -1); 

    /** The end point, when the mouse has been released. */
    private Point endPoint = new Point(-1, -1);
   
    /**
     * Return the starting point of a mouse press.
     * 
     * @return The starting point of a mouse press
     */
    public Point getStartPoint() {
        return startPoint;
    }

    /**
     * Return the end point of a mouse press.
     * 
     * @return The end point of a mouse press
     */
    public Point getEndPoint() {
        return endPoint;
    }
    
    /**
     * The image to display in the ImagePanel.
     */
    private EditableImage image;

    /**
     * <p>
     * The zoom-level of the current view.
     * A scale of 1.0 represents actual size; 0.5 is zoomed out to half size; 1.5 is zoomed in to one-and-a-half size; and so forth.
     * </p>
     * 
     * <p>
     * Note that the scale is internally represented as a multiplier, but externally as a percentage.
     * </p>
     */
    private double scale;

    /**
     * <p>
     * Create a new ImagePanel.
     * </p>
     * 
     * <p>
     * Newly created ImagePanels have a default zoom level of 100%
     * </p>
     */
    public ImagePanel() {
        image = new EditableImage();
        scale = 1.0;
        
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint(); 
            }

            public void mouseReleased(MouseEvent e) {
                endPoint = e.getPoint(); 
                repaint();

                int x = (int)Math.max(startPoint.getX(), endPoint.getX()) + 10;
                int y = (int)Math.max(startPoint.getY(), endPoint.getY()); 

                if ((int)startPoint.getX() != (int)endPoint.getX()
                    || (int)startPoint.getY() != (int)endPoint.getY()) {
                    createAndShowPopupMenu(ImageAction.target, x, y); 
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                endPoint = e.getPoint(); 
                repaint();
            }
        }); 
    }

    /**
     * A helper method that displays the popup menu at the specified 
     * coodinate, on top of the specified ImagePanel.
     * 
     * @param target the background panel that the popup menu will be displayed on
     * @param x the horizontal position of the top left corner of the popup menu
     * @param y the vertical position of the top let corner of the popup menu
     */
    private void createAndShowPopupMenu(ImagePanel target, int x, int y) {
        JPopupMenu popupMenu = new JPopupMenu();
    
        Action cropAction = TransformActions.actions.get(6);
        cropAction.putValue(Action.NAME, Andie.getText("imageCropText"));
        Action drawLineAction = DrawActions.actions.get(0);
        drawLineAction.putValue(Action.NAME, Andie.getText("drawMenuText") + " " + Andie.getText("drawLineText"));
        Action drawRectAction = DrawActions.actions.get(1);
        drawRectAction.putValue(Action.NAME, Andie.getText("drawMenuText") + " " + Andie.getText("drawRectText"));
        Action drawOvalAction = DrawActions.actions.get(2);
        drawOvalAction.putValue(Action.NAME, Andie.getText("drawMenuText") + " " + Andie.getText("drawOvalText"));

        popupMenu.add(cropAction);
        popupMenu.add(drawLineAction);
        popupMenu.add(drawRectAction);
        popupMenu.add(drawOvalAction);

        popupMenu.show(target, x, y);
    }

    /**
     * <p>
     * Get the currently displayed image
     * </p>
     *
     * @return the image currently displayed.
     */
    public EditableImage getImage() {
        return image;
    }

    /**
     * <p>
     * Get the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the original size, 50% is half-size, etc. 
     * </p>
     * @return The current zoom level as a percentage.
     */
    public double getZoom() {
        return 100*scale;
    }

    /**
     * <p>
     * Set the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the original size, 50% is half-size, etc. 
     * The zoom level is restricted to the range [50, 200].
     * </p>
     * @param zoomPercent The new zoom level as a percentage.
     */
    public void setZoom(double zoomPercent) {
        if (zoomPercent < 50) {
            zoomPercent = 50;
        }
        else if (zoomPercent > 200) {
            zoomPercent = 200;
        }

        scale = zoomPercent / 100;
    }

    /**
     * <p>
     * Gets the preferred size of this component for UI layout.
     * </p>
     * 
     * <p>
     * The preferred size is the size of the image (scaled by zoom level), or a default size if no image is present.
     * </p>
     * 
     * @return The preferred size of this component.
     */
    @Override
    public Dimension getPreferredSize() {
        if (image.hasImage()) {
            return new Dimension((int) Math.round(image.getCurrentImage().getWidth()*scale), 
                                 (int) Math.round(image.getCurrentImage().getHeight()*scale));
        } 
        else {
            return new Dimension(450, 450);
        }
    }

    /**
     * <p>
     * (Re)draw the component in the GUI.
     * </p>
     * 
     * @param g The Graphics component to draw the image on.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (image.hasImage()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.scale(scale, scale);
            g2.drawImage(image.getCurrentImage(), null, 0, 0);
            g2.dispose();
        }
        
        if (startPoint != null && endPoint != null) {
            int x = Math.min(startPoint.x, endPoint.x);
            int y = Math.min(startPoint.y, endPoint.y);
            int width = Math.abs(startPoint.x - endPoint.x);
            int height = Math.abs(startPoint.y - endPoint.y);
            g.drawRect(x, y, width, height);
        }
    }

    /**
     * Clear the current region selection within the panel.
     */
    public void clearSelection() {
        startPoint = new Point(-1, -1);
        endPoint = new Point(-1, -1);
        repaint();
    }
}