package de.emir.epd.alert;

import de.emir.epd.alert.ids.AlertBasics;
import de.emir.epd.alert.view.AlertStatusBar;
import de.emir.epd.alert.view.settings.AlertSettingsPage;
import de.emir.rcp.ids.Basic;
import de.emir.rcp.settings.ep.SettingsPageExtensionPoint;
import de.emir.rcp.statusbar.ep.StatusBarExtensionPoint;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;
import de.emir.tuml.ucore.runtime.logging.ULog;
import org.apache.logging.log4j.Logger;

public class AlertViewPlugin extends AbstractUIPlugin {
    private static Logger LOG = ULog.getLogger(AlertViewPlugin.class);

    @Override
    public void initializePlugin() {

    }

    @Override
    public void registerExtensionPoints() {

    }

    @Override
    public void addExtensions() {
        StatusBarExtensionPoint sbEP = ExtensionPointManager.getExtensionPoint(StatusBarExtensionPoint.class);
        if (sbEP != null) {
            sbEP.element(AlertStatusBar.class);
        }

        SettingsPageExtensionPoint settingsPageExtensionPoint = ExtensionPointManager.getExtensionPoint(SettingsPageExtensionPoint.class);

        if (settingsPageExtensionPoint != null) {
            settingsPageExtensionPoint
                    .page(Basic.SETTINGS_GENERAL_SETTINGS_PAGE)
                    .page(AlertBasics.ALERT_VIEW_SETTINGS_PAGE_ID, AlertSettingsPage.class)
                    .label("Alert View")
                    .icon("/icons/emiricons/32/feedback.png");
        }
    }
}
