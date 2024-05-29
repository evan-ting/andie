package cosc202.andie; 

import java.awt.*;
import java.util.*;
import java.util.prefs.*;
import javax.imageio.*;
import javax.swing.*;


/**
 * <p>
 * Main class for A Non-Destructive Image Editor (ANDIE).
 * </p>
 * 
 * <p>
 * This class is the entry point for the program.
 * It creates a Graphical User Interface (GUI) that provides access to various image editing and processing operations.
 * </p>
 * 
 * <p> 
 * While English (from New Zealand) is the default language used by the application, 
 * users can also choose French (from France) as their preferred language in the GUI.
 * All French translations in the application are supplied by Google Translate, by
 * translating the initial english texts supported by the GUI.
 * All ISO country and language codes used in this application are retrieved from
 * <a href="https://docs.oracle.com/cd/E13214_01/wli/docs92/xref/xqisocodes.html">this webpage</a> from Oracle.
 * </p>
 * 
 * <p>
 * The code for setting up keyboard shortcuts were retrieved from <a href="https://docs.oracle.com/javase/tutorial/uiswing/components/menu.html">this tutorial webpage</a> from Oracle.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @author Evan Ting
 * @author Jennifer Puzey
 * @version 1.0
 */

public class Andie {
    
    /** An object used to store and change user preferences. */
    static Preferences prefs;
    
    /** An object used to get the current {@code Locale} object. */
    static ResourceBundle bundle;

    /** The Frame object containing the ANDIE application. */
    static JFrame frame;

    /** 
     * <p>
     * This is a wrapper method for retrieving the appropriate translation for the current 
     * locale set by the user.  
     * It saves a few keystrokes, instead of typing 'Andie.bundle.getString()' every time
     * in other files.
     * </p>
     * 
     * @param key The 'label' of the value to be retrieved.
     * @return The value linked to the key as a string, in the language of the locale set 
     * by the user.
     */
    static String getText(String key) {
        return bundle.getString(key);
    }

    /**
     * <p>
     * Launches the main GUI for the ANDIE program.
     * </p>
     * 
     * <p>
     * This method sets up an interface consisting of an active image (an {@code ImagePanel})
     * and various menus which can be used to trigger operations to load, save, edit, etc. 
     * These operations are implemented {@link ImageOperation}s and triggered via
     * {@code ImageAction}s grouped by their general purpose into menus.
     * </p>
     * 
     * @see ImagePanel
     * @see ImageAction
     * @see ImageOperation
     * @see FileActions
     * @see EditActions
     * @see ViewActions
     * @see FilterActions
     * @see ColourActions
     * @see TransformActions
     * @see LanguageActions
     * @see HelpActions
     * 
     * @throws Exception if something goes wrong.
     */
    private static void createAndShowGUI() throws Exception {
        // Set up the main GUI frame
        frame = new JFrame("ANDIE");

        Image image = ImageIO.read(Andie.class.getClassLoader().getResource("icon.png"));
        frame.setIconImage(image);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // The main content area is an ImagePanel
        ImagePanel imagePanel = new ImagePanel();
        ImageAction.setTarget(imagePanel);
        JScrollPane scrollPane = new JScrollPane(imagePanel);
        frame.add(scrollPane, BorderLayout.CENTER);
        
        // Add in menus for various types of action the user may perform.
        JMenuBar menuBar = new JMenuBar();

        // File menus are pretty standard, so things that usually go in File menus go here.
        FileActions fileActions = new FileActions();
        menuBar.add(fileActions.createMenu());

        // Likewise Edit menus are very common, so should be clear what might go here.
        EditActions editActions = new EditActions();
        menuBar.add(editActions.createMenu());

        // View actions control how the image is displayed, but do not alter its actual content
        ViewActions viewActions = new ViewActions();
        menuBar.add(viewActions.createMenu());

        // Filters apply a per-pixel operation to the image, generally based on a local window
        FilterActions filterActions = new FilterActions();
        menuBar.add(filterActions.createMenu());

        // Actions that affect the representation of colour in the image
        ColourActions colourActions = new ColourActions();
        menuBar.add(colourActions.createMenu());
        
        // Actions that alter the size and orientation of the image
        TransformActions transformActions = new TransformActions();
        menuBar.add(transformActions.createMenu());

        // Actions that allows basic shapes to be drawn in the GUI.
        DrawActions drawActions = new DrawActions();
        menuBar.add(drawActions.createMenu());

        // Actions to record macros and save them to use on other images
        MacroActions macroActions = new MacroActions();
        menuBar.add(macroActions.createMenu());

        // Actions that assist the user
        HelpActions helpActions = new HelpActions();
        menuBar.add(helpActions.createMenu());

        // Actions that change the language displayed in the GUI.
        LanguageActions languageActions = new LanguageActions();
        menuBar.add(languageActions.createMenu());

        // The Toolbar of common actions displayed in the GUI.
        Toolbar toolbar = new Toolbar(frame);
        frame.add(toolbar.getToolBar(), BorderLayout.NORTH);

        frame.setJMenuBar(menuBar);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * <p>
     * Main entry point to the ANDIE program.
     * </p>
     * 
     * <p>
     * Creates and launches the main GUI in a separate thread.
     * As a result, this is essentially a wrapper around {@code createAndShowGUI()}.
     * </p>
     * 
     * <p>
     * As the application supports more than one language within the GUI,
     * User language preferences are stored and language preference changes 
     * are applied upon relaunching the application.  
     * </p>
     * 
     * @param args Command line arguments, not currently used
     * @throws Exception If something goes awry
     * @see #createAndShowGUI()
     */
    public static void main(String[] args) throws Exception {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    prefs = Preferences.userNodeForPackage(Andie.class);
                    Locale.setDefault(new Locale(prefs.get("language", "en"), 
                                                 prefs.get("country", "NZ")));
                    bundle = ResourceBundle.getBundle("TextBundle");
                    createAndShowGUI();
                } 
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, Andie.getText("applicationStartupFailedText"), Andie.getText("applicationStartupFailedTitle"), JOptionPane.OK_OPTION);
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        });
    }
}