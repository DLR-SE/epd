package de.emir.rcp.pluginmanager.views;

import de.emir.rcp.pluginmanager.views.details.DependencyDescriptionContentPanel;
import de.emir.rcp.pluginmanager.views.details.DependencyDetailsContentPanel;
import de.emir.rcp.pluginmanager.views.details.LocalRepositoryContentPanel;
import de.emir.rcp.pluginmanager.views.details.PluginDetailsContentPanel;
import de.emir.rcp.pluginmanager.views.details.RepositoryDescriptionContentPanel;
import de.emir.rcp.pluginmanager.views.details.RepositoryDetailsContentPanel;
import de.emir.rcp.pluginmanager.views.details.WorkspaceDescriptionContentPanel;
import de.emir.rcp.ui.widgets.AbstractDetailsContentPanel;
import de.emir.rcp.ui.widgets.IDetailsAreaProvider;
import de.emir.tuml.runtime.epf.ObservableDependency;
import de.emir.tuml.runtime.epf.ObservableRepository;

public class PluginsTreeDetailsAreaProvider implements IDetailsAreaProvider {

    @Override
    public AbstractDetailsContentPanel<?> getDetailsPanel(Object o) {

        if (o instanceof PluginTreeNodeData) {
            return new PluginDetailsContentPanel((PluginTreeNodeData) o);
        } else if (o instanceof ObservableRepository) {
            return new RepositoryDetailsContentPanel((ObservableRepository) o);
        } else if (o instanceof ObservableDependency) {
            return new DependencyDetailsContentPanel((ObservableDependency) o);
        } else if (o instanceof String) {

            if (o.equals(PluginsView.CAT_WORKSPACES)) {
                return new WorkspaceDescriptionContentPanel((String) o);
            } else if (o.equals(PluginsView.CAT_DEPENDENCIES)) {
                return new DependencyDescriptionContentPanel((String) o);
            } else if (o.equals(PluginsView.CAT_REPOSITORIES)) {
                return new RepositoryDescriptionContentPanel((String) o);
            }

        } else if (o instanceof LocalRepositoryNodeData) {
            return new LocalRepositoryContentPanel((LocalRepositoryNodeData) o);
        }

        return null;

    }

}
