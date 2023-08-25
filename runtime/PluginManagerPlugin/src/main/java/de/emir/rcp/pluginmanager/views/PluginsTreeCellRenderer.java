package de.emir.rcp.pluginmanager.views;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import de.emir.tuml.runtime.epf.utils.MavenUtil;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;

import de.emir.rcp.pluginmanager.manager.PmManager;
import de.emir.rcp.pluginmanager.model.DependencyData;
import de.emir.tuml.runtime.epf.ObservableDependency;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import io.tesla.aether.Repository;

public class PluginsTreeCellRenderer extends DefaultTreeCellRenderer {

    private static final long serialVersionUID = 6363575970858790545L;

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
            int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

        Object o = node.getUserObject();
        if (o instanceof PluginTreeNodeData) {

            Model m = ((PluginTreeNodeData) o).model;

            setIcon(PluginRenderUtil.getIcon(m));
            setText(PluginRenderUtil.getLabel((PluginTreeNodeData) o));

        } else if (o instanceof Repository) {

            setText(((Repository) o).getId());
            setIcon(PluginRenderUtil.REPO_ICON);

        } else if (o instanceof String) {

            String cat = (String) o;
            setText(cat);

            if (cat.equals(PluginsView.CAT_WORKSPACES)) {
                setIcon(PluginRenderUtil.WS_ICON);
            } else if (cat.equals(PluginsView.CAT_DEPENDENCIES)) {
                setIcon(PluginRenderUtil.DEP_ICON);
            } else if (cat.equals(PluginsView.CAT_REPOSITORIES)) {
                setIcon(PluginRenderUtil.REPOSITORIES_ICON);
            }

        } else if (o instanceof ObservableDependency) {

            PmManager pm = ServiceManager.get(PmManager.class);
            DependencyData dd = pm.getDependencyData((ObservableDependency) o);

            setIcon(PluginRenderUtil.getIcon(dd));
            setText(PluginRenderUtil.getLabel(dd));

        } else if (o instanceof LocalRepositoryNodeData) {
            setIcon(PluginRenderUtil.LOCAL_REPO_ICON);
            setText(PluginsView.CAT_LOCAL_REPO);
        }else if (o instanceof Dependency){
            setIcon(PluginRenderUtil.MAVEN_ICON);
            setText(MavenUtil.getCoordinate((Dependency) o));
        }

        return this;
    }

}
