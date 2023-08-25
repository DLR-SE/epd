package de.emir.rcp.pluginmanager.views.details;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.*;

import de.emir.rcp.pluginmanager.manager.PmManager;
import de.emir.rcp.pluginmanager.views.PluginsTreeCellRenderer;
import de.emir.rcp.pluginmanager.views.dialogs.ResolvedDependenciesDialog;
import de.emir.tuml.runtime.epf.ObservableDependency;
import de.emir.tuml.runtime.epf.PluginManager;
import de.emir.tuml.runtime.epf.ProductFile;
import de.emir.tuml.runtime.epf.utils.MavenUtil;
import de.emir.tuml.runtime.epf.utils.WorkspaceResolver;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;

import de.emir.rcp.pluginmanager.views.PluginRenderUtil;
import de.emir.rcp.pluginmanager.views.PluginTreeNodeData;
import de.emir.rcp.ui.widgets.AbstractDetailsContentPanel;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.eclipse.aether.artifact.Artifact;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.border.TitledBorder;

public class PluginDetailsContentPanel extends AbstractDetailsContentPanel<PluginTreeNodeData> {
    private JTextField artifactIdLabel;
    private JTextField groupValueLabel;
    private JTextField pathTextField;
    private JLabel label;
    private JLabel label_1;
    private JLabel lblPath;
    private JTree dependencyTree;
    private JTree neededFromTree;
    private JSplitPane splitPane;

    public PluginDetailsContentPanel(PluginTreeNodeData o) {
        super(o);
    }

    @Override
    public String getTitle() {
        return PluginRenderUtil.getLabel(getObject());
    }

