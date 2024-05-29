package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Colour menu.
 * </p>
 * 
 * <p>
 * The Colour menu contains actions that affect the colour of each pixel directly 
 * without reference to the rest of the image.
 * This includes conversion to greyscale in the sample code, but more operations will need to be added.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @author Charlotte Williams
 * @author Evan Ting
 * @author Jennifer Puzey
 * @version 1.0
 */
public class ColourActions {
    
    /** A list of actions for the Colour menu. */
    static ArrayList<Action> actions;

    /** 
     * <p>
     * A list of keyboard shortcuts for all colour operations.
     * </p>
     * 
     * <p>
     * The code used for setting up the keyboard shortcuts can be found at the
     * tutorial webpage mentioned in the application class.
     * </p>
     */
    static ArrayList<KeyStroke> shortcuts;

    /**
     * <p>
     * Create a set of Colour menu actions.
     * </p>
     */
    public ColourActions() {
        actions = new ArrayList<Action>();
        actions.add(new ConvertToGreyAction(Andie.getText("greyscaleText"), null, Andie.getText("greyscaleDescription"), null));
        actions.add(new InvertColourAction(Andie.getText("colourInversionText"), null, Andie.getText("colourInversionDescription"), null));
        actions.add(new CycleColourChannelAction(Andie.getText("channelCycleText"), null, Andie.getText("channelCycleDescription"), null));
        actions.add(new BrightnessContrastAction(Andie.getText("contrastBrightnessText"), null, Andie.getText("contrastBrightnessDescription"), null ));   
        
        shortcuts = new ArrayList<KeyStroke>();
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
    }

    /**
     * <p>
     * Create a menu containing the list of Colour actions.
     * </p>
     * 
     * @return The colour menu UI element.
     */
    public JMenu createMenu() {
        JMenu colourMenu = new JMenu(Andie.getText("colourMenuText"));

        for (int i = 0; i < actions.size(); ++i) {
            JMenuItem colourMenuItem = new JMenuItem(actions.get(i));
            colourMenuItem.setAccelerator(shortcuts.get(i));
            colourMenu.add(colourMenuItem);    
        }

        return colourMenu;
    }

    /**
     * <p>
     * Action to convert an image to greyscale.
     * </p>
     * 
     * @see ConvertToGrey
     */
    public class ConvertToGreyAction extends ImageAction {

        /**
         * <p>
         * Create a new convert-to-grey action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        ConvertToGreyAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ConvertToGreyAction is triggered.
         * It changes the image to greyscale.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ConvertToGrey());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * Action to invert the colours of an image.
     */
    public class InvertColourAction extends ImageAction {
        
        /**
         * <p>
         * Create a new invert-colour action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        InvertColourAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the invert-colour action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the InvertColourAction is triggered.
         * It inverts the colours of an image
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new InvertColour());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * Action to cycle colour channel of an image.
     */
    public class CycleColourChannelAction extends ImageAction {
        
        /**
         * <p>
         * Create a new cycle-colour-channel action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        CycleColourChannelAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the cycle-colour-channel action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the CycleColourChannelAction is triggered.
         * It prompt the user to answer what colours channels they want to cycle
         * Then cycles the colour channel of the image
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            String[] options = {"GBR", "BRG", "RBG", "GRB", "BGR"};
            String selection = (String)JOptionPane.showInputDialog(target, Andie.getText("channelCyclePrompt"), Andie.getText("channelCyclePromptTitle"), JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            if (selection != null) {
                target.getImage().apply(new CycleColourChannel(selection));
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }

    /**
     * <p>
     * Action to adjust the brightness of an image.
     * </p>
     * 
     * @see BrightnessContrastAdjust
     */
    public class BrightnessContrastAction extends ImageAction {

        /**
         * <p>
         * Create a new brightness action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        BrightnessContrastAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the brightness action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the brightness action is triggered.
         * It changes the brightness of the image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (!target.getImage().hasImage()) {
                JOptionPane.showMessageDialog(target, Andie.getText("noImageToEditWarningText"), Andie.getText("noImageLoadedWarningTitle"), JOptionPane.OK_OPTION);
                return;
            }

            try {
                String input = JOptionPane.showInputDialog(target, Andie.getText("contrastPrompt"));
                String input2 = JOptionPane.showInputDialog(target, Andie.getText("brightnessPrompt"));
                
                if (input != null && input2 != null) {
                    int contrast = Integer.parseInt(input);
                    int brightness = Integer.parseInt(input2);

                    if (contrast < -100 || contrast > 100
                        || brightness < -100 || brightness > 100) {
                        JOptionPane.showMessageDialog(target, Andie.getText("contrastBrightnessErrorText"), Andie.getText("contrastBrightnessErrorTitle"), JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    target.getImage().apply(new BrightnessContrastAdjust(contrast, brightness));
                    target.repaint();
                    target.getParent().revalidate();
                }
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(target, Andie.getText("contrastBrightnessErrorText"), Andie.getText("contrastBrightnessErrorTitle"), JOptionPane.ERROR_MESSAGE);
            }
        }
    }  
}