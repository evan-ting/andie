package cosc202.andie;

import java.awt.*;
import javax.swing.*;

/**
 * <p>
 * The toolbar that contains common actions.
 * </p>
 *
 * <p>
 * A toolbar that contains common actions so that they are easier to access
 * and see with helpful image icons.
 * </p>
 *
 * <p>
 * All of the icons were retrieved from <a href="https://uxwing.com">this website</a>
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 *
 * @author Layton Ford
 * @author Evan Ting
 * @version 1.0
 */
public class Toolbar {

    /** The toolbar to be displayed below the menu bar. */
    private JToolBar toolbar;

    /** The width of each icon shown in the toolbar. */
    private final int ICON_WIDTH = 20;

    /** The height of each icon shown in the toolbar. */
    private final int ICON_HEIGHT = 20;

    /**
     * <p>
     * A method that retrieves the toolbar itself
     * </p>
     *
     * @return The toolbar
     */
    public JToolBar getToolBar() {
        return toolbar;
    }
    
    /**
     * <p>
     * The toolbar that contains common actions.
     * </p>
     */
    public Toolbar(JFrame frame){
        toolbar = new JToolBar(JToolBar.HORIZONTAL);
        toolbar.setFloatable(false);

        ImageIcon[] icons = {
            new ImageIcon(Andie.class.getClassLoader().getResource("open_icon.png")),
            new ImageIcon(Andie.class.getClassLoader().getResource("save_icon.png")),
            new ImageIcon(Andie.class.getClassLoader().getResource("save_as_icon.png")),
            new ImageIcon(Andie.class.getClassLoader().getResource("undo_icon.png")),
            new ImageIcon(Andie.class.getClassLoader().getResource("redo_icon.png")),
            new ImageIcon(Andie.class.getClassLoader().getResource("zoom_in_icon.png")),
            new ImageIcon(Andie.class.getClassLoader().getResource("zoom_out_icon.png"))
        };

        String[] toolTipText = {
            Andie.getText("fileOpenDescription"),
            Andie.getText("fileSaveDescription"),
            Andie.getText("fileSaveAsDescription"),
            Andie.getText("undoText"),
            Andie.getText("redoText"),
            Andie.getText("zoomInText"),
            Andie.getText("zoomOutText")
        };

        Action[] actions = {
            FileActions.actions.get(0),
            FileActions.actions.get(1),
            FileActions.actions.get(2),
            EditActions.actions.get(0),
            EditActions.actions.get(1),
            ViewActions.actions.get(0),
            ViewActions.actions.get(1)
        };

        for (int i = 0; i < actions.length; ++i) {
            icons[i] = new ImageIcon(icons[i].getImage().getScaledInstance(ICON_WIDTH,ICON_HEIGHT, Image.SCALE_SMOOTH));
            JButton button = toolbar.add(actions[i]);
            button.setHideActionText(true);
            button.setToolTipText(toolTipText[i]);
            button.setIcon(icons[i]);
        }            
    }
}