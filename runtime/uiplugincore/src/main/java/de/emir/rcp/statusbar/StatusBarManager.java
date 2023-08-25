package de.emir.rcp.statusbar;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.statusbar.ep.StatusBarElementDescriptor;
import de.emir.rcp.statusbar.ep.StatusBarExtensionPoint;
import de.emir.runtime.plugin.windows.MainWindow;
import de.emir.tuml.ucore.runtime.extension.IService;
import de.emir.tuml.ucore.runtime.logging.ULog;

public class StatusBarManager implements IService {

    private StatusBarExtensionPoint ep = new StatusBarExtensionPoint();

    public StatusBarManager() {

    }

    public StatusBarExtensionPoint getStatusBarExtensionPoint() {
        return ep;
    }

    public void fillStatusBar() {

        MainWindow mw = PlatformUtil.getWindowManager().getMainWindow();
        JPanel statusBar = mw.getStatusBar();

        if (statusBar == null) {
            // Bar not visible
            return;
        }

        List<StatusBarElementDescriptor> elements = ep.getElements();

        for (StatusBarElementDescriptor ed : elements) {

            MatteBorder mainBorder = new MatteBorder(0, 0, 2, 0, Color.red) {

                private static final long serialVersionUID = 9034343104646594880L;

                @Override
                public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

                    g.setColor(Color.black);
                    g.drawLine(0, 0, 0, height);
                    g.setColor(Color.DARK_GRAY);
                    g.drawLine(1, 0, 1, height);
                }

            };

            JLabel separatorLabel = new JLabel(" ");

            separatorLabel.setBorder(mainBorder);

            statusBar.add(separatorLabel);

            Class<? extends AbstractStatusBarElement> elementClass = ed.getElementClass();

            AbstractStatusBarElement element = null;

            try {
                element = elementClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                ULog.error(e);
            }

            if (element != null) {

                Component elemContents = element.createContents();
                statusBar.add(elemContents);

            }

        }
    }

}
