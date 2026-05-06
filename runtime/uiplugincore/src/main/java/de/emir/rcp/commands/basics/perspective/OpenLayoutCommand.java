package de.emir.rcp.commands.basics.perspective;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.ids.Basic;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.PropertyStore;
import de.emir.runtime.plugin.windows.MainWindow;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

import java.io.File;
import java.io.IOException;

/**
 * Loads a layout file and applies the layout when executed.
 */
public class OpenLayoutCommand extends AbstractCommand {

    private File file;
    private boolean tabContext;

    /**
     * Creates a new OpenLayoutCommand.
     * @param file Layout file to load.
     * @param tabContext If set to true, the tab layout property is disabled everytime the OpenLayoutCommand is executed. 
     *                   This ensures that the normal layout and tab layout functionalities do not collide.
     */
    public OpenLayoutCommand(File file, boolean tabContext) {
        this.file = file;
        this.tabContext = tabContext;
    }

    /**
     * Loads the given layout file as the current application layout.
     */
    @Override
    public void execute() {
        if (!tabContext) {
            PropertyStore.getContext(Basic.TAB_LAYOUT_PROP_CTX)
                    .getProperty(Basic.TAB_LAYOUT_ACTIVE_PROP, false).setValue(false);
        }
        PropertyStore.getContext(Basic.LAYOUT_PROP_CTX)
                .getProperty(Basic.LAYOUT_SELECTED_PROP,
                        ResourceManager.get(OpenLayoutCommand.class).getHomePath() + File.separator + "application-layout.xml")
                .setValue(file.toString());
        MainWindow window = PlatformUtil.getWindowManager().getMainWindow();
        try {
            window.loadLayout(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
