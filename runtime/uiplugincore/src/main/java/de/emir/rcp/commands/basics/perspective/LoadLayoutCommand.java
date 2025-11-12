package de.emir.rcp.commands.basics.perspective;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import de.emir.rcp.UICorePlugin;
import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.runtime.plugin.windows.MainWindow;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

public class LoadLayoutCommand extends AbstractCommand {
    @Override
    public void execute() {
        ResourceManager manager = ResourceManager.get(SaveLayoutCommand.class);
        Path home = manager.getHomePath();
        File layoutFolder = home.resolve("layouts").toFile();

        if (layoutFolder.exists() == false) {
            boolean check = layoutFolder.mkdir();
            JOptionPane.showConfirmDialog(null, "No saved layouts found");
            return;
        }

        MainWindow window = PlatformUtil.getWindowManager().getMainWindow();

        File[] files = layoutFolder.listFiles();
        if(files == null || files.length == 0){
            JOptionPane.showConfirmDialog(null, "No saved layouts found");
            return;
        }

        Map<String, File> map = new HashMap<>();
        for(File file : files){
            map.put(file.getName(), file);
        }

        String[] options = map.keySet().toArray(new String[0]);

        String fileName = (String) JOptionPane.showInputDialog(
                null,
                "Layout Name",
                "Load layout",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (fileName == null) {
            return;
        }
        File loadFile = map.get(fileName);

        try {
            window.loadLayout(loadFile);
        } catch (IOException e) {
			JOptionPane.showMessageDialog(null, e, "Couldn't load layout file!", JOptionPane.ERROR_MESSAGE,
					ResourceManager.get(UICorePlugin.class).getImageIcon("icons/emiricons/32/dangerous.png"));
        }

    }
}
