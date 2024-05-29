package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Language menu.
 * </p>
 * 
 * <p>
 * The Language menu contains other languages supported by
 * the GUI that users can set as the language displayed by the GUI.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Evan Ting
 * @version 1.0
 */

public class LanguageActions {
    
    /** A list of actions for the Language menu. */
    static ArrayList<Action> actions;

    /** A list of shortcuts for the language menu */
    static ArrayList<KeyStroke> shortcuts;
    
    /** A list of supported locales, represented as strings. */
    private ArrayList<String> supportedLocales;

    /**
     * <p>
     * Create a set of Language menu actions, and their corresponding locales.
     * </p>
     */
    public LanguageActions() {
        actions = new ArrayList<Action>();
        actions.add(new SetToFrenchAction(Andie.getText("frenchDisplayText"), null, Andie.getText("frenchDisplayDescription"), null));
        actions.add(new SetToEnglishAction(Andie.getText("englishDisplayText"), null, Andie.getText("englishDisplayDescription"), null));

        shortcuts = new ArrayList<KeyStroke>();
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));

        supportedLocales = new ArrayList<String>();
        supportedLocales.add("frFR");
        supportedLocales.add("enNZ");
    }

    /**
     * <p>
     * Create a menu containing the list of Language actions, excluding the action 
     * that corresponds with the current locale.
     * </p>
     * 
     * @return The Language menu UI element.
     */
    public JMenu createMenu() {
        JMenu languageMenu = new JMenu(Andie.getText("languageMenuText"));
        String currentLanguage = Andie.prefs.get("language", null);
        String currentCountry = Andie.prefs.get("country", null);;

        for(int i = 0; i < actions.size(); ++i) {
            String locale = supportedLocales.get(i);
            if (!locale.equals(currentLanguage + currentCountry)) {    
                JMenuItem languageMenuItem = new JMenuItem(actions.get(i));
                languageMenuItem.setAccelerator(shortcuts.get(i));
                languageMenu.add(languageMenuItem);
            }
        }

        return languageMenu;
    }

    /**
     * <p>
     * Action to change the language displayed in the GUI to French,
     * with the language code 'fr' and the country code 'FR'.
     * </p>
     */
    public class SetToFrenchAction extends AbstractAction {
        
        /**
         * <p>
         * Create a new set-to-french action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        SetToFrenchAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon);

            if (desc != null) {
                putValue(SHORT_DESCRIPTION, desc);
            }

            if (mnemonic != null) {
                putValue(MNEMONIC_KEY, mnemonic);
            }
        }

        /**
         * <p>
         * Callback for when the set-to-french action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever SetToFrenchAction is triggered.
         * The action will then close the application (if desired by the user),
         * and will change the displayed language to French upon relaunching
         * the application.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            int option = JOptionPane.showConfirmDialog(ImageAction.target, Andie.getText("languageSettingConfirmation"), Andie.getText("languageConfirmationTitle"), JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {    
                Andie.prefs.put("language", "fr");
                Andie.prefs.put("country", "FR");
                System.exit(0);
            } 
        }
    }

    /**
     * <p>
     * Action to change the language displayed in the GUI to English,
     * with the language code 'en' and the country code 'NZ'.
     * </p>
     */
    public class SetToEnglishAction extends AbstractAction {
        
        /**
         * <p>
         * Create a new set-to-english action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        SetToEnglishAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon);

            if (desc != null) {
                putValue(SHORT_DESCRIPTION, desc);
            }
            
            if (mnemonic != null) {
                putValue(MNEMONIC_KEY, mnemonic);
            }
        }

        /**
         * <p>
         * Callback for when the set-to-english action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever SetToEnglishAction is triggered.
         * The action will then close the application (if desired by the user),
         * and will change the displayed language to English upon relaunching
         * the application.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            int option = JOptionPane.showConfirmDialog(ImageAction.target, Andie.getText("languageSettingConfirmation"), Andie.getText("languageConfirmationTitle"), JOptionPane.YES_NO_OPTION);
            
            if (option == JOptionPane.YES_OPTION) {    
                Andie.prefs.put("language", "en");
                Andie.prefs.put("country", "NZ");
                System.exit(0);
            } 
        }
    }
}