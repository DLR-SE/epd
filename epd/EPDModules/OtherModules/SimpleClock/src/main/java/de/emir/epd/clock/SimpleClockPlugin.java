package de.emir.epd.clock;

import de.emir.epd.clock.ids.ClockBasics;
import de.emir.epd.clock.view.ClockView;
import de.emir.epd.clock.view.settings.ClockSettingsPage;
import de.emir.rcp.settings.ep.SettingsPageExtensionPoint;
import de.emir.rcp.views.ep.ViewExtensionPoint;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;
import de.emir.tuml.ucore.runtime.logging.ULog;
import org.slf4j.Logger;

public class SimpleClockPlugin extends AbstractUIPlugin {
    private static Logger LOG = ULog.getLogger(SimpleClockPlugin.class);

    @Override
    public void initializePlugin() {

    }

    @Override
    public void registerExtensionPoints() {

    }

    @Override
    public void addExtensions() {
        ViewExtensionPoint extensionPoint = ExtensionPointManager.getExtensionPoint(ViewExtensionPoint.class);
        extensionPoint.view(ClockBasics.CLOCK_VIEW_ID, ClockView.class).label("Clock").icon("/icons/emiricons/32/schedule.png");

        SettingsPageExtensionPoint settingsPageExtensionPoint = ExtensionPointManager.getExtensionPoint(SettingsPageExtensionPoint.class);
        settingsPageExtensionPoint.page(ClockBasics.CLOCK_VIEW_SETTINGS_PAGE_ID, ClockSettingsPage.class).label("Clock View").icon("/icons/emiricons/32/schedule.png");
    }

    @Override
    public void postAddExtensions() {
    	// TODO: set time
    }
}
