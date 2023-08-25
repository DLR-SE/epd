package de.emir.rcp.manager;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import de.emir.rcp.settings.ep.SettingsPageData;
import de.emir.rcp.settings.ep.SettingsPageDescriptor;
import de.emir.rcp.settings.ep.SettingsPageExtensionPoint;
import de.emir.tuml.ucore.runtime.extension.IService;

public class SettingsPageManager implements IService {

    private SettingsPageExtensionPoint spEP = new SettingsPageExtensionPoint();

    public SettingsPageExtensionPoint getSettingsPageExtensionPoint() {
        return spEP;
    }

    /**
     * Returns a tree with all registered settings pages
     * 
     * @return
     */
    public DefaultMutableTreeNode getTreeNodes() {

        List<SettingsPageDescriptor> pages = spEP.getPages();

        DefaultMutableTreeNode root = new DefaultMutableTreeNode();

        for (SettingsPageDescriptor childPage : pages) {
            createSubtree(root, childPage);
        }

        return root;

    }

    private void createSubtree(DefaultMutableTreeNode root, SettingsPageDescriptor page) {

        SettingsPageData spd = new SettingsPageData();
        spd.id = page.getId();
        spd.label = page.getLabel();
        spd.icon = page.getIcon();
        spd.pageClass = page.getPageClass();

        DefaultMutableTreeNode child = new DefaultMutableTreeNode(spd);

        root.add(child);

        List<SettingsPageDescriptor> childPages = page.getPages();

        for (SettingsPageDescriptor childPage : childPages) {
            createSubtree(child, childPage);
        }

    }

}
