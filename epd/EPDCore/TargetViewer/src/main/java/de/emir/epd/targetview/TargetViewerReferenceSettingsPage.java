package de.emir.epd.targetview;

import de.emir.epd.targetview.panels.ReferenceSettingsPanel;
import de.emir.rcp.settings.AbstractSettingsPage;

import java.awt.*;

/**
 * Settings page component for the target reference settings. These settings influence how targets which are
 * only referenced by a bearing and distance to a reference target are handled. Using these settings,
 * the user can assign a fixed position, reference target received from the data or ownship as a position
 * reference to locate these targets.
 */
public class TargetViewerReferenceSettingsPage extends AbstractSettingsPage {
    private ReferenceSettingsPanel panel;

    /**
     * Loads the reference settings panel.
     *
     * @return New instance of the target reference settings panel.
     */
    @Override
    public Component fillPage() {
        panel = new ReferenceSettingsPanel();
        return panel;
    }

    /**
     * Checks if the settings should be saved, i.e. are changed.
     *
     * @return True if changed, else false.
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
     * Store the changes.
     */
    @Override
    public void finish() {
        if (panel != null) {
            panel.save();
        }
    }
}
