package de.emir.rcp.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import bibliothek.gui.dock.common.ColorMap;
import bibliothek.gui.dock.common.DefaultMultipleCDockable;
import bibliothek.gui.dock.common.event.CDockableStateListener;
import bibliothek.gui.dock.common.event.CFocusListener;
import bibliothek.gui.dock.common.intern.CDockable;
import bibliothek.gui.dock.common.mode.ExtendedMode;
import bibliothek.gui.dock.themes.basic.DisplayerContentPane;
import bibliothek.gui.dock.util.ConfiguredBackgroundPanel;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**
 * Base class for all views of the application. Each view needs to be registered using
 * the ViewExtensionPoint and it's properties are described by the ViewDescriptor.
 * Important: Each Instance of a specific view (if the view is reopenable) is assigned to the same ViewDescriptor.
 * Therefore each view contains the ID of the descriptor which it is assigned to (descriptorID) and the ID of the
 * view itself (uniqueID). If multiple instances of a view can be created, each uniqueID is created in the following
 * scheme: descriptorID+_InstanceX, for example for ViewDescriptor AISView it would be AISView_Instance1, AISView_Instance2 etc.
 */
public abstract class AbstractView extends DefaultMultipleCDockable {
    protected final String uniqueID;
    protected final String descriptorID;
    private JPanel parentPanel;
    private ViewLockStateObserver lockStateObserver;
    private Thread notifyThread;

    /**
     * Creates a new AbstractView instance.
     * @param id Unique ID of the view.
     */
    public AbstractView(String id) {
        super(PlatformUtil.getWindowManager().getMainWindow().getMainControl().getMultipleDockableFactory("AbstractViewFactory"));

        this.uniqueID = id;
        this.descriptorID = uniqueID.split("_Instance")[0];

        // Manages the visibility of the title bar and the resize functionality
        lockStateObserver = new ViewLockStateObserver(this);

        parentPanel = new JPanel();

        parentPanel.setLayout(new BorderLayout());

        getContentPane().add(parentPanel);
        setFocusComponent(parentPanel);

        addCDockableStateListener(new CDockableStateListener() {

            @Override
            public void visibilityChanged(CDockable dockable) {
                if (dockable.isVisible()) {
                    onOpen();
                } else {
                    onClose();
                }

            }

            @Override
            public void extendedModeChanged(CDockable dockable, ExtendedMode mode) {
                // TODO

            }
        });

        addFocusListener(new CFocusListener() {

            private Border defaultBorder;

            @Override
            public void focusLost(CDockable dockable) {
                onFocusLost();

                ConfiguredBackgroundPanel pane = (ConfiguredBackgroundPanel) getContentPane();
                DisplayerContentPane pp = (DisplayerContentPane) pane.getParent();

                if (pp == null) {
                    return;
                }
                pp.setBorder(defaultBorder);

            }

            @Override
            public void focusGained(CDockable dockable) {
                onFocusGained();

                ConfiguredBackgroundPanel pane = (ConfiguredBackgroundPanel) getContentPane();
                DisplayerContentPane pp = (DisplayerContentPane) pane.getParent();

                defaultBorder = pp.getBorder();

            }
        });

        lockStateObserver.setState();

        PlatformUtil.getKeyBindingManager().registerViewKeyBindings(this);

    }

    /**
     * Lets the title area of this view blink, if it is minimized
     */
    public void showNotification() {
        if (isShowing()) {
            return;
        }
        if (notifyThread != null) {
            return;
        }

        notifyThread = new Thread(new Runnable() {

            @Override
            public void run() {

                Color oldColor = getColors().getColor(ColorMap.COLOR_KEY_MINIMIZED_BUTTON_BACKGROUND);

                for (int i = 0; i < 5; i++) {

                    setTabBackgroundColor(i % 2 == 0 ? new Color(43, 61, 84) : oldColor);

                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        setTabBackgroundColor(oldColor);
                        return;
                    }

                }

                setTabBackgroundColor(oldColor);
                notifyThread = null;

            }
        });

        notifyThread.start();

    }

    private void setTabBackgroundColor(Color c) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                getColors().setColor(ColorMap.COLOR_KEY_MINIMIZED_BUTTON_BACKGROUND, c);

            }
        });

    }

    /**
     * Gets the parent panel of the AbstractView. This is the component where the layout of createContent of the view
     * is stored in.
     * @return Parent JPane.
     */
    public JPanel getParentPanel() {
        return parentPanel;
    }

    /**
     * Populates the content of the view.
     * @return Content of the view which should be displayed.
     */
    public abstract Component createContent();

    /**
     * OnOpen handler which is called when the view is opened.
     */
    public abstract void onOpen();

    /**
     * OnClose handler which is called when the view is closed.
     */
    public abstract void onClose();

    /**
     * OnShutdown handler which is called when the plugin shuts down.
     */
    public void onShutdown() {

    }

    /**
     * OnFocusGained handler which is called when the focus is shifted to this view.
     */
    public void onFocusGained() {
    }

    /**
     * OnFocusLost handler which is called when the focus is shifted away from this view.
     */
    public void onFocusLost() {
    }

    /**
     * Shows a collapsed view
     */
    public void show() {
        toFront();
    }

    /**
     * Gets the unique ID of the view. This usually equals the descriptor ID if the view is not reopenable. If
     * the view is reopenable, it will return descriptorID+_InstanceX where X specifies the current Instance of the view,
     * for example for AISTargetView it would be AISTargetView_Instance1, AISTargetView_Instance2 etc.
     * @return Unique ID of the view.
     */
    public String getUniqueId() {
        return this.uniqueID;
    }

    /**
     * Gets the descriptor ID of the view. This is the ID of the descriptor which is used for setting properties of this
     * view.
     * @return ID of the corresponding ViewDescriptor.
     */
    public String getDescriptorId() {
        return this.descriptorID;
    }
}
