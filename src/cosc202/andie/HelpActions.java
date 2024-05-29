package cosc202.andie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * <p>
 * Actions provided by the help menu.
 * </p>
 * @author Charlotte Williams
 * @version 1.0
 * */

public class HelpActions {

    /** A list of actions for the help menu. */
    static ArrayList<Action> actions;
    
    /** A list of shortcuts for the help menu. */
    static ArrayList<KeyStroke> shortcuts;

    /**
     * <p>
     * Create a set of help menu actions, and their corresponding keyboard shortcuts.
     * </p>
     */
    public HelpActions() {
        actions = new ArrayList<Action>();
        actions.add(new ShortcutsAction(Andie.getText("shortcutsText"), null, Andie.getText("shortcutsDescription"), null));

        shortcuts = new ArrayList<KeyStroke>();
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
    }

    /**
     * <p>
     * Create a menu containing the list of help actions, and set a keyboard shortcut for each action.
     * </p>
     * 
     * @return The edit menu UI element.
     */
    public JMenu createMenu() {
        JMenu helpMenu = new JMenu(Andie.getText("helpMenuText"));

        for (int i = 0; i < actions.size(); ++i) {
            JMenuItem helpMenuItem = new JMenuItem(actions.get(i));
            helpMenuItem.setAccelerator(shortcuts.get(i));
            helpMenu.add(helpMenuItem);    
        }

        return helpMenu;
    }

    /**
     * Action to view all the shortcuts available in the ANDIE program
     */
    public class ShortcutsAction extends ImageAction {

        /**
         * <p>
         * Create a new shortcuts action.
         * </p>
         *
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        ShortcutsAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the ShortcutsAction is triggered
         * Shows a message dialog with all available shortcuts the user can use in ANDIE
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            String[] message = {
                Andie.getText("fileOpenDescription") + ": CTRL+O",
                Andie.getText("fileSaveDescription") + ": CTRL+S",
                Andie.getText("fileSaveAsDescription") + ": CTRL+SHIFT+S",
                Andie.getText("fileExportDescription") + ": CTRL+E",
                Andie.getText("fileExitDescription") + ": CTRL+F4",
                Andie.getText("undoText") + ": CTRL+Z",
                Andie.getText("redoText") + ": CTRL+Y",
                Andie.getText("zoomInText") + ": CTRL+=",
                Andie.getText("zoomOutText")+ ": CTRL+-",
                Andie.getText("zoomFullText") + ": CTRL+F",
                Andie.getText("meanFilterDescription") + ": CTRL+M",
                Andie.getText("medianFilterDescription") + ": CTRL+N",
                Andie.getText("sharpenFilterDescription") + ": CTRL+1",
                Andie.getText("gaussianFilterDescription") + ": CTRL+2",
                Andie.getText("embossFilterDescription") + ": CTRL+3",
                Andie.getText("sobelFilterDescription") + ": CTRL+4",
                Andie.getText("blockAveragingText") + ": CTRL+5",
                Andie.getText("randomScatteringText") + ": CTRL+6",
                Andie.getText("greyscaleDescription") + ": CTRL+G",
                Andie.getText("colourInversionDescription") + ": CTRL+I",
                Andie.getText("channelCycleDescription") + ": CTRL+C",
                Andie.getText("contrastBrightnessDescription") + ": CTRL+B",
                Andie.getText("horizontalFlipDescription") + ": CTRL+H",
                Andie.getText("verticalFlipDescription") + ": CTRL+V",
                Andie.getText("leftRotationDescription") + ": CTRL+SHIFT+" + Andie.getText("leftArrow"),
                Andie.getText("rightRotationDescription") + ": CTRL+SHIFT+" + Andie.getText("rightArrow"),
                Andie.getText("180RotationDescription") + ": CTRL+8",
                Andie.getText("imageResizeDescription") + ": CTRL+R",
                Andie.getText("imageCropDescription") + ": CTRL+X",
                Andie.getText("changeLanguage") + ": CTRL+L",
                Andie.getText("drawLineDescription") + ": CTRL+SHIFT+L",
                Andie.getText("drawRectDescription") + ": CTRL+SHIFT+R",
                Andie.getText("drawOvalDescription") + ": CTRL+SHIFT+V",
                Andie.getText("startStopMacro") + ": CTRL+SHIFT+Q",
                Andie.getText("importMacro") + ": CTRL+SHIFT+W",
                Andie.getText("shortcutsDescription") + ": CTRL+P"
            };

            JList<String> shortcuts = new JList<String>(message);
            JScrollPane scrollPane = new JScrollPane(shortcuts);
            scrollPane.setPreferredSize(new Dimension(400, 400));
            JOptionPane.showMessageDialog(ImageAction.target, scrollPane, Andie.getText("shortcutsText"), JOptionPane.INFORMATION_MESSAGE);
        }
    }
}