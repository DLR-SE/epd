package de.emir.runtime.plugin.windows;

import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.common.CWorkingArea;
import bibliothek.gui.dock.common.intern.CDockable;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.views.AbstractViewFactory;
import de.emir.runtime.plugin.windows.layout.BorderModifierDockableFrames;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;

/**
 * Main window UI component. Handles the management of Dockables and main layout of the application.
 */
public class MainWindow extends JFrame {

    private static final Logger log = LogManager.getLogger(MainWindow.class);

    public static interface ICloseListener {
        void aboutToClose();

        void closed();
    }

    public static final String LAYOUT_FILE = "application-layout.xml";

    private static final long serialVersionUID = -5182981258360273853L;

    private static final int BASIC_WIDTH = 800;
    private static final int BASIC_HEIGHT = 600;

    private CControl mainControl;

    private LayoutLockState layoutLockState = new LayoutLockState();

    private CWorkingArea workingArea;

    private boolean hasEditorArea;

    private ICloseListener mCloseListener = null;

    private JPanel statusBar;

    /**
     * Creates a new MainWindow.
     * @param mainFrameTitle Title of the window.
     * @param hasWorkingArea Sets the working area flag. This signals to DockingFrames that a working area is available.
     * @param hasStatusBar Sets the status bar flag. Appends a status bar if set to true.
     */
    public MainWindow(String mainFrameTitle, boolean hasWorkingArea, boolean hasStatusBar) {
        this.hasEditorArea = hasWorkingArea;

        setTitle(mainFrameTitle);

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        setBounds();

        mainControl = new CControl(this);
        mainControl.setIgnoreWorkingForEntry(false);

        BorderModifierDockableFrames.main(mainControl);
        getContentPane().add(mainControl.getContentArea());

        if (hasStatusBar) {
            createStatusBar();
        }

        setBasicLayout();
    }

    /**
     * Creates and appends a status bar to the window.
     */
    private void createStatusBar() {

        JPanel panelUp = new JPanel();

        MatteBorder mainBorder = new MatteBorder(0, 0, 2, 0, Color.red) {

            private static final long serialVersionUID = 9034343104646594880L;

            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

                g.setColor(UIManager.getColor("InternalFrame.borderColor"));
                g.drawLine(0, 0, width, 0);
                g.setColor(UIManager.getColor("InternalFrame.borderShadow"));
                g.drawLine(0, 1, width, 1);
            }

        };

        getContentPane().add(panelUp, BorderLayout.SOUTH);
        GridBagLayout gbl_panelUp = new GridBagLayout();
        gbl_panelUp.columnWidths = new int[] { 0, 0 };
        gbl_panelUp.rowHeights = new int[] { 0, 0 };
        gbl_panelUp.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gbl_panelUp.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
        panelUp.setLayout(gbl_panelUp);

        JPanel panel = new JPanel();
        panel.setBorder(mainBorder);

        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(2, 0, 0, 0);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;
        panelUp.add(panel, gbc_panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 0, 0 };
        gbl_panel.rowHeights = new int[] { 0, 0 };
        gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
        panel.setLayout(gbl_panel);

        statusBar = new JPanel();

