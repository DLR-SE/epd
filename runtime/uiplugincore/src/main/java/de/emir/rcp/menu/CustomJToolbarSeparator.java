package de.emir.rcp.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JSeparator;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import de.emir.rcp.ids.Basic;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class CustomJToolbarSeparator extends JSeparator {

    private static final long serialVersionUID = 7807847567683601470L;

    private Boolean showDev;
    private String fullPath;
    private AbstractUIPlugin provider;

    public CustomJToolbarSeparator(int orientation, String fullPath, AbstractUIPlugin provider) {
        super(orientation);

        this.fullPath = fullPath;
        this.provider = provider;

        PropertyContext propContext = PropertyStore.getContext(Basic.DEV_PROP_CTX);
        IProperty<Boolean> property = propContext.getProperty(Basic.PROP_DEV_SHOW_MENU_CONTRIBUTIONS, false);
        showDev = property.getValue();

        if (showDev == true) {
            setToolTipText("");
        }

    }

    @Override
    public JToolTip createToolTip() {

        if (showDev == true) {
            return new CustomDevTooltip(fullPath, provider);
        } else {
            return super.createToolTip();
        }

    }

    @Override
    public Dimension getPreferredSize() {

        if (getOrientation() == SwingConstants.VERTICAL) {
            return new Dimension(7, 24);
        } else {
            return super.getPreferredSize();
        }

    }

    @Override
    public void paint(Graphics g) {

        Dimension size = getSize();

        g.setColor(UIManager.getColor("Separator.foreground"));

        if (getOrientation() == SwingConstants.HORIZONTAL) {

            // g.drawLine(0, size.height / 2 - 1, size.width, size.height / 2 - 1);
            g.setColor(UIManager.getColor("Separator.shadow"));
            g.drawLine(0, size.height / 2, size.width, size.height / 2);

        } else {

            // g.drawLine(size.width / 2 - 1, 0, size.width / 2 - 1, size.height);
            g.setColor(UIManager.getColor("Separator.shadow"));
            g.drawLine(size.width / 2, 0, size.width / 2, size.height);

        }

    }

}
