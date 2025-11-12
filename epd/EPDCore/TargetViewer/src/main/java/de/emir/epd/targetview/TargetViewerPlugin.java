package de.emir.epd.targetview;

import de.emir.epd.mapview.ep.MapViewExtensionPoint;
import de.emir.rcp.settings.ep.SettingsPageExtensionPoint;
import de.emir.rcp.views.ep.IViewGroup;
import de.emir.rcp.views.ep.ViewExtensionPoint;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

/**
 * Entrypoint of the TargetViewer plugin. This plugin provides means to display all TrackedTarget and Target objects
 * inserted into the model on the map similar to the AISViewer plugin.
 */
public class TargetViewerPlugin extends AbstractUIPlugin {
    @Override
    public void addExtensions() {
        ResourceManager resourceManager = ResourceManager.get(TargetViewerPlugin.class);
        MapViewExtensionPoint mapViewExtensionPoint = ExtensionPointManager.getExtensionPoint(MapViewExtensionPoint.class);
        mapViewExtensionPoint.layer("TargetLayer", TargetLayer.class).label("Targets")
                .icon("icons/emiricons/32/radar.png")
                .settingsPanel(TargetViewMapSettingsPanel.class);

        ViewExtensionPoint vEP = ExtensionPointManager.getExtensionPoint(ViewExtensionPoint.class);
        IViewGroup group = vEP.group("TargetGroup")
                .label("Target")
                .icon("icons/emiricons/32/radar.png", resourceManager);

        group.view("targetViewer", TargetView.class)
                .label("Target")
                .icon("icons/emiricons/32/radar.png");

        SettingsPageExtensionPoint spEP = ExtensionPointManager.getExtensionPoint(SettingsPageExtensionPoint.class);

        spEP.page("targetLayerSettingsPage", TargetViewerSettingsPage.class)
                .label("Target View")
                .icon("icons/emiricons/32/radar.png", ResourceManager.get(getClass()));

        spEP.page("targetReferenceSettingsPage", TargetViewerReferenceSettingsPage.class)
                .label("Target References")
                .icon("icons/emiricons/32/add_location_alt.png", ResourceManager.get(getClass()));
    }
}
