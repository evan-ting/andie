package cosc202.andie;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

 /**
  * <p>
  * Actions provided by the Draw menu.
  * </p>
  * 
  * <p>
  * The Draw menu allows the user to draw some basic shapes over the current image
  * within ANDIE. 
  * </p>
  * 
  * <p> 
  * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
  * </p>
  * 
  * @author Evan Ting
  * @version 1.0
  */
public class DrawActions {
    
    /** A list of actions for the Draw menu. */
    static ArrayList<Action> actions;

    /** A list of keyboard shortcuts for each action in the Draw menu. */
    static ArrayList<KeyStroke> shortcuts;

    /**
     * <p>
     * Create a set of Draw menu actions, and their corresponding keyboard shortcuts.
     * </p>
     */
    public DrawActions() {
        actions = new ArrayList<Action>();
        actions.add(new DrawLineAction(Andie.getText("drawLineText"), null, Andie.getText("drawLineDescription"), null));
        actions.add(new DrawRectAction(Andie.getText("drawRectText"), null, Andie.getText("drawRectDescription"), null));
        actions.add(new DrawOvalAction(Andie.getText("drawOvalText"), null, Andie.getText("drawOvalDescription"), null));

        shortcuts = new ArrayList<KeyStroke>();
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
    }

    /**
     * <p>
     * Create a menu containing the list of Draw actions.
     * </p>
     * 
     * @return The draw menu UI element.
     */
    public JMenu createMenu() {
        JMenu drawMenu = new JMenu(Andie.getText("drawMenuText"));

        for (int i = 0; i < actions.size(); ++i) {
            JMenuItem drawMenuItem = new JMenuItem(actions.get(i));
            drawMenuItem.setAccelerator(shortcuts.get(i));
            drawMenu.add(drawMenuItem);    
        }

        return drawMenu;
    }

    /**
     * <p>
     * Action to draw a line over the current image.
     * </p>
     */
    public class DrawLineAction extends ImageAction {

        /**
         * <p>
         * Create a new DrawLineAction.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        DrawLineAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the DrawLineAction is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the DrawLineAction is triggered.
         * A line will be drawn between the two points selected by the user.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (!target.getImage().hasImage()) {
                JOptionPane.showMessageDialog(ImageAction.target, Andie.getText("noImageToEditWarningText"), Andie.getText("noImageLoadedWarningTitle"), JOptionPane.OK_OPTION);
                return;
            }
            else if ((int)target.getStartPoint().getX() == (int)target.getEndPoint().getX()
                && (int)target.getStartPoint().getY() == (int)target.getEndPoint().getY()) {
                
                JOptionPane.showMessageDialog(ImageAction.target, Andie.getText("noImageRegionToDrawText"), Andie.getText("noImageRegionToDrawTitle"), JOptionPane.OK_OPTION);
                return;
            }
            
            String colour = chooseColour();
            
            if (colour != null) {
                double scale = target.getZoom() / 100;
                int x1 = (int)(target.getStartPoint().getX() / scale);
                int y1 = (int)(target.getStartPoint().getY() / scale);
                int x2 = (int)(target.getEndPoint().getX() / scale);
                int y2 = (int)(target.getEndPoint().getY() / scale);

                int width = target.getImage().getCurrentImage().getWidth();
                int height = target.getImage().getCurrentImage().getHeight();
                
                if ((x1 >= width && x2 >= width) || (y1 >= height && y2 >= height)) {
                    JOptionPane.showMessageDialog(target, Andie.getText("noImageRegionToDrawText"), Andie.getText("noImageRegionToDrawTitle"), JOptionPane.WARNING_MESSAGE);
                    return;
                }

                target.getImage().apply(new DrawLineOperation(colour, x1, y1, x2, y2));
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }
     
    /**
     * <p>
     * Action to draw a square/rectangle in ANDIE.
     * </p>
     */   
    public class DrawRectAction extends ImageAction {

