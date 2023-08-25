package de.emir.rcp.pluginmanager.views;

import org.apache.maven.model.Model;

public class PluginTreeNodeData {

    public Model model;
    public PluginTreeNodeData parent;

    public PluginTreeNodeData(PluginTreeNodeData parent, Model model) {
        this.model = model;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return this.model.getArtifactId();
    }

}
