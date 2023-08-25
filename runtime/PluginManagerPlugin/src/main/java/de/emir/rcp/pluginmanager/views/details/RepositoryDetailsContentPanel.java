package de.emir.rcp.pluginmanager.views.details;

import java.awt.Component;
import java.awt.Font;
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
import de.emir.rcp.pluginmanager.views.PluginRenderUtil;
import de.emir.rcp.ui.widgets.AbstractDetailsContentPanel;
import de.emir.tuml.runtime.epf.ObservableRepository;
import io.reactivex.rxjava3.disposables.Disposable;

public class RepositoryDetailsContentPanel extends AbstractDetailsContentPanel<ObservableRepository> {
    private JTextField idText;
    private JTextField urlText;
    private JTextField userNameText;
    private JTextField passwordText;

    private List<Disposable> subscriptions = new ArrayList<>();

    public RepositoryDetailsContentPanel(ObservableRepository o) {
        super(o);

    }

    @Override
    public String getTitle() {
        return getObject().getId();
    }

    @Override
    public Icon getIcon() {
        return PluginRenderUtil.REPO_ICON;
    }

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public Component createContents() {

        JPanel p = new JPanel();
        GridBagLayout gbl_p = new GridBagLayout();
        gbl_p.columnWidths = new int[] { 0, 0, 0 };
        gbl_p.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gbl_p.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
        gbl_p.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        p.setLayout(gbl_p);

        JLabel lblGeneral = new JLabel("General");
        lblGeneral.setFont(new Font("Tahoma", Font.BOLD, 11));
        GridBagConstraints gbc_lblGeneral = new GridBagConstraints();
        gbc_lblGeneral.gridwidth = 2;
        gbc_lblGeneral.anchor = GridBagConstraints.WEST;
        gbc_lblGeneral.insets = new Insets(0, 15, 5, 0);
        gbc_lblGeneral.gridx = 0;
        gbc_lblGeneral.gridy = 0;
        p.add(lblGeneral, gbc_lblGeneral);

        JLabel lblId = new JLabel("ID:");
        GridBagConstraints gbc_lblId = new GridBagConstraints();
        gbc_lblId.anchor = GridBagConstraints.EAST;
        gbc_lblId.insets = new Insets(0, 5, 5, 5);
        gbc_lblId.gridx = 0;
        gbc_lblId.gridy = 1;
        p.add(lblId, gbc_lblId);

        idText = new JTextField();
        idText.setEditable(false);
        GridBagConstraints gbc_idText = new GridBagConstraints();
        gbc_idText.insets = new Insets(0, 0, 5, 15);
        gbc_idText.fill = GridBagConstraints.HORIZONTAL;
        gbc_idText.gridx = 1;
        gbc_idText.gridy = 1;
        p.add(idText, gbc_idText);
        idText.setColumns(10);

        JLabel lblUrl = new JLabel("URL:");
        GridBagConstraints gbc_lblUrl = new GridBagConstraints();
        gbc_lblUrl.anchor = GridBagConstraints.EAST;
        gbc_lblUrl.insets = new Insets(0, 5, 5, 5);
        gbc_lblUrl.gridx = 0;
        gbc_lblUrl.gridy = 2;
        p.add(lblUrl, gbc_lblUrl);

        urlText = new JTextField();
        urlText.setEditable(false);
        GridBagConstraints gbc_urlText = new GridBagConstraints();
        gbc_urlText.insets = new Insets(0, 0, 5, 15);
        gbc_urlText.fill = GridBagConstraints.HORIZONTAL;
        gbc_urlText.gridx = 1;
        gbc_urlText.gridy = 2;
        p.add(urlText, gbc_urlText);
        urlText.setColumns(10);

        JLabel lblAuthentication = new JLabel("Authentication");
        lblAuthentication.setFont(new Font("Tahoma", Font.BOLD, 11));
        GridBagConstraints gbc_lblAuthentication = new GridBagConstraints();
        gbc_lblAuthentication.gridwidth = 2;
        gbc_lblAuthentication.anchor = GridBagConstraints.WEST;
        gbc_lblAuthentication.insets = new Insets(0, 15, 5, 0);
        gbc_lblAuthentication.gridx = 0;
        gbc_lblAuthentication.gridy = 4;
        p.add(lblAuthentication, gbc_lblAuthentication);

        JLabel lblUsername = new JLabel("Username:");
        GridBagConstraints gbc_lblUsername = new GridBagConstraints();
        gbc_lblUsername.anchor = GridBagConstraints.EAST;
        gbc_lblUsername.insets = new Insets(0, 15, 5, 5);
        gbc_lblUsername.gridx = 0;
        gbc_lblUsername.gridy = 5;
        p.add(lblUsername, gbc_lblUsername);

        userNameText = new JTextField();
        userNameText.setEditable(false);
        GridBagConstraints gbc_userNameText = new GridBagConstraints();
        gbc_userNameText.insets = new Insets(0, 0, 5, 15);
        gbc_userNameText.fill = GridBagConstraints.HORIZONTAL;
        gbc_userNameText.gridx = 1;
        gbc_userNameText.gridy = 5;
        p.add(userNameText, gbc_userNameText);
        userNameText.setColumns(10);

        JLabel lblPassword = new JLabel("Password:");
        GridBagConstraints gbc_lblPassword = new GridBagConstraints();
        gbc_lblPassword.anchor = GridBagConstraints.EAST;
        gbc_lblPassword.insets = new Insets(0, 5, 5, 5);
        gbc_lblPassword.gridx = 0;
        gbc_lblPassword.gridy = 6;
        p.add(lblPassword, gbc_lblPassword);

        passwordText = new JTextField();
        passwordText.setEditable(false);
        GridBagConstraints gbc_passwordText = new GridBagConstraints();
        gbc_passwordText.insets = new Insets(0, 0, 5, 15);
        gbc_passwordText.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordText.gridx = 1;
        gbc_passwordText.gridy = 6;
        p.add(passwordText, gbc_passwordText);
        passwordText.setColumns(10);

        updateValues();

        JButton btnEdit = new JButton("Edit");
        GridBagConstraints gbc_btnEdit = new GridBagConstraints();
        gbc_btnEdit.insets = new Insets(0, 0, 0, 15);
        gbc_btnEdit.anchor = GridBagConstraints.EAST;
        gbc_btnEdit.gridx = 1;
        gbc_btnEdit.gridy = 7;

        btnEdit.setIcon(PluginRenderUtil.EDIT_ICON);

        p.add(btnEdit, gbc_btnEdit);

        btnEdit.addActionListener(new ExecuteCommandAction(PMBasics.EDIT_CMD_ID));

        return p;
    }

    private void updateValues() {
        ObservableRepository repo = getObject();
        userNameText.setText(repo.getUsername());
        passwordText.setText(repo.getPassword());
        idText.setText(repo.getId());
        urlText.setText(repo.getUrl());
    }

    @Override
    public void onOpen() {

        ObservableRepository repo = getObject();

        subscriptions.add(repo.subscribeId(opt -> updateValues()));
        subscriptions.add(repo.subscribeUrl(opt -> updateValues()));
        subscriptions.add(repo.subscribeUsername(opt -> updateValues()));
        subscriptions.add(repo.subscribePassword(opt -> updateValues()));

    }

    @Override
    public void onClose() {

        for (Disposable d : subscriptions) {
            d.dispose();
        }

    }

}
