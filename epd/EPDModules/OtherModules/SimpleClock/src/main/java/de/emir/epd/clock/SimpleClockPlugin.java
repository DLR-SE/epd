package de.emir.epd.clock;

import de.emir.epd.clock.ids.ClockBasics;
import de.emir.epd.clock.view.ClockView;
import de.emir.epd.clock.view.settings.ClockSettingsPage;
import de.emir.rcp.settings.ep.SettingsPageExtensionPoint;
import de.emir.rcp.views.ep.ViewExtensionPoint;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;
import de.emir.tuml.ucore.runtime.logging.ULog;
import org.apache.logging.log4j.Logger;

/**
 * Plugin which provides a clock view for the layout. The clock view displays the date and time for
 * the local (configurable) timezone and UTC.
 */
public class SimpleClockPlugin extends AbstractUIPlugin {

    /**
     * Configures and registers the clock views and settings pages.
     */
    @Override
    public void addExtensions() {
        ViewExtensionPoint extensionPoint = ExtensionPointManager.getExtensionPoint(ViewExtensionPoint.class);
        if(extensionPoint != null) {
            extensionPoint.view(ClockBasics.CLOCK_VIEW_ID, ClockView.class).label("Clock").icon("/icons/emiricons/32/schedule.png");
        }

        SettingsPageExtensionPoint settingsPageExtensionPoint = ExtensionPointManager.getExtensionPoint(SettingsPageExtensionPoint.class);
        if(settingsPageExtensionPoint != null) {
            settingsPageExtensionPoint.page(ClockBasics.CLOCK_VIEW_SETTINGS_PAGE_ID, ClockSettingsPage.class).label("Clock View").icon("/icons/emiricons/32/schedule.png");
        }
    }
}
