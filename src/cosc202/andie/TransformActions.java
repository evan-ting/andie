package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.*;

/**
 * <p>
 * Actions provided by the Transform menu.
 * </p>
 * 
 * <p>
 * The Transform menu contains features that allow the user to 
 * change the size and orientation of the image loaded into 
 * the application.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Evan Ting
 * @author Jennifer Puzey
 * @author Ari Zuo
 * @author Charlotte Williams
 * @version 1.0
 */
public class TransformActions {

    /** A list of actions for the Transform menu. */
    static ArrayList<Action> actions;
    
    /** A list of keyboard shortcuts for all transform actions */
    static ArrayList<KeyStroke> shortcuts;
   
    /**
     * <p>
     * Create a set of Transform menu actions.
     * </p>
     */
    public TransformActions() {
        actions = new ArrayList<Action>();
        actions.add(new HorizontalFlipAction(Andie.getText("horizontalFlipText"), null, Andie.getText("horizontalFlipDescription"), null));
        actions.add(new VerticalFlipAction(Andie.getText("verticalFlipText"), null, Andie.getText("verticalFlipDescription"), null));
        actions.add (new RotationLeftAction(Andie.getText("leftRotationText"), null,Andie.getText("leftRotationDescription"),null));
        actions.add(new RotationRightAction(Andie.getText("rightRotationText"), null, Andie.getText("rightRotationDescription"), null));
        actions.add(new Rotation180Action(Andie.getText("180RotationText"), null, Andie.getText("180RotationDescription"), null));
        actions.add(new ResizeAction(Andie.getText("imageResizeText"), null, Andie.getText("imageResizeDescription"), null));
        actions.add(new CropAction(Andie.getText("imageCropText"), null, Andie.getText("imageCropDescription"), null));

        shortcuts = new ArrayList<KeyStroke>();
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_8, ActionEvent.CTRL_MASK));
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));        
    }

    /**
     * <p>
     * Create a menu containing the list of Transform actions.
     * </p>
     * 
     * @return The Transform menu UI element.
     */
    public JMenu createMenu() {
        JMenu transformMenu = new JMenu(Andie.getText("transformMenuText"));

        for (int i = 0; i < actions.size(); ++i) {
            JMenuItem transformMenuItem = new JMenuItem(actions.get(i));
            transformMenuItem.setAccelerator(shortcuts.get(i));
            transformMenu.add(transformMenuItem);    
        }
        return transformMenu;
    }

    /**
     * <p>
     * Action to flip an image horizontally.
     * </p>
     * 
     * @see HorizontalFlip
     */
    public class HorizontalFlipAction extends ImageAction {

        /**
         * <p>
         * Create a new horizontal-flip action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        HorizontalFlipAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the horizontal-flip action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the HorizontalFlipAction is triggered.
         * The action will then horizontally flip the image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new HorizontalFlip());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to flip an image vertically.
     * </p>
     * 
     * @see VerticalFlip
     */
    public class VerticalFlipAction extends ImageAction {

        /**
         * <p>
         * Create a new vertical-flip action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        VerticalFlipAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the vertical-flip action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the VerticalFlipAction is triggered.
         * The action will then vertically flip the image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new VerticalFlip());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to rotate an image to the left 90 degrees
     * </p>
     *
     */
    public class RotationLeftAction extends ImageAction {

        /**
         * <p>
         * Create a new RotationLeftAction
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        RotationLeftAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the left rotation action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RotateLeft is triggered.
         * The action will then rotate the image left 90 degrees.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new RotateLeft());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to rotate an image to the right 90 degrees
     * </p>
     *
     */
    public class RotationRightAction extends ImageAction{

        /**
         * <p>
         * Create a new RotationRightAction
         * </p>
         * 
         * @param name the name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        RotationRightAction(String name, ImageIcon icon, String desc, Integer mnemonic){
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the right rotation action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RotateRight is triggered.
         * The action will then rotate the image right 90 degrees.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e){
            target.getImage().apply(new RotateRight());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to rotate an image to the 180 degrees
     * </p>
     *
     */
    public class Rotation180Action extends ImageAction{

        /**
         * <p>
         * Create a new Rotation180Action
         * </p>
         * 
         * @param name the name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        Rotation180Action(String name, ImageIcon icon, String desc, Integer mnemonic){
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the rotate 180 action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the Rotate180Action is triggered.
         * The action will then rotate the image 180 degrees.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e){
            target.getImage().apply(new Rotate180());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to resize an image based on an inputted scale
     * </p>
     *
     */
    public class ResizeAction extends ImageAction {
        
        /** 
         * <p>
         * Create a new resize action
         * </p>
         * 
         * @param name the name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        ResizeAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the resize action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever ResizeAction is triggered.
         * The action will then resize the image based on the scale.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (!target.getImage().hasImage()) {
                JOptionPane.showMessageDialog(target, Andie.getText("noImageToEditWarningText"), Andie.getText("noImageLoadedWarningTitle"), JOptionPane.OK_OPTION);
                return;
            }

            String input = JOptionPane.showInputDialog(target, Andie.getText("imageResizePrompt"));
            
            try {
                double scale = Double.parseDouble(input);
                BufferedImage current = target.getImage().getCurrentImage();

                // The resize percentage must be positive, and the sides of the resized image must be between 1 and 10000 pixels (inclusive).
                if (scale <= 0) {
                    throw new IllegalArgumentException();
                }
                else if ((int)(current.getWidth() * scale / 100) == 0
                        || (int)(current.getHeight() * scale / 100) == 0) {
                    JOptionPane.showMessageDialog(target, Andie.getText("resizeOperationAbortedText1"), Andie.getText("resizeOperationAbortedTitle"), JOptionPane.OK_OPTION);
                }
                else if ((int)(current.getWidth() * scale / 100) > 10000
                        || (int)(current.getHeight() * scale / 100) > 10000) {
                    JOptionPane.showMessageDialog(target, Andie.getText("resizeOperationAbortedText2"), Andie.getText("resizeOperationAbortedTitle"), JOptionPane.OK_OPTION);
                }
                else {
                    target.getImage().apply(new Resize(scale));
                    target.repaint();
                    target.getParent().revalidate();
                }
            }  
            catch (IllegalArgumentException ex){
                JOptionPane.showMessageDialog(target, Andie.getText("resizeOperationAbortedText3"), Andie.getText("resizeOperationAbortedTitle"), JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Action to crop an image, based on the user-selected region.
     */
    public class CropAction extends ImageAction  {

        /** 
         * <p>
         * Create a new CropAction
         * </p>
         * 
         * @param name the name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        CropAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the resize action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever CropAction is triggered.
         * The action will then crop the image, based on the user-selected region.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            ImagePanel imagePanel = target;

            if (!target.getImage().hasImage()) {
                JOptionPane.showMessageDialog(ImageAction.target, Andie.getText("noImageToEditWarningText"), Andie.getText("noImageLoadedWarningTitle"), JOptionPane.OK_OPTION);
                return;
            }

            Point startPoint = imagePanel.getStartPoint();
            Point endPoint = imagePanel.getEndPoint();

            // If no area has been selected for cropping.
            if ((int)startPoint.getX() == (int)endPoint.getX()
                && (int)startPoint.getY() == (int)endPoint.getY()) {
                
                JOptionPane.showMessageDialog(ImageAction.target, Andie.getText("noImageRegionToCropText"), Andie.getText("noImageRegionToCropTitle"), JOptionPane.OK_OPTION);
                return;
            }

            int imageWidth = imagePanel.getImage().getCurrentImage().getWidth();
            int imageHeight = imagePanel.getImage().getCurrentImage().getHeight();
            int startX = (int) Math.max(0, Math.min(startPoint.getX(), endPoint.getX()));
            int startY = (int) Math.max(0, Math.min(startPoint.getY(), endPoint.getY()));

            if (startX < imageWidth && startY < imageHeight) {
                int endX = (int) Math.min(imageWidth, Math.max(startPoint.getX(), endPoint.getX()));
                int endY = (int) Math.min(imageHeight, Math.max(startPoint.getY(), endPoint.getY()));    
                double scale = ImageAction.target.getZoom() / 100;

                startPoint.move((int)(startX / scale), (int)(startY / scale));
                endPoint.move((int)(endX / scale), (int)(endY / scale));
        
                target.getImage().apply(new Crop(startPoint, endPoint));
                target.repaint();
                target.getParent().revalidate();
            } 
            else {
                JOptionPane.showMessageDialog(imagePanel, Andie.getText("noImageRegionToCropText"), Andie.getText("noImageRegionToCropTitle"), JOptionPane.WARNING_MESSAGE);
            }
   
            imagePanel.clearSelection();
        }
    }
}