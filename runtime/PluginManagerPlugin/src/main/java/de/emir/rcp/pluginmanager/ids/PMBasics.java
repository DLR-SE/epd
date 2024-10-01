package de.emir.rcp.pluginmanager.ids;

import de.emir.rcp.ids.Basic;
import de.emir.tuml.runtime.epf.provider.impl.PomFileDescriptorProvider;

public class PMBasics {

    // TODO: Also set in AbstractProduct. Not referenced to avoid dependency. Find a
    // better solution?
    public static final String PRODUCT_FILE_NAME = "Product.xml";
    public static final String EDIT_ARG = "-edit";

    public static final String LOCK_ARG = "-lock";

    public static final String MAVEN_PLUGIN_PROPERTY = PomFileDescriptorProvider.PLUGIN_CLASS_PROPERTY;

    private static final String ID_PREFIX = "de.emir.rcp.pluginmanager.";

    public static final String PLUGINS_VIEW_ID = ID_PREFIX + "PluginsView";

    public static final String PM_PROP_CTX = ID_PREFIX + "prop.Context";
    public static final String PM_PROP_LAST_PRODUCT_PATH = ID_PREFIX + "prop.LastProductPath";
    public static final String PM_PROP_LAST_FILE_FOLDER = ID_PREFIX + "prop.LastSelectedFileFolder";
    public static final String PM_PROP_LAST_EXPORT_SETTINGS_MAP = "LastExportSettingsMap"; //ID_PREFIX + "prop.LastExportSettingsMap";
    public static final String CHANGE_PRODUCT_DEF_CMD_ID = ID_PREFIX + "cmd.chooseProductDefCommand";
    public static final String REMOVE_ELEMENT_CMD_ID = ID_PREFIX + "cmd.removePluginCommand";
    public static final String CLEAR_PLUGINS_CMD_ID = ID_PREFIX + "cmd.clearPluginCommand";
    public static final String ADD_PLUGINS_CMD_ID = ID_PREFIX + "cmd.addPluginCommand";
    public static final String BUGGED_ADD_PLUGINS_CMD_ID = ID_PREFIX + "cmd.buggedAddPluginCommand";
    public static final String SAVE_AND_EXIT_CMD_ID = ID_PREFIX + "cmd.SaveAndExitCommand";
    public static final String EDIT_CMD_ID = ID_PREFIX + "cmd.EditCommand";
    public static final String ADD_REPO_CMD_ID = ID_PREFIX + "cmd.AddRepositoryCommand";
    public static final String ADD_DEP_CMD_ID = ID_PREFIX + "cmd.AddDependencyCommand";
    public static final String RESOLVE_DEPENDENCIES_CMD_ID = ID_PREFIX + "cmd.ResolvedependenciesCommand";
    public static final String EXPORT_PRODUCT_CMD_ID = ID_PREFIX + "cmd.ExportProductCommand";

    public static final String PLUGINS_TOOLBAR_ID = Basic.TOOLBAR_IDENTIFIER + "pluginsToolbar";
    public static final String PLUGINS_TOOLBAR_RIGHT_ID = Basic.TOOLBAR_IDENTIFIER + "pluginsToolbarRight";
    public static final String STATUS_BAR_TOOLBAR_ID = Basic.TOOLBAR_IDENTIFIER + "pluginsStatusBarToolbar";

    public static final String PLUGIN_TREE_SELECTION_CTX = ID_PREFIX + "pluginTreeSelectionContext";

    public static final String EXTENSION_POINT_DOXYGEN = "DoxygenExtensionPoint";

}