    @Override
    public Icon getIcon() {
        PluginTreeNodeData data = getObject();
        return PluginRenderUtil.getIcon(data.model);
    }

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public Component createContents() {
        JPanel p = new JPanel();
        GridBagLayout gbl_p = new GridBagLayout();
        gbl_p.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
        gbl_p.rowHeights = new int[] { 0, 0, 0, 0, 0, 21, 0 };
        gbl_p.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
        gbl_p.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
        p.setLayout(gbl_p);

        lblPath = new JLabel("Path:");
        GridBagConstraints gbc_lblPath = new GridBagConstraints();
        gbc_lblPath.insets = new Insets(5, 0, 5, 5);
        gbc_lblPath.anchor = GridBagConstraints.EAST;
        gbc_lblPath.gridx = 0;
        gbc_lblPath.gridy = 0;
        p.add(lblPath, gbc_lblPath);

        pathTextField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridwidth = 3;
        gbc_textField.insets = new Insets(5, 0, 5, 5);
        gbc_textField.gridx = 1;
        gbc_textField.gridy = 0;
        p.add(pathTextField, gbc_textField);
        pathTextField.setEditable(false);

        JLabel lblGroup = new JLabel("<groupId>");
        GridBagConstraints gbc_lblGroup = new GridBagConstraints();
        gbc_lblGroup.anchor = GridBagConstraints.EAST;
        gbc_lblGroup.insets = new Insets(0, 5, 5, 5);
        gbc_lblGroup.gridx = 0;
        gbc_lblGroup.gridy = 2;
        p.add(lblGroup, gbc_lblGroup);

        groupValueLabel = new JTextField();
        GridBagConstraints gbc_groupValueLabel = new GridBagConstraints();
        gbc_groupValueLabel.fill = GridBagConstraints.HORIZONTAL;
        gbc_groupValueLabel.gridwidth = 3;
        gbc_groupValueLabel.insets = new Insets(0, 0, 5, 5);
        gbc_groupValueLabel.gridx = 1;
        gbc_groupValueLabel.gridy = 2;
        p.add(groupValueLabel, gbc_groupValueLabel);
        groupValueLabel.setEditable(false);
        JLabel lblArtifactid = new JLabel("<artifactId>");
        GridBagConstraints gbc_lblArtifactid = new GridBagConstraints();
        gbc_lblArtifactid.anchor = GridBagConstraints.EAST;
        gbc_lblArtifactid.insets = new Insets(0, 15, 5, 5);
        gbc_lblArtifactid.gridx = 0;
        gbc_lblArtifactid.gridy = 3;
        p.add(lblArtifactid, gbc_lblArtifactid);

        artifactIdLabel = new JTextField();
        GridBagConstraints gbc_artifactIdLabel = new GridBagConstraints();
        gbc_artifactIdLabel.fill = GridBagConstraints.HORIZONTAL;
        gbc_artifactIdLabel.gridwidth = 3;
        gbc_artifactIdLabel.insets = new Insets(0, 0, 5, 5);
        gbc_artifactIdLabel.gridx = 1;
        gbc_artifactIdLabel.gridy = 3;
        p.add(artifactIdLabel, gbc_artifactIdLabel);
        artifactIdLabel.setEditable(false);
        Model m = getObject().model;

        String groupId = m.getGroupId();

        String path = m.getPomFile().getAbsolutePath();

        if (groupId == null) {

            groupId = m.getParent() != null ? m.getParent().getGroupId() : null;

        }
        groupValueLabel.setText(groupId);
        artifactIdLabel.setText(m.getArtifactId());
        pathTextField.setText(path);

        label = new JLabel("</groupId>");
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.anchor = GridBagConstraints.WEST;
        gbc_label.insets = new Insets(0, 0, 5, 0);
        gbc_label.gridx = 4;
        gbc_label.gridy = 2;
        p.add(label, gbc_label);

        label_1 = new JLabel("</artifactId>");
        GridBagConstraints gbc_label_1 = new GridBagConstraints();
        gbc_label_1.anchor = GridBagConstraints.WEST;
        gbc_label_1.insets = new Insets(0, 0, 5, 15);
        gbc_label_1.gridx = 4;
        gbc_label_1.gridy = 3;
        p.add(label_1, gbc_label_1);
        
        splitPane = new JSplitPane();
        splitPane.setDividerSize(3);
        GridBagConstraints gbc_splitPane = new GridBagConstraints();
        gbc_splitPane.gridwidth = 5;
        gbc_splitPane.fill = GridBagConstraints.BOTH;
        gbc_splitPane.gridx = 0;
        gbc_splitPane.gridy = 5;
        p.add(splitPane, gbc_splitPane);

        JScrollPane scrollPane = new JScrollPane();
        splitPane.setLeftComponent(scrollPane);
        scrollPane.setPreferredSize(new Dimension(250,50));
        //scrollPane.setForeground(Color.WHITE);
        scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 5));
        scrollPane.setBorder(new TitledBorder(
                UIManager.getBorder("TitledBorder.border"), 
                "Dependencies", TitledBorder.LEADING, 
                TitledBorder.TOP, 
                new Font("Tahoma", 0, 10)));
        
        dependencyTree = new JTree(createDependencyNodes());
        dependencyTree.setCellRenderer(new PluginsTreeCellRenderer());
        dependencyTree.setRootVisible(false);
        dependencyTree.setShowsRootHandles(true);
        scrollPane.setViewportView(dependencyTree);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setPreferredSize(new Dimension(250,50));
        splitPane.setRightComponent(scrollPane_1);
        //scrollPane_1.setForeground(Color.WHITE);
        scrollPane_1.setFont(new Font("Tahoma", Font.PLAIN, 5));
        scrollPane_1.setBorder(new TitledBorder(
                UIManager.getBorder("TitledBorder.border"),
                "Required by", TitledBorder.LEADING,
                TitledBorder.TOP,
                new Font("Tahoma", 0, 10)));
        
        neededFromTree = new JTree(createNeededFromNodes());
        neededFromTree.setCellRenderer(new PluginsTreeCellRenderer());
        neededFromTree.setRootVisible(false);
        neededFromTree.setShowsRootHandles(true);
        scrollPane_1.setViewportView(neededFromTree);

        return p;
    }

    private MutableTreeNode createNeededFromNodes() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        Model current = getObject().model;
        root.setUserObject(getObject());
        if (current.getPackaging() != null && current.getPackaging().equals("pom")){
            return root;
        }

        checkModel(current);

        PmManager pmm = ServiceManager.get(PmManager.class);
        ProductFile pf = pmm.getModelProvider().getProductFile();

        MavenUtil util = new MavenUtil(pf, true);
        Collection<Model> models = new WorkspaceResolver(util).resolveWorkspaces(pf.getWorkspaces());

        for (Model model : models){
            checkModel(model);
            for (Dependency dependency : model.getDependencies()){

                checkDependency(model, dependency);

                if (dependency.getGroupId().equals(current.getGroupId()) == false){
                    continue;
                }

                if (dependency.getArtifactId().equals(current.getArtifactId()) == false){
                    continue;
                }

//                if (dependency.getVersion().equals(current.getVersion()) == false){
////                    addString = " {different-version}";
//                }

                root.add(new DefaultMutableTreeNode(new PluginTreeNodeData(getObject(), model)));
                break;
            }
        }

        for (ObservableDependency observableDependency : pf.getDependencies()){
            Artifact artifact = util.resolveArtifact(observableDependency.getCoordinate());
            Model model = null;
            try {
                model = util.resolveJarModel(artifact.getFile().toURI().toURL());
            } catch (MalformedURLException e) {
                continue;
            }

            if (model == null) continue;

            checkModel(model);

            for (Dependency dependency : model.getDependencies()){
                checkDependency(model, dependency);

                if (dependency.getGroupId().equals(current.getGroupId()) == false){
                    continue;
                }

                if (dependency.getArtifactId().equals(current.getArtifactId()) == false){
                    continue;
                }

//                if (dependency.getVersion().equals(current.getVersion()) == false){
////                    addString = " {different-version}";
//                }

                root.add(new DefaultMutableTreeNode(new PluginTreeNodeData(getObject(), model)));
                break;
            }
        }

        return root;
    }

    private void checkModel(Model model){
        String artifactID = model.getArtifactId();
        if (artifactID != null && artifactID.startsWith("$")){
            artifactID = artifactID.replace("${","").replace("}","");
            artifactID = (String) model.getProperties().get(artifactID);
        }
        String groupID = model.getGroupId();
        if (groupID != null && groupID.startsWith("$")){
            groupID = groupID.replace("${","").replace("}","");
            groupID = (String) model.getProperties().get(groupID);
        }else if (model.getGroupId() == null && model.getParent() != null){
            groupID = model.getParent().getGroupId();
        }

        String version = model.getVersion();
        if (version == null || version.trim().equals("")){
            version = "Unknown";
        }else if (version.startsWith("$")){
            version = version.replace("${","").replace("}","");
            version = (String) model.getProperties().get(version);
            if (version == null){
                version = "Unknown";
            }
        }

        model.setArtifactId(artifactID);
        model.setGroupId(groupID);
        model.setVersion(version);
    }

    private void checkDependency(Model model, Dependency dependency){
        String artifactID = dependency.getArtifactId();
        if (artifactID != null && artifactID.startsWith("$")){
            artifactID = artifactID.replace("${","").replace("}","");
            artifactID = (String) model.getProperties().get(artifactID);
        }
        String groupID = dependency.getGroupId();
        if (groupID != null && groupID.startsWith("$")){
            groupID = groupID.replace("${","").replace("}","");
            groupID = (String) model.getProperties().get(groupID);
        }
        String version = dependency.getVersion();
        if (version == null || version.trim().equals("")){
            version = "Unknown";
        }else if (version.startsWith("$")){
            version = version.replace("${","").replace("}","");
            version = (String) model.getProperties().get(version);
            if (version == null){
                version = "Unknown";
            }
        }

        dependency.setArtifactId(artifactID);
        dependency.setGroupId(groupID);
        dependency.setVersion(version);
    }

    private MutableTreeNode createDependencyNodes(){
        PluginTreeNodeData data = getObject();
        Model model = data.model;
        checkModel(model);

        PmManager pmm = ServiceManager.get(PmManager.class);
        ProductFile pf = pmm.getModelProvider().getProductFile();

        MavenUtil mavenUtil = new MavenUtil(pf, true);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode(data);

        for (Dependency dependency : model.getDependencies()){
            if (MavenUtil.isRequired(dependency) == false){
                continue;
            }

            boolean added = false;

            for (File file : pf.getWorkspaces()){
            	Model m = pmm.getWorkspaceModel(file);
            	if (m != null && m.getArtifactId().equals(dependency.getArtifactId())) {
            		//TODO: also check groupId and version
            		root.add(new DefaultMutableTreeNode(new PluginTreeNodeData(data, m)));
                    added = true;
                    break;
            	}
//                if (file.getAbsolutePath().contains(dependency.getArtifactId())){
//                    Model dep = mavenUtil.readModel(file);
//                    root.add(new DefaultMutableTreeNode(new PluginTreeNodeData(data, dep)));
//                    added = true;
//                    break;
//                }
            }

            if (added){
                continue;
            }

            for (ObservableDependency observableDependency : pf.getDependencies()){
                if (observableDependency.getArtifactId().equals(dependency.getArtifactId())){
                    root.add(new DefaultMutableTreeNode(observableDependency));
                    added = true;
                    break;
                }
            }

            if (added == false){
                root.add(new DefaultMutableTreeNode(dependency));
            }

        }

        return root;
    }

    @Override
    public void onOpen() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                splitPane.setDividerLocation(0.5);
            }
        });
    }

    @Override
    public void onClose() {

    }

}