        /**
         * <p>
         * Create a new DrawRectAction.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        DrawRectAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
        
        /**
         * <p>
         * Callback for when the DrawRectAction is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the DrawRectAction is triggered.
         * A square/rectangle will be drawn over the selected region of the image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (!target.getImage().hasImage()) {
                JOptionPane.showMessageDialog(ImageAction.target, Andie.getText("noImageToEditWarningText"), Andie.getText("noImageLoadedWarningTitle"), JOptionPane.OK_OPTION);
                return;
            }
            else if ((int)target.getStartPoint().getX() == (int)target.getEndPoint().getX()
                && (int)target.getStartPoint().getY() == (int)target.getEndPoint().getY()) {
    
                JOptionPane.showMessageDialog(ImageAction.target, Andie.getText("noImageRegionToDrawText"), Andie.getText("noImageRegionToDrawTitle"), JOptionPane.OK_OPTION);
                return;
            }
            
            String colour = chooseColour();
            String fillType = chooseFillType();
            
            if (colour != null && fillType != null) {
                double scale = target.getZoom() / 100;
                int x1 = (int)(target.getStartPoint().getX() / scale);
                int y1 = (int)(target.getStartPoint().getY() / scale);
                int x2 = (int)(target.getEndPoint().getX() / scale);
                int y2 = (int)(target.getEndPoint().getY() / scale);

                int width = target.getImage().getCurrentImage().getWidth();
                int height = target.getImage().getCurrentImage().getHeight();
                
                if ((x1 >= width && x2 >= width) || (y1 >= height && y2 >= height)) {
                    JOptionPane.showMessageDialog(target, Andie.getText("noImageRegionToDrawText"), Andie.getText("noImageRegionToDrawTitle"), JOptionPane.WARNING_MESSAGE);
                    return;
                }

                target.getImage().apply(new DrawRectOperation(colour, fillType, x1, y1, x2, y2));
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }

    /**
     * <p>
     * Action to draw a circle/oval in ANDIE.
     * </p>
     */   
    public class DrawOvalAction extends ImageAction {

        /**
         * <p>
         * Create a new DrawOvalAction.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        DrawOvalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
        
        /**
         * <p>
         * Callback for when the DrawOvalAction is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the DrawOvalAction is triggered.
         * A circle/oval will be drawn over the selected region of the image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (!target.getImage().hasImage()) {
                JOptionPane.showMessageDialog(ImageAction.target, Andie.getText("noImageToEditWarningText"), Andie.getText("noImageLoadedWarningTitle"), JOptionPane.OK_OPTION);
                return;
            }
            else if ((int)target.getStartPoint().getX() == (int)target.getEndPoint().getX()
                && (int)target.getStartPoint().getY() == (int)target.getEndPoint().getY()) {
                
                JOptionPane.showMessageDialog(ImageAction.target, Andie.getText("noImageRegionToDrawText"), Andie.getText("noImageRegionToDrawTitle"), JOptionPane.OK_OPTION);
                return;
            }
            
            String colour = chooseColour();
            String fillType = chooseFillType();
            
            if (colour != null && fillType != null) {
                double scale = target.getZoom() / 100;
                int x1 = (int)(target.getStartPoint().getX() / scale);
                int y1 = (int)(target.getStartPoint().getY() / scale);
                int x2 = (int)(target.getEndPoint().getX() / scale);
                int y2 = (int)(target.getEndPoint().getY() / scale);

                int width = target.getImage().getCurrentImage().getWidth();
                int height = target.getImage().getCurrentImage().getHeight();
                
                if ((x1 >= width && x2 >= width) || (y1 >= height && y2 >= height)) {
                    JOptionPane.showMessageDialog(target, Andie.getText("noImageRegionToDrawText"), Andie.getText("noImageRegionToDrawTitle"), JOptionPane.WARNING_MESSAGE);
                    return;
                }

                target.getImage().apply(new DrawOvalOperation(colour, fillType, x1, y1, x2, y2));
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }

    /**
     * <p>
     * A helper method for choosing the colour of the shape/line to be drawn.
     * </p>
     * 
     * @return The colour of the shape/line to be drawn
     */
    private String chooseColour() {
        String[] colours = {
            Andie.getText("red"),
            Andie.getText("orange"),
            Andie.getText("yellow"),
            Andie.getText("green"),
            Andie.getText("blue"),
            Andie.getText("purple"),
            Andie.getText("black"),
            Andie.getText("white")
        };
        String colour = (String)JOptionPane.showInputDialog(ImageAction.target, Andie.getText("chooseColourPrompt"), Andie.getText("chooseColourTitle"), JOptionPane.PLAIN_MESSAGE, null, colours, colours[0]);
        return colour;
    }

    /**
     * <p>
     * A helper method for choosing the fill type of the shape to be drawn.
     * </p>
     * 
     * @return The fill type of the shape to be drawn
     */
    private String chooseFillType() {
        String[] fillTypes = {
            Andie.getText("solidFill"),
            Andie.getText("outlineFill")
        };
        String fillType = (String)JOptionPane.showInputDialog(ImageAction.target, Andie.getText("chooseFillTypePrompt"), Andie.getText("chooseFillTypeTitle"), JOptionPane.PLAIN_MESSAGE, null, fillTypes, fillTypes[0]);
        return fillType;
    }
}