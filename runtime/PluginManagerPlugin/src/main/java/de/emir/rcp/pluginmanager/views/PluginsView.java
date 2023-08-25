package de.emir.rcp.pluginmanager.views;

import de.emir.rcp.commands.ExecuteCommandAction;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.AbstractModelProvider;
import de.emir.rcp.model.ModelTransactionStack;
import de.emir.rcp.pluginmanager.ids.PMBasics;
import de.emir.rcp.pluginmanager.manager.PmManager;
import de.emir.rcp.pluginmanager.model.DependencyData;
import de.emir.rcp.ui.widgets.JTreeWithFilterAndDetailsArea;
import de.emir.rcp.views.AbstractView;
import de.emir.tuml.runtime.epf.ObservableDependency;
import de.emir.tuml.runtime.epf.ObservableRepository;
import de.emir.tuml.runtime.epf.ProductFile;
import de.emir.tuml.runtime.epf.utils.MavenUtil;
import de.emir.tuml.runtime.epf.utils.WorkspaceResolver;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import io.reactivex.rxjava3.disposables.Disposable;
import io.tesla.aether.Repository;
import org.apache.maven.model.Model;
import org.apache.maven.model.Parent;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class PluginsView extends AbstractView {
    private JTextField textField;

    private Disposable productFileDisposeable;
    private JTreeWithFilterAndDetailsArea pluginsTree;

    private JButton btnChoose;

    private Disposable workspacesSubscription;

    private DefaultMutableTreeNode rootNode;

    private Disposable stackSubscription;

    private Disposable reposSubscription;

    private Disposable depsSubscription;

    private List<Disposable> modelSubscriptions = new ArrayList<>();

    public static final String CAT_WORKSPACES = "Workspaces";
    public static final String CAT_DEPENDENCIES = "Dependencies";
    public static final String CAT_REPOSITORIES = "Repositories";
    public static final String CAT_LOCAL_REPO = "Local Repository";

    public PluginsView(String id) {
        super(id);
    }

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public Component createContent() {

        JPanel p = new JPanel();
        GridBagLayout gbl_p = new GridBagLayout();

        gbl_p.columnWeights = new double[]{1.0};
        gbl_p.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0};
        p.setLayout(gbl_p);

        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;
        p.add(panel, gbc_panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0};
        gbl_panel.rowWeights = new double[]{0.0};
        panel.setLayout(gbl_panel);

        JLabel lblProductDefinition = new JLabel("Product Definition:");
        GridBagConstraints gbc_lblProductDefinition = new GridBagConstraints();
        gbc_lblProductDefinition.anchor = GridBagConstraints.EAST;
        gbc_lblProductDefinition.insets = new Insets(5, 5, 5, 5);
        gbc_lblProductDefinition.gridx = 0;
        gbc_lblProductDefinition.gridy = 0;
        panel.add(lblProductDefinition, gbc_lblProductDefinition);

        textField = new JTextField();
        textField.setEditable(false);
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(5, 0, 5, 0);
        gbc_textField.fill = GridBagConstraints.BOTH;
        gbc_textField.gridx = 1;
        gbc_textField.gridy = 0;
        panel.add(textField, gbc_textField);
        textField.setColumns(10);

        btnChoose = new JButton("Choose...");
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.fill = GridBagConstraints.VERTICAL;
        gbc_btnNewButton.insets = new Insets(5, 5, 5, 5);
        gbc_btnNewButton.gridx = 2;
        gbc_btnNewButton.gridy = 0;
        panel.add(btnChoose, gbc_btnNewButton);

        JSeparator separator = new JSeparator();
        GridBagConstraints gbc_separator = new GridBagConstraints();
        gbc_separator.fill = GridBagConstraints.HORIZONTAL;
        gbc_separator.insets = new Insets(0, 5, 2, 5);
        gbc_separator.gridx = 0;
        gbc_separator.gridy = 1;
        p.add(separator, gbc_separator);

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        JPanel tbArea = new JPanel();

        GridBagLayout gbl_tbArea = new GridBagLayout();

        gbl_tbArea.columnWeights = new double[]{0.0, 1.0};
        gbl_tbArea.rowWeights = new double[]{0.0};
        tbArea.setLayout(gbl_tbArea);

        GridBagConstraints gbc_toolbarLeft = new GridBagConstraints();
        gbc_toolbarLeft.anchor = GridBagConstraints.WEST;
        gbc_toolbarLeft.insets = new Insets(0, 3, 3, 3);
        gbc_toolbarLeft.gridx = 0;
        gbc_toolbarLeft.gridy = 0;

        tbArea.add(toolBar, gbc_toolbarLeft);

        JToolBar toolBarRight = new JToolBar();
        toolBarRight.setFloatable(false);

        GridBagConstraints gbc_toolbarRight = new GridBagConstraints();
        gbc_toolbarRight.anchor = GridBagConstraints.EAST;
        gbc_toolbarRight.insets = new Insets(0, 3, 3, 3);
        gbc_toolbarRight.gridx = 1;
        gbc_toolbarRight.gridy = 0;

        tbArea.add(toolBarRight, gbc_toolbarRight);

        GridBagConstraints gbc_tbArea = new GridBagConstraints();
        gbc_tbArea.fill = GridBagConstraints.HORIZONTAL;
        gbc_tbArea.insets = new Insets(0, 0, 0, 0);
        gbc_tbArea.gridx = 0;
        gbc_tbArea.gridy = 2;
        p.add(tbArea, gbc_tbArea);

        pluginsTree = new JTreeWithFilterAndDetailsArea();
        GridBagConstraints gbc_tree = new GridBagConstraints();
        gbc_tree.insets = new Insets(0, 5, 0, 5);
        gbc_tree.fill = GridBagConstraints.BOTH;
        gbc_tree.gridx = 0;
        gbc_tree.gridy = 3;
        p.add(pluginsTree, gbc_tree);

        rootNode = new DefaultMutableTreeNode();
        pluginsTree.setRootNode(rootNode);
        pluginsTree.setTreeWidth(250);
        pluginsTree.setMinimumTreeWidth(250);
        pluginsTree.setCellRenderer(new PluginsTreeCellRenderer());
        pluginsTree.setDetailsAreaProvider(new PluginsTreeDetailsAreaProvider());
        btnChoose.addActionListener(new ExecuteCommandAction(PMBasics.CHANGE_PRODUCT_DEF_CMD_ID));

        bindProductFile();

        PlatformUtil.getMenuManager().fillToolbar(toolBar, PMBasics.PLUGINS_TOOLBAR_ID);
        PlatformUtil.getMenuManager().fillToolbar(toolBarRight, PMBasics.PLUGINS_TOOLBAR_RIGHT_ID);
        pluginsTree.addTreeSelectionListener(new PluginsTreeSelectionListener());

        return p;
    }

    private void bindProductFile() {

        handleProductFileChanged();

        PmManager pmm = ServiceManager.get(PmManager.class);
        productFileDisposeable = pmm.getModelProvider().subscribeProductFile(opt -> handleProductFileChanged());

        AbstractModelProvider mp = PlatformUtil.getModelManager().getModelProvider();
        mp.subscribeTransactionStack(opt -> checkStackSubscription());

    }

    private void checkStackSubscription() {

        if (stackSubscription != null) {
            stackSubscription.dispose();
        }

        AbstractModelProvider mp = PlatformUtil.getModelManager().getModelProvider();
        ModelTransactionStack ts = mp.getTransactionStack();

        setProductPathTextField();

        if (ts == null) {
            return;
        }

        stackSubscription = ts.subscribeDirtyState(c -> setProductPathTextField());

    }

    private void handleProductFileChanged() {

        disposeSubscriptions();

        setProductPathTextField();
        setChooseButtonEnabledState();
        setTreeModel();

        subscribeWorkspaces();
        subscribeRepositories();
        subscribeDependencies();

    }

    private void disposeSubscriptions() {

        if (workspacesSubscription != null) {
            workspacesSubscription.dispose();
        }
        if (reposSubscription != null) {
            reposSubscription.dispose();
        }

        if (depsSubscription != null) {
            depsSubscription.dispose();
        }

    }

    private void subscribeWorkspaces() {
        PmManager pmm = ServiceManager.get(PmManager.class);
        ProductFile pf = pmm.getModelProvider().getProductFile();

        if (pf == null) {
            return;
        }
        workspacesSubscription = pf.subscribeWorkspaces(c -> setTreeModel());

    }

    private void subscribeRepositories() {

        PmManager pmm = ServiceManager.get(PmManager.class);
        ProductFile pf = pmm.getModelProvider().getProductFile();

        if (pf == null) {
            return;
        }
        reposSubscription = pf.subscribeRepositories(c -> setTreeModel());

    }

    private void subscribeDependencies() {

        PmManager pmm = ServiceManager.get(PmManager.class);
        ProductFile pf = pmm.getModelProvider().getProductFile();

        if (pf == null) {
            return;
        }
        depsSubscription = pf.subscribeDependencies(c -> setTreeModel());

    }

    private void setTreeModel() {

        pluginsTree.clearSelection();
        PlatformUtil.getSelectionManager().setSelection(PMBasics.PLUGIN_TREE_SELECTION_CTX, null);

        rootNode.removeAllChildren();

        for (Disposable sub : modelSubscriptions) {
            sub.dispose();
        }

        modelSubscriptions.clear();

        PmManager pmm = ServiceManager.get(PmManager.class);

        MavenUtil mu = pmm.getModelProvider().getMavenUtil();

        ProductFile pf = pmm.getModelProvider().getProductFile();

        if (pf == null) {
            return;
        }

        DefaultMutableTreeNode nodeWs = new DefaultMutableTreeNode(CAT_WORKSPACES);
        DefaultMutableTreeNode nodeDependencies = new DefaultMutableTreeNode(CAT_DEPENDENCIES);
        DefaultMutableTreeNode nodeRepositories = new DefaultMutableTreeNode(CAT_REPOSITORIES);

        LocalRepositoryNodeData lrnd = new LocalRepositoryNodeData();
        lrnd.setProductFile(pf);
        DefaultMutableTreeNode nodeLocalRepo = new DefaultMutableTreeNode(lrnd);

        rootNode.add(nodeWs);
        rootNode.add(nodeDependencies);
        rootNode.add(nodeRepositories);
        rootNode.add(nodeLocalRepo);

        buildTree(pf, mu, nodeWs);

        List<ObservableDependency> deps = pf.getDependencies();

        for (ObservableDependency dependency : deps) {

            DefaultMutableTreeNode depNode = new DefaultMutableTreeNode(dependency);
            DependencyData dd = pmm.getDependencyData(dependency);
            nodeDependencies.add(depNode);
            modelSubscriptions.add(dd.subscribeState(state -> {
                setTreeModel();
            }));

        }

        List<ObservableRepository> repos = pf.getRemoteRepositories();

        for (Repository repo : repos) {

            DefaultMutableTreeNode repoNode = new DefaultMutableTreeNode(repo);
            nodeRepositories.add(repoNode);
        }

        sort(nodeWs);

        pluginsTree.setRootNode(rootNode);

        for (int i = 0; i < pluginsTree.getRowCount(); ++i) {
            pluginsTree.expandRow(i);
        }

    }

    private void buildTree(ProductFile pf, MavenUtil mu, DefaultMutableTreeNode nodeWs) {
        Collection<Model> workspaces = new WorkspaceResolver(mu).resolveWorkspaces(pf.getWorkspaces());

        List<Model> rootPoms = new ArrayList<>();

        for (Model model : workspaces) {
            Model m = model;
            while (m.getParent() != null) {
                Parent parent = m.getParent();
                Path path = m.getPomFile().toPath();
                path = path.getParent().resolve(parent.getRelativePath());
                path = path.normalize();

                m = mu.readModel(path.toFile());
            }

            boolean contains = false;
            for (Model check : rootPoms) {
                if (check.getPomFile().equals(m.getPomFile())) {
                    contains = true;
                    break;
                }
            }

            if (contains == false) {
                rootPoms.add(m);
            }
        }

        for (Model m : rootPoms) {
            buildTree(nodeWs, m, null, mu, workspaces);
        }
    }

    private void buildTree(DefaultMutableTreeNode root, Model model, PluginTreeNodeData parent, MavenUtil mu, Collection<Model> workspaces) {

        if (model == null || root == null || mu == null)
            return;
        if (model.getPomFile() == null)
            return;

        String pomFolder = model.getPomFile().getParent();

        PluginTreeNodeData data = new PluginTreeNodeData(parent, model);

        DefaultMutableTreeNode node = new DefaultMutableTreeNode(data);

        for (String mod : model.getModules()) {

            File modFile = new File(pomFolder + File.separator + mod + File.separator + "pom.xml");

            for (Model workspace : workspaces) {
                if (workspace.getPomFile().getParent().contains(pomFolder)) {
                    Model moduleModel = mu.readModel(modFile);
                    buildTree(node, moduleModel, data, mu, workspaces);
                    break;
                }
            }
        }

        if (node.getChildCount() > 0) {
            root.add(node);
        } else if (model.getModules().size() == 0) {
            for (Model m : workspaces) {
                if (m.getPomFile().getAbsolutePath().equals(model.getPomFile().getAbsolutePath())) {
                    root.add(node);
                    break;
                }
            }
        }
    }

    public void sort(DefaultMutableTreeNode parent) {
        Comparator<DefaultMutableTreeNode> tnc = Comparator.comparing(DefaultMutableTreeNode::isLeaf)
                .thenComparing(n -> n.getUserObject().toString());

        int n = parent.getChildCount();
        for (int i = 0; i < n - 1; i++) {

            int min = i;
            for (int j = i + 1; j < n; j++) {

                if (tnc.compare((DefaultMutableTreeNode) parent.getChildAt(min),
                        (DefaultMutableTreeNode) parent.getChildAt(j)) > 0) {
                    min = j;
                }
            }

            if (i != min) {
                DefaultMutableTreeNode a = (DefaultMutableTreeNode) parent.getChildAt(i);
                DefaultMutableTreeNode b = (DefaultMutableTreeNode) parent.getChildAt(min);
                parent.insert(b, i);
                parent.insert(a, min);
            }
        }

        for (int i = 0; i < n - 1; i++) {

            DefaultMutableTreeNode child = (DefaultMutableTreeNode) parent.getChildAt(i);
            if (child.getChildCount() > 0) {
                sort(child);
            }

        }
    }

    private void setChooseButtonEnabledState() {
        PmManager pmm = ServiceManager.get(PmManager.class);

        btnChoose.setEnabled(pmm.getModelProvider().isLocked() == false);

    }

    private void setProductPathTextField() {

        PmManager pmm = ServiceManager.get(PmManager.class);

        ProductFile pf = pmm.getModelProvider().getProductFile();

        ModelTransactionStack ts = pmm.getModelProvider().getTransactionStack();

        if (pf != null) {

            File f = pf.getFile();
            textField.setText(f.getAbsolutePath() + ((ts != null && ts.isDirty()) ? "*" : ""));

        } else {

            textField.setText("");

        }

    }

    @Override
    public void onOpen() {

    }

    @Override
    public void onClose() {

        productFileDisposeable.dispose();

    }

}
