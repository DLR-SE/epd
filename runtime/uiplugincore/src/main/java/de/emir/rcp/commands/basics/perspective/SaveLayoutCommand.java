package de.emir.rcp.commands.basics.perspective;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.runtime.plugin.windows.MainWindow;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class SaveLayoutCommand extends AbstractCommand {
    @Override
    public void execute() {
        ResourceManager manager = ResourceManager.get(SaveLayoutCommand.class);
        Path home = manager.getHomePath();
        File layoutFolder = home.resolve("layouts").toFile();

        if (layoutFolder.exists() == false) {
            boolean check = layoutFolder.mkdir();
            if (check == false){
                JOptionPane.showConfirmDialog(null, "Couldn't create layout folder, please check application access rights");
                return;
            }

        }

        MainWindow window = PlatformUtil.getWindowManager().getMainWindow();

        String layoutName = JOptionPane.showInputDialog("Layout Name");
        if (layoutName == null) {
            return;
        }
        File layoutFile = new File(layoutFolder.getAbsolutePath() + File.separator + layoutName + ".xml");
        try {
            window.saveLayout(layoutFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
