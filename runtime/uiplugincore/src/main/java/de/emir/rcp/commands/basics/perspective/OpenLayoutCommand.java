package de.emir.rcp.commands.basics.perspective;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.runtime.plugin.windows.MainWindow;

import java.io.File;
import java.io.IOException;

public class OpenLayoutCommand extends AbstractCommand {

    private File file;

    public OpenLayoutCommand(File file) {
        this.file = file;
    }

    @Override
    public void execute() {
        MainWindow window = PlatformUtil.getWindowManager().getMainWindow();

        try {
            window.loadLayout(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
