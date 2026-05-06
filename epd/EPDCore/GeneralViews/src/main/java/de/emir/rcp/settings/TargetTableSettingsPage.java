package de.emir.rcp.settings;

import de.emir.rcp.settings.panels.TargetTableSettingsPanel;

import java.awt.*;

/**
 * Component for registering the TargetTableSettings to the plugin.
 */
public class TargetTableSettingsPage extends AbstractSettingsPage {
    private TargetTableSettingsPanel panel;

    @Override
    public Component fillPage() {
        panel = new TargetTableSettingsPanel();
        return panel;
    }

    /**
     * Checks if the target table settings have been modified.
     * @return True if modified.
     */
    @Override
    public boolean isDirty() {
        if(this.panel != null) {
            return panel.isDirty();
        } else {
            return false;
        }

    }

    /**
     * Store the changes of the target table panel to the property store.
     */
    @Override
    public void finish() {
        if(panel != null) {
            panel.saveSettings();
        }
    }
}
