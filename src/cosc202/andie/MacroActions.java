package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;

/**
 * <p>
 * Actions provided by the macro menu.
 * </p>
 * 
 * <p>
 * The macro menu contains actions to start and stop recording a macro, open a macro file to apply on the current image, and save a macro file
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Jennifer Puzey
 * @version 1.0
 */
public class MacroActions {

    /** A list of actions for the macro menu. */
    static ArrayList<Action> actions;

    /** A list of keyboard shortcuts for each action in the Macro menu. */
    static ArrayList<KeyStroke> shortcuts;
    
    /**
     * <p>
     * Create a set of macro menu actions, and their corresponding keyboard
     * shortcuts.
     * </p>
     */
    public MacroActions() {
        actions = new ArrayList<Action>();
        actions.add(new ToggleRecordAction(Andie.getText("recordText"), null, Andie.getText("recordDescription"),
                Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new MacroOpenAction(Andie.getText("macroOpenText"), null, Andie.getText("macroOpenDescription"),
                Integer.valueOf(KeyEvent.VK_A)));

        shortcuts = new ArrayList<KeyStroke>();
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
    }

    /**
     * <p>
     * Create a menu containing the list of File actions, and set a keyboard
     * shortcut for each action.
     * </p>
     * 
     * @return The File menu UI element.
     */
    public JMenu createMenu() {
        JMenu macroMenu = new JMenu(Andie.getText("macroMenuText"));

        for (int i = 0; i < actions.size(); ++i) {
            JMenuItem macroMenuItem = new JMenuItem(actions.get(i));
            macroMenuItem.setAccelerator(shortcuts.get(i));
            macroMenu.add(macroMenuItem);
        }

        return macroMenu;
    }

    /**
     * <p>
     * Action to save a set of macros
     * </p>
     * 
     * @see EditableImage#saveAs(String)
     */
    public class MacroSaveAction extends ImageAction {

        /**
         * <p>
         * Create a new macro save action
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MacroSaveAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         *  Callback for when the MacroSave action is triggered
         * </p>
         * 
         * <p>
         *  This method is called whenever the MacroSaveAction is triggered
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String filepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().saveAs(filepath);
                } 
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(target, Andie.getText("noImageToSaveWarningText"),
                            Andie.getText("noImageLoadedWarningTitle"), JOptionPane.OK_OPTION);
                }
            }
        }
    }

    /**
     * <p>
     * Action to begin recording a set of macro operations.
     * </p>
     * 
     * @see EditableImage#saveAs(String)
     */
    public class RecordAction extends ImageAction {

        /**
         * <p>
         * Create a new record action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        RecordAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the record action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RecordAction is triggered.
         * 
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (!target.getImage().hasImage()) {
                JOptionPane.showMessageDialog(target, Andie.getText("noImageRecord"), Andie.getText("noImageRecordTitle"),
                        JOptionPane.ERROR_MESSAGE);
                return;
            } 
            else {
                EditableImage.startRecording();
            }
        }
    }

    /**
     * <p>
     * Action to stop recording a macro.
     * </p>
     * 
     * @see EditableImage#saveAs(String)
     */
    public class StopRecordAction extends ImageAction {

        /**
         * <p>
         * Create a new stop recording action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        StopRecordAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the stop recording action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the StopRecordAction is triggered.
         * 
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            EditableImage.stopRecording();
        }
    }

    /**
     * <p>
     * Action to open a macro and apply it to a new image.
     * </p>
     * 
     * @see EditableImage#saveAs(String)
     */
    public class MacroOpenAction extends ImageAction {

        /**
         * <p>
         * Create a new open macro action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MacroOpenAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the macro-open action is triggered.
         * </p>
         *
         * <p>
         * This method is called whenever the macroOpen action is triggered.
         * </p>
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (!target.getImage().hasImage()) {
                JOptionPane.showMessageDialog(target, Andie.getText("noImageMacro"), Andie.getText("noImageMacroTitle"),
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Macro Files (*.ops)", "ops");
                fileChooser.addChoosableFileFilter(filter);
                int returnVal = fileChooser.showOpenDialog(target);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String macroFilePath = selectedFile.getAbsolutePath();

                    if (macroFilePath.toLowerCase().endsWith(".ops")) {
                        try {
                            target.getImage().openMacro(macroFilePath);
                            Andie.frame.setTitle("ANDIE*");
                            target.repaint();
                            target.getParent().revalidate();
                        } 
                        catch (Exception ex) {
                            JOptionPane.showMessageDialog(target, Andie.getText("macroImportFailedText"),
                                    Andie.getText("macroImportFailedTitle"), JOptionPane.ERROR_MESSAGE);
                        }
                    } 
                    else {
                        JOptionPane.showMessageDialog(target, Andie.getText("notMacroFileWarning"),
                                Andie.getText("notMacroFileWarningTitle"), JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

   /**
    * <p>
    * Callback for when the macro-toggle action is triggered.
    * </p>
    *
    * <p>
    * This method is called whenever the macroToggle action is triggered.
    * </p>
    *
    */    
    private class ToggleRecordAction extends ImageAction{

        /** Whether the user is currently recording */
        private boolean isRecording;

        /**
         * <p>
         * Create a new toggle record action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ToggleRecordAction(String name, ImageIcon icon, String desc, Integer mnemonic){
            super(name, icon, desc, mnemonic);
            this.isRecording = false;  
        }

        /**
         * <p>
         * Callback for when the toggle action is triggered.
         * </p>
         *
         * <p>
         * This method is called whenever the toggle action is triggered.
         * </p>
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e){
            if (isRecording){
                EditableImage.stopRecording();
                putValue(Action.NAME, Andie.getText("recordText"));
                putValue(Action.SHORT_DESCRIPTION, Andie.getText("recordDescription"));
                isRecording = false;

                new MacroSaveAction(Andie.getText("macroSaveText"), null, Andie.getText("macroSaveDescription"), null).actionPerformed(e);
            } 
            else {
                if (!target.getImage().hasImage()) {
                    JOptionPane.showMessageDialog(target, Andie.getText("noImageRecord"), Andie.getText("noImageRecordTitle"), JOptionPane.ERROR_MESSAGE);
                    return;
                } 
                else {
                    EditableImage.startRecording();
                    putValue(Action.NAME, Andie.getText("stopRecordText"));
                    putValue(Action.SHORT_DESCRIPTION, Andie.getText("stopRecordDescription"));
                    isRecording = true;
                }
            }
        }
    }
}