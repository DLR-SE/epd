package de.emir.rcp.pluginmanager.views.details;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.emir.rcp.commands.ExecuteCommandAction;
import de.emir.rcp.pluginmanager.ids.PMBasics;
import de.emir.rcp.pluginmanager.manager.PmManager;
import de.emir.rcp.pluginmanager.model.DependencyData;
import de.emir.rcp.pluginmanager.views.PluginRenderUtil;
import de.emir.rcp.ui.widgets.AbstractDetailsContentPanel;
import de.emir.tuml.runtime.epf.ObservableDependency;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import io.reactivex.rxjava3.disposables.Disposable;

public class DependencyDetailsContentPanel extends AbstractDetailsContentPanel<ObservableDependency> {

    private List<Disposable> subscriptions = new ArrayList<>();
    private JTextField groupText;
    private JTextField artifactText;
    private JTextField versionText;

    public DependencyDetailsContentPanel(ObservableDependency o) {
        super(o);

    }

    @Override
    public String getTitle() {

        PmManager pm = ServiceManager.get(PmManager.class);
        DependencyData dd = pm.getDependencyData(getObject());

        return PluginRenderUtil.getLabel(dd);
    }

    @Override
    public Icon getIcon() {

        PmManager pm = ServiceManager.get(PmManager.class);
        DependencyData dd = pm.getDependencyData(getObject());

        return PluginRenderUtil.getIcon(dd);
    }

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public Component createContents() {

        JPanel p = new JPanel();
        GridBagLayout gbl_p = new GridBagLayout();
        gbl_p.columnWidths = new int[] { 0, 0, 0 };
        gbl_p.rowHeights = new int[] { 0, 0, 0, 0, 0 };
        gbl_p.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
        gbl_p.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0 };
        p.setLayout(gbl_p);

        JLabel lblId = new JLabel("<groupId>");
        GridBagConstraints gbc_lblId = new GridBagConstraints();
        gbc_lblId.anchor = GridBagConstraints.EAST;
        gbc_lblId.insets = new Insets(10, 10, 5, 5);
        gbc_lblId.gridx = 0;
        gbc_lblId.gridy = 0;
        p.add(lblId, gbc_lblId);

        groupText = new JTextField();
        GridBagConstraints gbc_groupText = new GridBagConstraints();
        gbc_groupText.insets = new Insets(10, 0, 5, 5);
        gbc_groupText.fill = GridBagConstraints.HORIZONTAL;
        gbc_groupText.gridx = 1;
        gbc_groupText.gridy = 0;
        p.add(groupText, gbc_groupText);
        groupText.setColumns(10);
        groupText.setEditable(false);

        JLabel label = new JLabel("</groupId>");
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.anchor = GridBagConstraints.WEST;
        gbc_label.insets = new Insets(10, 0, 5, 0);
        gbc_label.gridx = 2;
        gbc_label.gridy = 0;
        p.add(label, gbc_label);
        JLabel lblUrl = new JLabel("<artifactId>");
        GridBagConstraints gbc_lblUrl = new GridBagConstraints();
        gbc_lblUrl.anchor = GridBagConstraints.EAST;
        gbc_lblUrl.insets = new Insets(0, 15, 5, 5);
        gbc_lblUrl.gridx = 0;
        gbc_lblUrl.gridy = 1;
        p.add(lblUrl, gbc_lblUrl);

        artifactText = new JTextField();
        GridBagConstraints gbc_artifactText = new GridBagConstraints();
        gbc_artifactText.insets = new Insets(0, 0, 5, 5);
        gbc_artifactText.fill = GridBagConstraints.HORIZONTAL;
        gbc_artifactText.gridx = 1;
        gbc_artifactText.gridy = 1;
        p.add(artifactText, gbc_artifactText);
        artifactText.setColumns(10);
        artifactText.setEditable(false);

        JLabel label_1 = new JLabel("</artifactId>");
        GridBagConstraints gbc_label_1 = new GridBagConstraints();
        gbc_label_1.anchor = GridBagConstraints.WEST;
        gbc_label_1.insets = new Insets(0, 0, 5, 15);
        gbc_label_1.gridx = 2;
        gbc_label_1.gridy = 1;
        p.add(label_1, gbc_label_1);

        JLabel lblNewLabel = new JLabel("<version>");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 2;
        p.add(lblNewLabel, gbc_lblNewLabel);

        versionText = new JTextField();
        GridBagConstraints gbc_versionText = new GridBagConstraints();
        gbc_versionText.insets = new Insets(0, 0, 5, 5);
        gbc_versionText.fill = GridBagConstraints.HORIZONTAL;
        gbc_versionText.gridx = 1;
        gbc_versionText.gridy = 2;
        p.add(versionText, gbc_versionText);
        versionText.setColumns(10);
        versionText.setEditable(false);

        JLabel label_2 = new JLabel("</version>");
        GridBagConstraints gbc_label_2 = new GridBagConstraints();
        gbc_label_2.anchor = GridBagConstraints.WEST;
        gbc_label_2.insets = new Insets(0, 0, 5, 0);
        gbc_label_2.gridx = 2;
        gbc_label_2.gridy = 2;
        p.add(label_2, gbc_label_2);

        JButton btnEdit = new JButton("Edit");
        GridBagConstraints gbc_btnEdit = new GridBagConstraints();
        gbc_btnEdit.insets = new Insets(0, 0, 5, 0);
        gbc_btnEdit.gridwidth = 2;
        gbc_btnEdit.anchor = GridBagConstraints.EAST;
        gbc_btnEdit.gridx = 2;
        gbc_btnEdit.gridy = 3;

        btnEdit.setIcon(PluginRenderUtil.EDIT_ICON);

        p.add(btnEdit, gbc_btnEdit);

        btnEdit.addActionListener(new ExecuteCommandAction(PMBasics.EDIT_CMD_ID));

        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.gridwidth = 3;
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 5;
        p.add(panel, gbc_panel);

        updateValues();

        return p;
    }

    private void updateValues() {
        ObservableDependency dep = getObject();

        groupText.setText(dep.getGroupId());
        artifactText.setText(dep.getArtifactId());
        versionText.setText(dep.getVersion());
    }

    @Override
    public void onOpen() {

        ObservableDependency dep = getObject();

        subscriptions.add(dep.subscribeGroupId(opt -> updateValues()));
        subscriptions.add(dep.subscribeArtifactId(opt -> updateValues()));
        subscriptions.add(dep.subscribeVersion(opt -> updateValues()));

    }

    @Override
    public void onClose() {

        for (Disposable d : subscriptions) {
            d.dispose();
        }

    }

}
