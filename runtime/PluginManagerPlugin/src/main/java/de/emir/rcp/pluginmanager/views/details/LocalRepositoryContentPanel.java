package de.emir.rcp.pluginmanager.views.details;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.emir.rcp.pluginmanager.manager.PmManager;
import de.emir.rcp.pluginmanager.model.transactions.SetLocalRepositoryTransaction;
import de.emir.rcp.pluginmanager.views.LocalRepositoryNodeData;
import de.emir.rcp.pluginmanager.views.PluginRenderUtil;
import de.emir.rcp.pluginmanager.views.PluginsView;
import de.emir.rcp.ui.widgets.AbstractDetailsContentPanel;
import de.emir.tuml.runtime.epf.ProductFile;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import io.reactivex.rxjava3.disposables.Disposable;

public class LocalRepositoryContentPanel extends AbstractDetailsContentPanel<LocalRepositoryNodeData> {
    private JTextField textField;
    private Disposable subscription;

    public LocalRepositoryContentPanel(LocalRepositoryNodeData o) {
        super(o);

    }

    @Override
    public String getTitle() {
        return PluginsView.CAT_LOCAL_REPO;
    }

    @Override
    public Icon getIcon() {
        return PluginRenderUtil.LOCAL_REPO_ICON;
    }

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public Component createContents() {
        JPanel p = new JPanel();
        GridBagLayout gbl_p = new GridBagLayout();
        gbl_p.columnWeights = new double[] { 0.0, 1.0, 0.0 };
        gbl_p.rowWeights = new double[] { 0.0, 1.0 };
        p.setLayout(gbl_p);

        JLabel lblPath = new JLabel("Path:");
        GridBagConstraints gbc_lblPath = new GridBagConstraints();
        gbc_lblPath.insets = new Insets(5, 15, 5, 5);
        gbc_lblPath.anchor = GridBagConstraints.EAST;
        gbc_lblPath.gridx = 0;
        gbc_lblPath.gridy = 0;
        p.add(lblPath, gbc_lblPath);

        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(5, 0, 5, 5);
        gbc_textField.fill = GridBagConstraints.BOTH;
        gbc_textField.gridx = 1;
        gbc_textField.gridy = 0;
        p.add(textField, gbc_textField);
        textField.setColumns(10);
        textField.setEditable(false);
        JButton btnBrowse = new JButton("Browse...");
        GridBagConstraints gbc_btnBrowse = new GridBagConstraints();
        gbc_btnBrowse.insets = new Insets(5, 0, 5, 15);
        gbc_btnBrowse.gridx = 2;
        gbc_btnBrowse.gridy = 0;
        p.add(btnBrowse, gbc_btnBrowse);

        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.gridwidth = 3;
        gbc_panel.insets = new Insets(0, 0, 0, 5);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 1;
        p.add(panel, gbc_panel);

        updateValues();

        btnBrowse.addActionListener(e -> handleBrowse());

        return p;
    }

    private void handleBrowse() {

        LocalRepositoryNodeData nd = getObject();
        ProductFile pf = nd.getProductFile();

        String localRepo = pf.getLocalRepository();
        JFileChooser dialog = new JFileChooser(localRepo);

        dialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int dialogResult = dialog.showOpenDialog(textField);

        if (dialogResult != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File selection = dialog.getSelectedFile();

        PmManager pm = ServiceManager.get(PmManager.class);

        SetLocalRepositoryTransaction t = new SetLocalRepositoryTransaction(pf, selection.getAbsolutePath());

        pm.getModelProvider().getTransactionStack().run(t);

    }

    private void updateValues() {

        LocalRepositoryNodeData nd = getObject();
        ProductFile pf = nd.getProductFile();

        String localRepo = pf.getLocalRepository();

        if (localRepo == null) {
            textField.setText(null);
            return;
        }

        textField.setText(localRepo);
    }

    @Override
    public void onOpen() {
        LocalRepositoryNodeData nd = getObject();
        ProductFile pf = nd.getProductFile();
        subscription = pf.subscribeLocalRepository(opt -> updateValues());

    }

    @Override
    public void onClose() {

        if (subscription != null) {
            subscription.dispose();
        }

    }

}
