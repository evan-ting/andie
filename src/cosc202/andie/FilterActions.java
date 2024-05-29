package cosc202.andie;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Filter menu.
 * </p>
 *
 * <p>
 * The Filter menu contains actions that update each pixel in an image based on
 * some small local neighbourhood.
 * This includes a mean filter (a simple blur) in the sample code, but more operations will need to be added.
 * </p>
 *
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 *
 * @author Steven Mills
 * @author Layton Ford
 * @author Evan Ting
 * @author Charlotte Williams
 * @version 1.0
 */
public class FilterActions {
    
    /** A list of actions for the Filter menu. */
    static ArrayList<Action> actions;

    /** A list of keyboard shortcuts for all filters */
    static ArrayList<KeyStroke> shortcuts;

    /**
     * <p>
     * Create a set of Filter menu actions.
     * </p>
     */
    public FilterActions() {
        actions = new ArrayList<Action>();
        actions.add(new MeanFilterAction(Andie.getText("meanFilterText"), null, Andie.getText("meanFilterDescription"), null));
        actions.add(new MedianFilterAction(Andie.getText("medianFilterText"), null, Andie.getText("medianFilterDescription"), null));
        actions.add(new SharpenFilterAction(Andie.getText("sharpenFilterText"), null, Andie.getText("sharpenFilterDescription"), null));
        actions.add(new GaussianFilterAction(Andie.getText("gaussianFilterText"), null, Andie.getText("gaussianFilterDescription"), null));
        actions.add(new EmbossFilterAction(Andie.getText("embossFilterText"), null, Andie.getText("embossFilterDescription"), null));
        actions.add(new SobelFilterAction(Andie.getText("sobelFilterText"), null, Andie.getText("sobelFilterDescription"), null));
        actions.add(new BlockAveragingAction(Andie.getText("blockAveragingText"), null, Andie.getText("blockAveragingDescription"),null));
        actions.add(new RandomScatteringAction(Andie.getText("randomScatteringText"), null, Andie.getText("randomScatteringDescription"),null));

        shortcuts = new ArrayList<KeyStroke>();
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.CTRL_MASK));
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.CTRL_MASK));
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.CTRL_MASK));
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.CTRL_MASK));
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_5, ActionEvent.CTRL_MASK));
        shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_6, ActionEvent.CTRL_MASK));
    }

    /**
     * <p>
     * Create a menu containing the list of Filter actions and shortcuts.
     * </p>
     *
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu filterMenu = new JMenu(Andie.getText("filterMenuText"));

        for (int i = 0; i < actions.size(); ++i) {
            JMenuItem filterMenuItem = new JMenuItem(actions.get(i));
            filterMenuItem.setAccelerator(shortcuts.get(i));
            filterMenu.add(filterMenuItem);    
        }

        return filterMenu;
    }

    /**
     * <p>
     * Action to blur an image with a mean filter.
     * </p>
     *
     * @see MeanFilter
     */
    public class MeanFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new mean-filter action.
         * </p>
         *
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        MeanFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         *
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * It prompts the user for a filter radius, then applies an appropriately sized {@link MeanFilter}.
         * </p>
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;

            /**  
             * Pop-up dialog box to ask for the radius value (capped at 6).
             */
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 6, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            radiusSpinner.setEditor(new JSpinner.DefaultEditor(radiusSpinner));
            int option = JOptionPane.showOptionDialog(target, radiusSpinner, Andie.getText("filterRadiusPrompt"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } 
            else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();

                // Create and apply the filter
                target.getImage().apply(new MeanFilter(radius));
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }

    /**
     * <p>
     * Action to blur an image with a median filter.
     * </p>
     *
     * @see MedianFilter
     */
    public class MedianFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new median-filter action.
         * </p>
         *
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        MedianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the median-filter action is triggered.
         * </p>
         *
         * <p>
         * This method is called whenever the MedianFilterAction is triggered.
         * It prompts the user for a filter radius, then applies an appropriately sized {@link MedianFilter}.
         * </p>
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;

            /** 
             * Pop-up dialog box to ask for the radius value.
             * Capped at 6 as the application starts to get slow down/crash for higher values.
             */
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 6, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            radiusSpinner.setEditor(new JSpinner.DefaultEditor(radiusSpinner));
            int option = JOptionPane.showOptionDialog(target, radiusSpinner, Andie.getText("filterRadiusPrompt"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 1);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } 
            else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
                
                // Create and apply the filter.
                target.getImage().apply(new MedianFilter(radius));
                target.repaint();
                target.getParent().revalidate();
            }    
        }
    }

    /**
     * <p>
     * Action to sharpen an image with a sharpen filter.
     * </p>
     *
     * @see SharpenFilter
     */
    public class SharpenFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new sharpen-filter action.
         * </p>
         *
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        SharpenFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the sharpen-filter action is triggered.
         * </p>
         *
         * <p>
         * This method is called whenever the SharpenFilterAction is triggered.
         * It prompts the user for a filter radius, then applies an appropriately sized {@link SharpenFilter}.
         * </p>
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter.
            target.getImage().apply(new SharpenFilter());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to blur an image with a gaussian filter.
     * </p>
     *
     * @see GaussianFilter
     */
    public class GaussianFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new gaussian-filter action.
         * </p>
         *
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        GaussianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the gaussian-filter action is triggered.
         * </p>
         *
         * <p>
         * This method is called whenever the GaussianFilterAction is triggered.
         * It prompts the user for a filter radius, then applies an appropriately sized {@link GaussianFilter}.
         * </p>
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value (capped at 6).
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 6, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            radiusSpinner.setEditor(new JSpinner.DefaultEditor(radiusSpinner));
            int option = JOptionPane.showOptionDialog(target, radiusSpinner, Andie.getText("filterRadiusPrompt"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } 
            else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();

                // Create and apply the filter.
                target.getImage().apply(new GaussianFilter(radius));
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }

    public class BlockAveragingAction extends ImageAction {
        
        /**
         * <p>
         * Create a new block averaging action
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        BlockAveragingAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * Called whenever the block averaging action is triggered
         * 
         * @param e The event triggering this callback
         */
        public void actionPerformed(ActionEvent e) {
            String inputHeight = JOptionPane.showInputDialog(target, Andie.getText("blockAveragingHeightPrompt"));
            String inputWidth = JOptionPane.showInputDialog(target, Andie.getText("blockAveragingWidthPrompt"));
            int blockHeight =1;
            int blockWidth = 1;
            
            try {
                blockHeight = Integer.parseInt(inputHeight);
                blockWidth = Integer.parseInt(inputWidth);
            } 
            catch (NumberFormatException e1) {
                try {
                    if (!inputHeight.isEmpty() || !inputWidth.isEmpty()) {
                        JOptionPane.showMessageDialog(target, Andie.getText("averagingInputText"), Andie.getText("invalidInputTitle"), JOptionPane.OK_OPTION);
                        return;
                    }
                } 
                catch (NullPointerException e2) {
                    // Does nothing as user has cancelled operation or entered nothing
                }
            }

            try {
                if (blockHeight > 0 && blockHeight < 201 && blockWidth > 0 && blockWidth < 201) {
                    target.getImage().apply(new BlockAveraging(blockHeight, blockWidth));
                    target.repaint();
                    target.getParent().revalidate();
                }
                else {
                    JOptionPane.showMessageDialog(target, Andie.getText("averagingInputText"),
                    Andie.getText("invalidInputTitle"), JOptionPane.OK_OPTION);
                }
            } 
            catch (Exception e3) {
                JOptionPane.showMessageDialog(target, Andie.getText("averagingOperationFailedText"),
                    Andie.getText("averagingOperationFailedTitle"), JOptionPane.OK_OPTION);
            }
        }
    }

    /**
     * <p>
     * Action to make the image appear pressed into or raised out of the screen, using an emboss filter.
     * </p>
     */
    public class EmbossFilterAction extends ImageAction {
        
        /**
         * <p>
         * Create a new emboss-filter action.
         * </p>
         *
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        EmbossFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the emboss-filter action is triggered.
         * </p>
         *
         * <p>
         * This method is called whenever the EmbossFilterAction is triggered.
         * </p>
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            String[] options = {
                Andie.getText("northEmboss"),
                Andie.getText("northeastEmboss"),
                Andie.getText("eastEmboss"),
                Andie.getText("southeastEmboss"),
                Andie.getText("southEmboss"),
                Andie.getText("southwestEmboss"),
                Andie.getText("westEmboss"),
                Andie.getText("northwestEmboss")
            };
            String selectedOption = (String) JOptionPane.showInputDialog(target, Andie.getText("embossDirectionPrompt"), Andie.getText("embossDirectionTitle"),
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            if (selectedOption != null) {
                if (selectedOption.equals(options[0])) {
                    target.getImage().apply(new NorthEmbossFilter());
                }
                else if (selectedOption.equals(options[1])) {
                    target.getImage().apply(new NorthEastEmbossFilter());
                }
                else if (selectedOption.equals(options[2])) {
                    target.getImage().apply(new EastEmbossFilter());
                } 
                else if (selectedOption.equals(options[3])) {
                    target.getImage().apply(new SoutheastEmbossFilter());
                }
                else if (selectedOption.equals(options[4])) {
                    target.getImage().apply(new SouthEmbossFilter());
                }
                else if (selectedOption.equals(options[5])) {
                    target.getImage().apply(new SouthwestEmbossFilter());
                }
                else if (selectedOption.equals(options[6])) {
                    target.getImage().apply(new WestEmbossFilter());
                }
                else if (selectedOption.equals(options[7])) {
                    target.getImage().apply(new NorthwestEmbossFilter());
                }                    
                
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }

    /**
     * <p>
     * Action to make the image appear pressed into or raised out of the screen, using a Sobel filter.
     * </p>
     */
    public class SobelFilterAction extends ImageAction {
        
        /**
         * <p>
         * Create a new sobel-filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SobelFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
        
        /** 
         * <p>
         * Callback for when the sobel-filter action is triggered.
         * </p>
         *
         * <p>
         * This method is called whenever the SobelFilterAction is triggered.
         * </p>
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            String[] options = {
                Andie.getText("horizontalSobel"), 
                Andie.getText("verticalSobel")
            };
            String selectedOption = (String) JOptionPane.showInputDialog(target, Andie.getText("sobelDirectionPrompt"), Andie.getText("sobelDirectionTitle"),
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            if (selectedOption != null) {
                if (selectedOption.equals(options[0])) {
                    target.getImage().apply(new HorizontalSobelFilter());
                }
                else if (selectedOption.equals(options[1])) {
                    target.getImage().apply(new VerticalSobelFilter());
                }

                target.repaint();
                target.getParent().revalidate();
            }
        }
    }

    /**
     * Action to randomly scatter each individual pixel in a given radius
     */
    public class RandomScatteringAction extends ImageAction {

        /**
         * <p>
         * Create a new random scattering action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        RandomScatteringAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            int radius = 1;
            String input = JOptionPane.showInputDialog(target, Andie.getText("randomScatteringPrompt"));
            
            try {
                radius = Integer.parseInt(input);
            } 
            catch (NumberFormatException e1) {
                if (!input.isEmpty()) {
                    JOptionPane.showMessageDialog(target, Andie.getText("scatteringInputText"),
                    Andie.getText("invalidInputTitle"), JOptionPane.OK_OPTION);
                    return;
                }
            }
            
            try {
                if (radius > 0 && radius < 101) {
                    target.getImage().apply(new RandomScattering(radius));
                    target.repaint();
                    target.getParent().revalidate();
                }
                else {
                    JOptionPane.showMessageDialog(target, Andie.getText("scatteringInputText"),
                    Andie.getText("invalidInputTitle"), JOptionPane.OK_OPTION);
                    return;
                }
            } 
            catch (Exception e2) {
                JOptionPane.showMessageDialog(target, Andie.getText("scatteringOperationFailedText"), Andie.getText("scatteringOperationFailedTitle"),
                JOptionPane.OK_OPTION);
            }
        }
    }
}