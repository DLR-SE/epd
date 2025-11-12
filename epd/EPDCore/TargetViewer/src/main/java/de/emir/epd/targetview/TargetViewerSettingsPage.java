package de.emir.epd.targetview;

import de.emir.epd.targetview.panels.TargetSettingsPanel;
import de.emir.rcp.settings.AbstractSettingsPage;

import java.awt.*;

/**
 * Settings page for the TargetViewer plugin.
 */
public class TargetViewerSettingsPage extends AbstractSettingsPage {
    private TargetSettingsPanel panel;

    /**
     * Creates the content for the TargetViewer plugin settings page.
     *
     * @return Content which should be displayed as the settings page.
     */
    @Override
    public Component fillPage() {
        panel = new TargetSettingsPanel();
        return panel;
    }

    /**
     * Checks if the settings page is dirty, i.e. if the settings were manipulated and are not yet saved.
     *
     * @return
     */
    @Override
    public boolean isDirty() {
        if (panel != null) {
            return panel.isDirty();
        } else {
            return false;
        }

    }

    /**
     * Saves the current settings to the config.
     */
    @Override
    public void finish() {
        if (panel != null) {
            panel.saveSettings();
        }
    }
}
