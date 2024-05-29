package cosc202.andie;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;

/**
 * <p>
 * Actions provided by the File menu.
 * </p>
 * 
 * <p>
 * The File menu is very common across applications, 
 * and there are several items that the user will expect to find here.
 * Opening and saving files is an obvious one, but also exiting the program.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @author Evan Ting
 * @author Ari Zuo
 * @version 1.0
 */
public class FileActions {
    
    /** A list of actions for the File menu. */
    static ArrayList<Action> actions;

    /** 
     * <p>
     * A list of keyboard shortcuts for each action in the File menu.
     * </p>
     */
    static ArrayList<KeyStroke> shortcuts;

    /**
     * <p>
     * Create a set of File menu actions, and their corresponding keyboard shortcuts.
     * </p>
     */
    public FileActions() {
        actions = new ArrayList<Action>();
        actions.add(new FileOpenAction(Andie.getText("fileOpenText"), null, Andie.getText("fileOpenDescription"), Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new FileSaveAction(Andie.getText("fileSaveText"), null, Andie.getText("fileSaveDescription"), Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new FileSaveAsAction(Andie.getText("fileSaveAsText"), null, Andie.getText("fileSaveAsDescription"), Integer.valueOf(KeyEvent.VK_A)));
        actions.add(new FileExportAction(Andie.getText("fileExportText"), null, Andie.getText("fileExportDescription"), null));
        actions.add(new FileExitAction(Andie.getText("fileExitText"), null, Andie.getText("fileExitDescription"), Integer.valueOf(0)));

        shortcuts = new ArrayList<KeyStroke>();
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
    }

    /**
     * <p>
     * Create a menu containing the list of File actions, and set a keyboard shortcut for each action.
     * </p>
     * 
     * @return The File menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(Andie.getText("fileMenuText"));

        for (int i = 0; i < actions.size(); ++i) {
            JMenuItem fileMenuItem = new JMenuItem(actions.get(i));
            fileMenuItem.setAccelerator(shortcuts.get(i));
            fileMenu.add(fileMenuItem);    
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to open an image from file.
     * </p>
     * 
     * @see EditableImage#open(String)
     */
    public class FileOpenAction extends ImageAction {

        /**
         * <p>
         * Create a new file-open action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FileOpenAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-open action is triggered.
         * </p>
         *
         * <p>
         * This method is called whenever the FileOpenAction is triggered.
         * It prompts the user to select a file and opens it as an image.
         * If the file the user selects is not an image file, it shows an error
         * message.
         * </p>
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            int option = 0;

            if (target.getImage().hasImage()) {
                option = JOptionPane.showConfirmDialog(target, Andie.getText("unsavedChangesWarningText1"), Andie.getText("unsavedChangesWarningTitle"), JOptionPane.YES_NO_OPTION);
            }
            
            if (option == JOptionPane.YES_OPTION) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(target);

                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                        target.getImage().open(imageFilepath);
                        Andie.frame.setTitle("ANDIE");
                    } 
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(target, Andie.getText("notAnImageWarningText"), Andie.getText("notAnImageWarningTitle"), JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to save an image to its current file location.
     * </p>
     * 
     * @see EditableImage#save()
     */
    public class FileSaveAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FileSaveAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-save action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileSaveAction is triggered.
         * It saves the image to its original filepath.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                target.getImage().save();
                Andie.frame.setTitle("ANDIE");
            } 
            catch (Exception ex) {
                JOptionPane.showMessageDialog(target, Andie.getText("noImageToSaveWarningText"), Andie.getText("noImageLoadedWarningTitle"), JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * <p>
     * Action to save an image to a new file location.
     * </p>
     * 
     * @see EditableImage#saveAs(String)
     */
    public class FileSaveAsAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save-as action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FileSaveAsAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-save-as action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileSaveAsAction is triggered.
         * It prompts the user to select a file and saves the image to it.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().saveAs(imageFilepath);
                } 
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(target, Andie.getText("noImageToSaveWarningText"), Andie.getText("noImageLoadedWarningTitle"), JOptionPane.OK_OPTION);
                }
            }
        }
    }

    /**
     * <p>
     * Action to quit the ANDIE application.
     * </p>
     */
    public class FileExitAction extends AbstractAction {

        /**
         * <p>
         * Create a new file-exit action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FileExitAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-exit action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileExitAction is triggered.
         * It quits the program.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            int confirm = JOptionPane.showConfirmDialog(ImageAction.target, Andie.getText("unsavedChangesWarningText2"), Andie.getText("unsavedChangesWarningTitle"), JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    /**
     * <p>
     * Action to export the current image loaded into the application.
     * </p>
     */
    public class FileExportAction extends ImageAction {
        
        /**
         * <p>
         * Create a new file-export action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        public FileExportAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-export action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileExportAction is triggered.
         * It exports the current file into the folder selected by the user, and
         * is given a user-defined filename.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (!target.getImage().hasImage()) {
                JOptionPane.showMessageDialog(target, Andie.getText("noImagetoExportWarningText"), Andie.getText("noImageLoadedWarningTitle"), JOptionPane.OK_OPTION);
                return;
            }
            
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle(Andie.getText("fileExportText"));
            int result = fileChooser.showDialog(target, Andie.getText("fileExportText"));
            
            if (result == JFileChooser.APPROVE_OPTION) {
                String newFilename = fileChooser.getSelectedFile().getAbsolutePath(); 
                String currentFilename = target.getImage().getImageFilename();
                String extension = currentFilename.substring(1 + currentFilename.lastIndexOf(".")).toLowerCase();
                
                if (!newFilename.toLowerCase().endsWith("." + extension)) {            
                    newFilename += "." + extension;
                }

                try {
                    ImageIO.write(target.getImage().getCurrentImage(), extension, new File(newFilename));
                } 
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(target, Andie.getText("imageExportFailedDescription"), Andie.getText("imageExportFailedText"), JOptionPane.OK_OPTION);
                }
            }
        }
    }
}