package de.emir.rcp.commands.basics.perspective;

import de.emir.rcp.commands.basics.perspective.OpenLayoutCommand;
import de.emir.rcp.menu.AbstractDynamicMenuProvider;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Provider for the layout entries in the top menu bar.
 */
public class LayoutMenuProvider extends AbstractDynamicMenuProvider {

    private File file;

    /**
     * Creates a new layout mnenu provider.
     * @param file Directory from where to load layout files.
     */
    public LayoutMenuProvider(File file) {
        this.file = file;

        if (this.file.exists() == false) {
            this.file.mkdir();
        }

        if (this.file.isDirectory() == false) {
            this.file = this.file.getParentFile();
        }
    }

    /**
     * Loads all layout files as menu items.
     * @return List of menu items associated to each found layout file.
     */
    @Override
    public List<JMenuItem> populate() {
        List<JMenuItem> items = new ArrayList<>();

        File[] files = this.file.listFiles();
        if (files == null || files.length == 0) {
            items.add(new JMenuItem("Wow such empty"));
            return items;
        }

        for (File file : files) {
            if (!file.isDirectory()) {
                items.add(create(file));
            }
        }

        return items;
    }

    /**
     * Creates a new menu item from a layout file. The name of the file is used as a base for the 
     * menu item.
     * @param file File to load menu item for.
     * @return Corresponding menu item.
     */
    private JMenuItem create(File file) {
        String name = file.getName().split("\\.")[0];
        String first = name.substring(0, 1).toUpperCase();
        String rest = name.substring(1);
        JMenuItem item = new JMenuItem(first + rest);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OpenLayoutCommand cmd = new OpenLayoutCommand(file, false);
                cmd.execute();
            }
        });

        return item;
    }
}
