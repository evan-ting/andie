package cosc202.andie;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

 /**
  * <p>
  * Actions provided by the Edit menu.
  * </p>
  * 
  * <p>
  * The Edit menu is very common across a wide range of applications.
  * There are a lot of operations that a user might expect to see here.
  * In the sample code there are Undo and Redo actions, but more may need to be added.
  * </p>
  * 
  * <p> 
  * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
  * </p>
  * 
  * @author Steven Mills
  * @author Evan Ting
  * @version 1.0
  */
public class EditActions {
    
    /** A list of actions for the Edit menu. */
    static ArrayList<Action> actions;

    /** 
     * <p>
     * A list of keyboard shortcuts for each action in the Edit menu.
     * </p>
     */
    static ArrayList<KeyStroke> shortcuts;

    /**
     * <p>
     * Create a set of Edit menu actions, and their corresponding keyboard shortcuts.
     * </p>
     */
    public EditActions() {
        actions = new ArrayList<Action>();
        actions.add(new UndoAction(Andie.getText("undoText"), null, Andie.getText("undoText"), Integer.valueOf(KeyEvent.VK_Z)));
        actions.add(new RedoAction(Andie.getText("redoText"), null, Andie.getText("redoText"), Integer.valueOf(KeyEvent.VK_Y)));

        shortcuts = new ArrayList<KeyStroke>();
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
    }

    /**
     * <p>
     * Create a menu containing the list of Edit actions, and set a keyboard shortcut for each action.
     * </p>
     * 
     * @return The edit menu UI element.
     */
    public JMenu createMenu() {
        JMenu editMenu = new JMenu(Andie.getText("editMenuText"));

        for (int i = 0; i < actions.size(); ++i) {
            JMenuItem editMenuItem = new JMenuItem(actions.get(i));
            editMenuItem.setAccelerator(shortcuts.get(i));
            editMenu.add(editMenuItem);    
        }

        return editMenu;
    }

    /**
     * <p>
     * Action to undo an {@link ImageOperation}.
     * </p>
     * 
     * @see EditableImage#undo()
     */
    public class UndoAction extends ImageAction {

        /**
         * <p>
         * Create a new undo action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        UndoAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the undo action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the UndoAction is triggered.
         * It undoes the most recently applied operation.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                target.getImage().undo();
                target.repaint();
                target.getParent().revalidate();
                Andie.frame.setTitle("ANDIE*");
            } 
            catch (EmptyStackException ex) {
                JOptionPane.showMessageDialog(target,  Andie.getText("noChangesToUndoWarningText"), Andie.getText("noChangesToUndoWarningTitle"), JOptionPane.ERROR_MESSAGE);
            }
        }
    }

     /**
     * <p>
     * Action to redo an {@link ImageOperation}.
     * </p>
     * 
     * @see EditableImage#redo()
     */   
    public class RedoAction extends ImageAction {

        /**
         * <p>
         * Create a new redo action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        RedoAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the redo action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RedoAction is triggered.
         * It redoes the most recently undone operation.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                target.getImage().redo();
                target.repaint();
                target.getParent().revalidate();
                Andie.frame.setTitle("ANDIE*");
            } 
            catch(EmptyStackException ex) {
                JOptionPane.showMessageDialog(target,  Andie.getText("noChangesToRedoWarningText"), Andie.getText("noChangesToRedoWarningTitle"), JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}