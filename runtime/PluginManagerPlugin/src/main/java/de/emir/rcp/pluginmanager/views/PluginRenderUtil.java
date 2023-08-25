package de.emir.rcp.pluginmanager.views;

import java.awt.Image;

import javax.swing.Icon;

import org.apache.maven.model.Model;

import de.emir.rcp.pluginmanager.ids.PMBasics;
import de.emir.rcp.pluginmanager.manager.PmManager;
import de.emir.rcp.pluginmanager.model.DependencyData;
import de.emir.rcp.pluginmanager.model.DependencyState;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.resources.IconManager;

public class PluginRenderUtil {
    private static Icon pluginIcon = IconManager.getIcon(ServiceManager.get(PmManager.class),
            "icons/emiricons/32/extension.png", IconManager.preferedSmallIconSize());

    private static Icon pluginAndFolderIcon = IconManager.getIcon(ServiceManager.get(PmManager.class),
            "icons/emiricons/32/sbe_extensions.png", IconManager.preferedSmallIconSize());

    private static Icon folderIcon = IconManager.getIcon(ServiceManager.get(PmManager.class),
            "icons/emiricons/32/folder.png", IconManager.preferedSmallIconSize());

    private static Icon pomIcon = IconManager.getIcon(ServiceManager.get(PmManager.class), 
            "icons/emiricons/32/code.png", IconManager.preferedSmallIconSize());

    public static final Icon WS_ICON = IconManager.getIcon(ServiceManager.get(PmManager.class), 
            "icons/emiricons/32/work.png", IconManager.preferedSmallIconSize());

    public static final Icon DEP_ICON = IconManager.getIcon(ServiceManager.get(PmManager.class),
            "icons/emiricons/32/organization.png", IconManager.preferedSmallIconSize());

    public static final Icon REPOSITORIES_ICON = IconManager.getIcon(ServiceManager.get(PmManager.class),
            "icons/emiricons/32/stateful_set.png", IconManager.preferedSmallIconSize());

    public static final Icon REPO_ICON = IconManager.getIcon(ServiceManager.get(PmManager.class),
            "icons/emiricons/32/database.png", IconManager.preferedSmallIconSize());

    public static final Icon EDIT_ICON = IconManager.getIcon(ServiceManager.get(PmManager.class),
            "icons/emiricons/32/edit.png", IconManager.preferedSmallIconSize());
    public static final Image EDIT_ICON_IMAGE = IconManager.getScaledImage(ServiceManager.get(PmManager.class),
            "icons/emiricons/32/edit.png", IconManager.preferedSmallIconSize());

    public static final Image EXPORT_ICON_IMAGE = IconManager.getScaledImage(ServiceManager.get(PmManager.class),
            "icons/emiricons/32/archive.png", IconManager.preferedSmallIconSize());

    public static final Icon MAVEN_ICON = pomIcon;

    public static final Icon UNKNOWN_TYPE = IconManager.getIcon(ServiceManager.get(PmManager.class),
            "icons/emiricons/32/sync_problem.png", IconManager.preferedSmallIconSize());

    public static final Icon DEP_NOT_FOUND_ICON = IconManager.getIcon(ServiceManager.get(PmManager.class),
            "icons/emiricons/32/dangerous.png", IconManager.preferedSmallIconSize());

    public static final Icon LOCAL_REPO_ICON = IconManager.getIcon(ServiceManager.get(PmManager.class),
            "icons/emiricons/32/volume.png", IconManager.preferedSmallIconSize());

    public static final Icon CLASS_ICON = IconManager.getIcon(ServiceManager.get(PmManager.class),
            "icons/emiricons/32/application_instance.png", IconManager.preferedSmallIconSize());

    public static final Icon ERROR_ICON = IconManager.getIcon(ServiceManager.get(PmManager.class),
            "icons/emiricons/32/cancel.png", IconManager.preferedSmallIconSize());

    public static final Icon INFO_ICON = IconManager.getIcon(ServiceManager.get(PmManager.class),
            "icons/emiricons/32/lightbulb.png", IconManager.preferedSmallIconSize());

    public static Icon getIcon(Model m) {
        Object pluginProp = m.getProperties().get(PMBasics.MAVEN_PLUGIN_PROPERTY);
        if (pluginProp instanceof String) {
            if (m.getModules().size() > 0) {
                return pluginAndFolderIcon;
            } else {
                return pluginIcon;
            }
        } else {
            if (m.getModules().size() > 0) {
                return folderIcon;
            } else {
                return pomIcon;
            }
        }

    }

    public static Icon getIcon(DependencyData dd) {
        if (dd.getState() == DependencyState.NOT_FOUND) {
            return DEP_NOT_FOUND_ICON;
        } else if (dd.getState() == DependencyState.RESOLVED_PLUGIN) {
            return pluginIcon;
        } else if (dd.getState() == DependencyState.RESOLVED_LIB) {
            return pomIcon;
        }
        return UNKNOWN_TYPE;
    }

    public static String getLabel(PluginTreeNodeData data) {
        String artifactID = data.model.getArtifactId();
        artifactID = "<html>" + artifactID + "</html>";
        return artifactID;
    }

    public static String getLabel(DependencyData dd) {
        String label = dd.getDependency().getArtifactId();
        label += dd.getState() == DependencyState.NOT_FOUND ? " <font color=\"#BA8A87\">[missing]</font>" : "";
        return "<html>" + label + "</html>";
    }
}
