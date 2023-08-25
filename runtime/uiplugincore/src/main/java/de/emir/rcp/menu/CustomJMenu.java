package de.emir.rcp.menu;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenu;
import javax.swing.JToolTip;

import de.emir.rcp.ids.Basic;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class CustomJMenu extends JMenu {

    /**
     * 
     */
    private static final long serialVersionUID = 478835743022944654L;
    private Boolean showDev;
    private String fullPath;
    private AbstractUIPlugin provider;

    public CustomJMenu(String label, String tooltip, String fullPath, AbstractUIPlugin provider) {
        super(label);

        this.fullPath = fullPath;
        this.provider = provider;

        setToolTipText(tooltip);

        setFocusPainted(false);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseExited(MouseEvent e) {
                CustomJMenu.this.setCursor(Cursor.getDefaultCursor());

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                CustomJMenu.this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            }

        });

        PropertyContext propContext = PropertyStore.getContext(Basic.DEV_PROP_CTX);
        IProperty<Boolean> property = propContext.getProperty(Basic.PROP_DEV_SHOW_MENU_CONTRIBUTIONS, false);
        showDev = property.getValue();

        if (showDev == true && tooltip == null) {
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

}
