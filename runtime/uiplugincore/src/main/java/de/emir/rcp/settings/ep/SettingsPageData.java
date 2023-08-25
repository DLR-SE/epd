package de.emir.rcp.settings.ep;

import javax.swing.ImageIcon;

import de.emir.rcp.settings.AbstractSettingsPage;

public class SettingsPageData {
    public String id;
    public String label;
    public ImageIcon icon;
    public Class<? extends AbstractSettingsPage> pageClass;

    @Override
    public String toString() {
        if (label != null) {
            return label;
        }

        return id;
    }
}
