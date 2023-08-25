package de.emir.rcp.commands.basics.perspective;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.runtime.plugin.windows.MainWindow;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.*;
import java.nio.file.Path;

public class ExportLayoutCommand extends AbstractCommand {
    @Override
    public void execute() {
        ResourceManager manager = ResourceManager.get(ImportLayoutCommand.class);
        Path layoutFile = manager.getHomePath().resolve("layout.xml");

        MainWindow window = PlatformUtil.getWindowManager().getMainWindow();

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Export Dialog");
        chooser.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getAbsolutePath().endsWith(".xml");
            }

            @Override
            public String getDescription() {
                return "XML Layout File";
            }
        });
        int result = chooser.showSaveDialog(window);
        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file = chooser.getSelectedFile();

        try {
            copyFileUsingStream(layoutFile.toFile(), file);
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, "Couldn't copy file!");
        }
    }

    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }
}
