package de.emir.rcp.pluginmanager.views.dialogs;

import java.awt.Dimension;
import java.awt.Font;
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
import de.emir.tuml.runtime.epf.ObservableRepository;

public class EditRepositoryDialog extends JDialog {

    private static final long serialVersionUID = -2315770391175489012L;

    private ObservableRepository result;
    private JButton btnOk;
    private JTextField idText;
    private JTextField urlText;
    private JTextField usernameText;
    private JTextField passwordText;

    public EditRepositoryDialog(Window parent) {
        this(parent, null);

    }

    /**
     * @param parent
     * @param repo
     * @wbp.parser.constructor
     */
    public EditRepositoryDialog(Window parent, ObservableRepository repo) {
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
        setMinimumSize(new Dimension(550, 230));

        GridBagLayout gridBagLayout = new GridBagLayout();

        gridBagLayout.columnWeights = new double[] { 0.0, 1.0 };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0 };
        getContentPane().setLayout(gridBagLayout);

        JLabel lblGeneral = new JLabel("General");
        lblGeneral.setFont(new Font("Tahoma", Font.BOLD, 11));
        GridBagConstraints gbc_lblGeneral = new GridBagConstraints();
        gbc_lblGeneral.anchor = GridBagConstraints.WEST;
        gbc_lblGeneral.gridwidth = 2;
        gbc_lblGeneral.insets = new Insets(5, 5, 5, 5);
        gbc_lblGeneral.gridx = 0;
        gbc_lblGeneral.gridy = 0;
        getContentPane().add(lblGeneral, gbc_lblGeneral);

        JLabel lblId = new JLabel("ID:*");
        GridBagConstraints gbc_lblId = new GridBagConstraints();
        gbc_lblId.anchor = GridBagConstraints.EAST;
        gbc_lblId.insets = new Insets(0, 10, 5, 5);
        gbc_lblId.gridx = 0;
        gbc_lblId.gridy = 1;
        getContentPane().add(lblId, gbc_lblId);

        idText = new JTextField();
        GridBagConstraints gbc_idText = new GridBagConstraints();
        gbc_idText.insets = new Insets(0, 0, 5, 10);
        gbc_idText.fill = GridBagConstraints.HORIZONTAL;
        gbc_idText.gridx = 1;
        gbc_idText.gridy = 1;
        getContentPane().add(idText, gbc_idText);
        idText.setColumns(10);
        idText.getDocument().addDocumentListener(inputListener);
        JLabel lblUrl = new JLabel("URL:*");
        GridBagConstraints gbc_lblUrl = new GridBagConstraints();
        gbc_lblUrl.anchor = GridBagConstraints.EAST;
        gbc_lblUrl.insets = new Insets(0, 10, 5, 5);
        gbc_lblUrl.gridx = 0;
        gbc_lblUrl.gridy = 2;
        getContentPane().add(lblUrl, gbc_lblUrl);

        urlText = new JTextField();
        GridBagConstraints gbc_urlText = new GridBagConstraints();
        gbc_urlText.insets = new Insets(0, 0, 5, 10);
        gbc_urlText.fill = GridBagConstraints.HORIZONTAL;
        gbc_urlText.gridx = 1;
        gbc_urlText.gridy = 2;
        getContentPane().add(urlText, gbc_urlText);
        urlText.setColumns(10);
        urlText.getDocument().addDocumentListener(inputListener);
        JLabel lblAuthentication = new JLabel("Authentication");
        lblAuthentication.setFont(new Font("Tahoma", Font.BOLD, 11));
        GridBagConstraints gbc_lblAuthentication = new GridBagConstraints();
        gbc_lblAuthentication.gridwidth = 2;
        gbc_lblAuthentication.anchor = GridBagConstraints.WEST;
        gbc_lblAuthentication.insets = new Insets(0, 5, 5, 0);
        gbc_lblAuthentication.gridx = 0;
        gbc_lblAuthentication.gridy = 3;
        getContentPane().add(lblAuthentication, gbc_lblAuthentication);

        JLabel lblUsername = new JLabel("Username:");
        GridBagConstraints gbc_lblUsername = new GridBagConstraints();
        gbc_lblUsername.anchor = GridBagConstraints.EAST;
        gbc_lblUsername.insets = new Insets(0, 10, 5, 5);
        gbc_lblUsername.gridx = 0;
        gbc_lblUsername.gridy = 4;
        getContentPane().add(lblUsername, gbc_lblUsername);

        usernameText = new JTextField();
        GridBagConstraints gbc_usernameText = new GridBagConstraints();
        gbc_usernameText.insets = new Insets(0, 0, 5, 10);
        gbc_usernameText.fill = GridBagConstraints.HORIZONTAL;
        gbc_usernameText.gridx = 1;
        gbc_usernameText.gridy = 4;
        getContentPane().add(usernameText, gbc_usernameText);
        usernameText.setColumns(10);

        JLabel lblPassword = new JLabel("Password:");
        GridBagConstraints gbc_lblPassword = new GridBagConstraints();
        gbc_lblPassword.anchor = GridBagConstraints.EAST;
        gbc_lblPassword.insets = new Insets(0, 10, 5, 5);
        gbc_lblPassword.gridx = 0;
        gbc_lblPassword.gridy = 5;
        getContentPane().add(lblPassword, gbc_lblPassword);

        passwordText = new JTextField();
        GridBagConstraints gbc_passwordText = new GridBagConstraints();
        gbc_passwordText.insets = new Insets(0, 0, 5, 10);
        gbc_passwordText.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordText.gridx = 1;
        gbc_passwordText.gridy = 5;
        getContentPane().add(passwordText, gbc_passwordText);
        passwordText.setColumns(10);

        JPanel panel_1 = new JPanel();
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.gridwidth = 2;
        gbc_panel_1.insets = new Insets(0, 0, 5, 0);
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 0;
        gbc_panel_1.gridy = 6;
        getContentPane().add(panel_1, gbc_panel_1);

        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(5, 5, 10, 10);
        gbc_panel.anchor = GridBagConstraints.EAST;
        gbc_panel.gridwidth = 2;
        gbc_panel.fill = GridBagConstraints.VERTICAL;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 7;
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

        setLocationRelativeTo(null);

        btnOk.addActionListener(e -> handleOk());
        btnExit.addActionListener(e -> handleExit());

        if (repo == null) {
            setTitle("Add Repository...");
        } else {
            setTitle("Edit Repository...");
            passwordText.setText(repo.getPassword());
            usernameText.setText(repo.getUsername());
            urlText.setText(repo.getUrl());
            idText.setText(repo.getId());
        }

    }

    protected void validateInputs() {

        boolean valid = true;

        if (idText.getText().isEmpty() == true) {
            valid = false;
        }

        if (urlText.getText().isEmpty() == true) {
            valid = false;
        }

        btnOk.setEnabled(valid);

    }

    private void handleOk() {

        result = new ObservableRepository(urlText.getText());
        result.setId(idText.getText());
        result.setUsername(usernameText.getText().isEmpty() ? null : usernameText.getText());
        result.setPassword(passwordText.getText().isEmpty() ? null : passwordText.getText());

        setVisible(false);

    }

    public ObservableRepository getResult() {
        return result;
    }

    private void handleExit() {

        setVisible(false);

    }

}
