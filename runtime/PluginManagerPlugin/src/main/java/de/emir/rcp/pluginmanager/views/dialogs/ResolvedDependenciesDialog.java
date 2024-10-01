package de.emir.rcp.pluginmanager.views.dialogs;

import de.emir.rcp.manager.util.PlatformUtil;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.emir.rcp.pluginmanager.manager.PmManager;
import de.emir.rcp.pluginmanager.views.PluginRenderUtil;
import de.emir.tuml.runtime.epf.ObservableDependency;
import de.emir.tuml.runtime.epf.ProductFile;
import de.emir.tuml.runtime.epf.utils.MavenUtil;
import de.emir.tuml.runtime.epf.utils.WorkspaceResolver;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;

public class ResolvedDependenciesDialog extends JDialog {

    private Model model;
    private JList<DependencyWrapper> resolvedList;

    public ResolvedDependenciesDialog(Model model) {
        this.model = model;
        init();
    }

    protected void init() {
        resolvedList = new JList<>(getResolvedAsDependency(model));
        resolvedList.setCellRenderer(new ListRenderer());
        getContentPane().add(resolvedList, BorderLayout.CENTER);
        setLocationRelativeTo(PlatformUtil.getWindowManager().getMainWindow());
    }

    private DependencyWrapper[] getResolvedAsDependency(Model model){
        List<DependencyWrapper> wrappers = new ArrayList<>();
        MavenUtil mavenUtil = new MavenUtil(true);
        PmManager pm = ServiceManager.get(PmManager.class);
        ProductFile pf = pm.getModelProvider().getProductFile();
        for (Dependency dependency : model.getDependencies()){
            if (MavenUtil.isRequired(dependency) == false){
                continue;
            }

            if (isResolvedAsWorkspace(pf, dependency)){
                wrappers.add(new DependencyWrapper(MavenUtil.getCoordinate(dependency), true, true));
                continue;
            }

            if (isResolvedAsDependency(pf, dependency)){
                wrappers.add(new DependencyWrapper(MavenUtil.getCoordinate(dependency), true, false));
                continue;
            }

            wrappers.add(new DependencyWrapper(MavenUtil.getCoordinate(dependency), false, false));
        }

        return wrappers.toArray(new DependencyWrapper[0]);
    }

    private boolean isResolvedAsDependency(ProductFile pf, Dependency dependency) {

        List<ObservableDependency> observableDependencies = pf.getDependencies();
        for (ObservableDependency oDependency : observableDependencies){
            if (dependency.getGroupId().equals(oDependency.getGroupId()) == false){
                continue;
            }

            if (dependency.getArtifactId().equals(oDependency.getArtifactId()) == false){
                continue;
            }

            //TODO check for Version ? (it might be null)

            return true;
        }


        return false;
    }

    private boolean isResolvedAsWorkspace(ProductFile pf, Dependency dependency) {
        Collection<Model> workspaces = new WorkspaceResolver(new MavenUtil(true)).resolveWorkspaces(pf.getWorkspaces());
        for (Model workspace : workspaces){
            if (dependency.getGroupId().equals(workspace.getGroupId()) == false){
                continue;
            }

            if (dependency.getArtifactId().equals(workspace.getArtifactId()) == false){
                continue;
            }

            //TODO check for Version ? (it might be null)

            return true;
        }

        return false;
    }

    class DependencyWrapper {
        private String coordinate;
        private boolean resolved;
        private boolean workspace;

        public DependencyWrapper(String coordinate, boolean resolved, boolean workspace) {
            this.coordinate = coordinate;
            this.resolved = resolved;
            this.workspace = workspace;
        }

        public boolean isResolved() {
            return resolved;
        }

        public String getCoordinate() {
            return coordinate;
        }

        public boolean isWorkspace() {
            return workspace;
        }

        @Override
        public String toString() {
            return coordinate;
        }
    }

    class ListRenderer extends DefaultListCellRenderer{
        private static final long serialVersionUID = 6363575970858790545L;

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value instanceof DependencyWrapper){
                DependencyWrapper wrapper = (DependencyWrapper) value;
                if (wrapper.isResolved() == false){
                    setIcon(PluginRenderUtil.ERROR_ICON);
                    setText(wrapper.getCoordinate());
                }else {
                    if (wrapper.isWorkspace()){
                        setIcon(PluginRenderUtil.WS_ICON);
                        setText(wrapper.getCoordinate());
                    }else {
                        setIcon(PluginRenderUtil.DEP_ICON);
                        setText(wrapper.getCoordinate());
                    }
                }
            }

            return this;
        }
    }
}