        GridBagConstraints gbc_statusBar = new GridBagConstraints();
        gbc_statusBar.insets = new Insets(3, 5, 0, 5);
        gbc_statusBar.fill = GridBagConstraints.BOTH;
        gbc_statusBar.gridx = 0;
        gbc_statusBar.gridy = 0;
        panel.add(statusBar, gbc_statusBar);
        FlowLayout fl_statusBar = new FlowLayout(FlowLayout.RIGHT, 3, 0);
        statusBar.setLayout(fl_statusBar);

    }

    /**
     * Gets the status bar component.
     * @return Status bar component.
     */
    public JPanel getStatusBar() {
        return statusBar;
    }

    /**
     * Gets the EditorArea. This is an unmodifiable area of the docking frames layout.
     * @return EditorArea component.
     */
    public CWorkingArea getWorkingArea() {
        return workingArea;
    }

    /**
     * Gets the layout lock state. This signals if the layout is movable and editable.
     * @return Current lock state of the layout.
     */
    public LayoutLockState getLayoutLockState() {
        return layoutLockState;
    }

    /**
     * Sets the close listener of the main window.
     * @param cl Close listener to set for the main window.
     */
    public void setCloseListener(ICloseListener cl) {
        mCloseListener = cl;
    }

    /**
     * Sets the basic layout of the view at initial startup.
     */
    private void setBasicLayout() {
        mainControl.getController().getIcons().setIconClient("locationmanager.maximize",
                IconManager.getIcon(this, "icons/emiricons/windowcontrols/16/fullscreen.png", IconManager.preferedSmallIconSize()));
        mainControl.getController().getIcons().setIconClient("locationmanager.minimize",
                IconManager.getIcon(this, "icons/emiricons/windowcontrols/16/minimize.png", IconManager.preferedSmallIconSize()));
        mainControl.getController().getIcons().setIconClient("locationmanager.normalize",
                IconManager.getIcon(this, "icons/emiricons/windowcontrols/16/fullscreen_exit.png", IconManager.preferedSmallIconSize()));
        mainControl.getController().getIcons().setIconClient("locationmanager.externalize",
                IconManager.getIcon(this, "icons/emiricons/windowcontrols/16/launch.png", IconManager.preferedSmallIconSize()));
        mainControl.getController().getIcons().setIconClient("locationmanager.unexternalize",
                IconManager.getIcon(this, "icons/emiricons/windowcontrols/16/dashboard.png", IconManager.preferedSmallIconSize()));
        mainControl.getController().getIcons().setIconClient("locationmanager.unmaximize_externalized",
                IconManager.getIcon(this, "icons/emiricons/windowcontrols/16/exit_to_app.png", IconManager.preferedSmallIconSize()));
        
        mainControl.getController().getIcons().setIconClient("flap.hold",
                IconManager.getIcon(this, "icons/emiricons/windowcontrols/16/pin_invoke.png", IconManager.preferedSmallIconSize()));
        mainControl.getController().getIcons().setIconClient("flap.free",
                IconManager.getIcon(this, "icons/emiricons/windowcontrols/16/pin_end.png", IconManager.preferedSmallIconSize()));

        mainControl.getController().getIcons().setIconClient("close",
                IconManager.getIcon(this, "icons/emiricons/windowcontrols/16/close.png", IconManager.preferedSmallIconSize()));

        mainControl.getController().getRelocator()
                .addVetoableDockRelocatorListener(new LockableRelocatorListener(this));

        LayoutLockResizeObserver lockStateObserver = new LayoutLockResizeObserver(this);

        if (hasEditorArea == true) {

            workingArea = mainControl.createWorkingArea("EditorArea");
            workingArea.setVisible(true);
            workingArea.getComponent().setBorder(null);
        }

        lockStateObserver.setState();

    }

    /**
     * Calculates the current viewbounds from the current screen size.
     */
    private void setBounds() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        setBounds((int) (width / 2 - BASIC_WIDTH / 2), (int) (height / 2 - BASIC_HEIGHT / 2), BASIC_WIDTH,
                BASIC_HEIGHT);

    }

    /**
     * Opens the main window.
     */
    public void openMainFrame() {
        setVisible(true);
    }

    /**
     * Gets the main CControl element. This is used for managing the layout of CDockables (i.e. AbstractViews).
     * @return Main CControl instance.
     */
    public CControl getMainControl() {
        return mainControl;
    }

    /**
     * Saves the layout to an xml file called application-layout.xml.
     */
    public void saveLayout() {
        File file = ResourceManager.get(getClass()).resolveFile(LAYOUT_FILE);
        if (file == null) {
            // this would be the first startup and the file does not exists yet, so we will
            // create it by default in the home directory of the application
            file = ResourceManager.get(getClass()).getHomePath().resolve(LAYOUT_FILE).toFile();
        }

        try {
            saveLayout(file);
        } catch (IOException e) {
            log.error("Error while saving layout file: {}.", e.getMessage());
        }
    }

    /**
     * Saves the current layout to a custom file.
     * @param file Custom file to store layout in.
     * @throws IOException
     */
    public void saveLayout(File file) throws IOException {
        mainControl.writeXML(file);
    }

    public void loadLayout() {
        try {
            File file = ResourceManager.get(getClass()).resolveFile(LAYOUT_FILE); // note: this will first look in the
                                                                                  // applications root and later im
            // the applications home directory
            if (file != null) {
                loadLayout(file);
            } else {
                File oldFile = ResourceManager.get(getClass()).resolveFile("layout.xml");
                if (oldFile != null) {
                    log.error("Did not find layout file but detected old non-compatible layout.xml configuration. The layout needs to be reconfigured.");
                }
            }
        } catch (IOException e) {
            File oldFile = ResourceManager.get(getClass()).resolveFile("layout.xml");
            if (oldFile != null) {
                log.error("Did not find layout file but detected old non-compatible layout.xml configuration. The layout needs to be reconfigured.");
            } else {
                log.error("Error while accessing layout configuration: {}.", e.getMessage());
            }
        }
    }

    public void loadLayout(File file) throws IOException {
        PlatformUtil.getViewManager().setLoadComplete(false);
        mainControl.readXML(file);
        PlatformUtil.getViewManager().makeViewsVisible();
        PlatformUtil.getViewManager().setLoadComplete(true);
    }

    public boolean hasEditorArea() {
        return hasEditorArea;
    }

    public void showError(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public void notifyAboutToClose() {
        if (mCloseListener != null) {
            try {
                mCloseListener.aboutToClose();
            } catch (Exception e) {
                ULog.error("Failed to notify about to close");
            }
        }
    }

    public void notifyClosed() {
        if (mCloseListener != null) {
            try {
                mCloseListener.closed();
            } catch (Exception e) {
                ULog.error("Failed to notify about to close");
            }
        }
    }

    // TODO: Move to external utils class
    @Deprecated
    public BufferedImage getScreenShot() {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        paint(image.getGraphics());
        return image;
    }

}
