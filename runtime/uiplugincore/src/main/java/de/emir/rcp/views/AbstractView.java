package de.emir.rcp.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import bibliothek.gui.dock.common.ColorMap;
import bibliothek.gui.dock.common.DefaultSingleCDockable;
import bibliothek.gui.dock.common.action.CAction;
import bibliothek.gui.dock.common.event.CDockableStateListener;
import bibliothek.gui.dock.common.event.CFocusListener;
import bibliothek.gui.dock.common.intern.CDockable;
import bibliothek.gui.dock.common.mode.ExtendedMode;
import bibliothek.gui.dock.themes.basic.DisplayerContentPane;
import bibliothek.gui.dock.util.ConfiguredBackgroundPanel;
import de.emir.rcp.manager.util.PlatformUtil;

public abstract class AbstractView extends DefaultSingleCDockable {

    private JPanel parentPanel;
    private ViewLockStateObserver lockStateObserver;
    private Thread notifyThread;

    public AbstractView(String id) {
        super(id, (CAction[]) null);

        // Manages the visibility of the title bar and the resize functionality
        lockStateObserver = new ViewLockStateObserver(this);

        parentPanel = new JPanel();

        parentPanel.setLayout(new BorderLayout());

        getContentPane().add(parentPanel);
        setFocusComponent(parentPanel);

        addCDockableStateListener(new CDockableStateListener() {

            @Override
            public void visibilityChanged(CDockable dockable) {
                if (dockable.isVisible() == true) {
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

//                if (defaultBorder instanceof EtchedBorder) {
//
//                    Border etched = BorderFactory.createEtchedBorder(UIManager.getColor("Button.select"), UIManager.getColor("controlShadow"));
//
//                    pp.setBorder(etched);
//
//                }
//                pp.setBorder(new LineBorder(UIManager.getColor("textHighlight")));

            }
        });

        lockStateObserver.setState();

        PlatformUtil.getKeyBindingManager().registerViewKeyBindings(this);

    }

    /**
     * Lets the title area of this view blink, if it is minimized
     */
    public void showNotification() {

        if (isShowing() == true) {
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

    public JPanel getParentPanel() {
        return parentPanel;
    }

    public abstract Component createContent();

    public abstract void onOpen();

    public abstract void onClose();

    public void onShutdown() {

    }

    public void onFocusGained() {
    }

    public void onFocusLost() {
    }

    /**
     * Shows a collapsed view
     */
    public void show() {
        toFront();
    }
}
