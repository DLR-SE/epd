package de.emir.rcp.pluginmanager.views.dialogs;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import de.emir.rcp.pluginmanager.views.PluginRenderUtil;
import de.emir.tuml.runtime.epf.ObservableDependency;

public class EditDependencyDialog extends JDialog {

    private static final long serialVersionUID = -2315770391175489012L;

    private ObservableDependency result;
    private JButton btnOk;
    private JTextField groupText;
    private JTextField artifactText;
    private JTextField versionText;

    public EditDependencyDialog(Window parent) {
        this(parent, null);

    }

    /**
     * @param parent
     * @param repo
     * @wbp.parser.constructor
     */
    public EditDependencyDialog(Window parent, ObservableDependency dependency) {
        super(parent);

        setModal(true);

        DocumentListener inputListener = new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateInputs();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                validateInputs();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateInputs();
            }
        };

        setIconImage(PluginRenderUtil.EDIT_ICON_IMAGE);
        setMinimumSize(new Dimension(400, 180));

        GridBagLayout gridBagLayout = new GridBagLayout();

        gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0 };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0 };
        getContentPane().setLayout(gridBagLayout);

        JLabel lblId = new JLabel("<groupId>");
        GridBagConstraints gbc_lblId = new GridBagConstraints();
        gbc_lblId.anchor = GridBagConstraints.EAST;
        gbc_lblId.insets = new Insets(10, 10, 5, 5);
        gbc_lblId.gridx = 0;
        gbc_lblId.gridy = 0;
        getContentPane().add(lblId, gbc_lblId);

        groupText = new JTextField();
        GridBagConstraints gbc_groupText = new GridBagConstraints();
        gbc_groupText.insets = new Insets(10, 0, 5, 5);
        gbc_groupText.fill = GridBagConstraints.HORIZONTAL;
        gbc_groupText.gridx = 1;
        gbc_groupText.gridy = 0;
        getContentPane().add(groupText, gbc_groupText);
        groupText.setColumns(10);
        groupText.getDocument().addDocumentListener(inputListener);

        JLabel label = new JLabel("</groupId>");
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.insets = new Insets(10, 0, 5, 10);
        gbc_label.gridx = 2;
        gbc_label.gridy = 0;
        getContentPane().add(label, gbc_label);
        JLabel lblUrl = new JLabel("<artifactId>");
        GridBagConstraints gbc_lblUrl = new GridBagConstraints();
        gbc_lblUrl.anchor = GridBagConstraints.EAST;
        gbc_lblUrl.insets = new Insets(0, 10, 5, 5);
        gbc_lblUrl.gridx = 0;
        gbc_lblUrl.gridy = 1;
        getContentPane().add(lblUrl, gbc_lblUrl);

        artifactText = new JTextField();
        GridBagConstraints gbc_artifactText = new GridBagConstraints();
        gbc_artifactText.insets = new Insets(0, 0, 5, 5);
        gbc_artifactText.fill = GridBagConstraints.HORIZONTAL;
        gbc_artifactText.gridx = 1;
        gbc_artifactText.gridy = 1;
        getContentPane().add(artifactText, gbc_artifactText);
        artifactText.setColumns(10);
        artifactText.getDocument().addDocumentListener(inputListener);

        JLabel label_1 = new JLabel("</artifactId>");
        GridBagConstraints gbc_label_1 = new GridBagConstraints();
        gbc_label_1.insets = new Insets(0, 0, 5, 10);
        gbc_label_1.gridx = 2;
        gbc_label_1.gridy = 1;
        getContentPane().add(label_1, gbc_label_1);

        JLabel lblNewLabel = new JLabel("<version>");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 2;
        getContentPane().add(lblNewLabel, gbc_lblNewLabel);

        versionText = new JTextField();
        GridBagConstraints gbc_versionText = new GridBagConstraints();
        gbc_versionText.insets = new Insets(0, 0, 5, 5);
        gbc_versionText.fill = GridBagConstraints.HORIZONTAL;
        gbc_versionText.gridx = 1;
        gbc_versionText.gridy = 2;
        getContentPane().add(versionText, gbc_versionText);
        versionText.setColumns(10);
        versionText.getDocument().addDocumentListener(inputListener);
        JLabel label_2 = new JLabel("</version>");
        GridBagConstraints gbc_label_2 = new GridBagConstraints();
        gbc_label_2.insets = new Insets(0, 0, 5, 10);
        gbc_label_2.gridx = 2;
        gbc_label_2.gridy = 2;
        getContentPane().add(label_2, gbc_label_2);

        JPanel panel_1 = new JPanel();
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.gridwidth = 3;
        gbc_panel_1.insets = new Insets(0, 0, 5, 5);
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 0;
        gbc_panel_1.gridy = 3;
        getContentPane().add(panel_1, gbc_panel_1);

        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(5, 5, 10, 10);
        gbc_panel.anchor = GridBagConstraints.EAST;
        gbc_panel.gridwidth = 3;
        gbc_panel.fill = GridBagConstraints.VERTICAL;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 4;
        getContentPane().add(panel, gbc_panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 0, 0, 0 };
        gbl_panel.rowHeights = new int[] { 0, 0 };
        gbl_panel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
        panel.setLayout(gbl_panel);

        JButton btnExit = new JButton("Cancel");
        GridBagConstraints gbc_btnExit = new GridBagConstraints();
        gbc_btnExit.insets = new Insets(0, 0, 0, 5);
        gbc_btnExit.gridx = 0;
        gbc_btnExit.gridy = 0;
        panel.add(btnExit, gbc_btnExit);

        btnOk = new JButton("   OK   ");
        btnOk.setEnabled(false);
        GridBagConstraints gbc_btnOk = new GridBagConstraints();
        gbc_btnOk.gridx = 1;
        gbc_btnOk.gridy = 0;
        panel.add(btnOk, gbc_btnOk);

        setLocationRelativeTo(parent);

        btnOk.addActionListener(e -> handleOk());
        btnExit.addActionListener(e -> handleExit());

        if (dependency == null) {
            setTitle("Add Dependency...");
        } else {
            setTitle("Edit Dependency...");

            groupText.setText(dependency.getGroupId());
            artifactText.setText(dependency.getArtifactId());
            versionText.setText(dependency.getVersion());

        }

    }

    protected void validateInputs() {

        boolean valid = true;

        if (groupText.getText().isEmpty() == true) {
            valid = false;
        }

        if (artifactText.getText().isEmpty() == true) {
            valid = false;
        }

        if (versionText.getText().isEmpty() == true) {
            valid = false;
        }

        btnOk.setEnabled(valid);

    }

    private void handleOk() {

        result = new ObservableDependency();
        result.setGroupId(groupText.getText());
        result.setArtifactId(artifactText.getText());
        result.setVersion(versionText.getText());

        setVisible(false);

    }

    public ObservableDependency getResult() {
        return result;
    }

    private void handleExit() {

        setVisible(false);

    }

}
