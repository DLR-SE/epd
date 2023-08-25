package de.emir.rcp.pluginmanager.views.details;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.emir.rcp.pluginmanager.views.PluginRenderUtil;
import de.emir.rcp.ui.widgets.AbstractDetailsContentPanel;

public class WorkspaceDescriptionContentPanel extends AbstractDetailsContentPanel<String> {

    public WorkspaceDescriptionContentPanel(String o) {
        super(o);

    }

    @Override
    public String getTitle() {
        return getObject();
    }

    @Override
    public Icon getIcon() {
        return PluginRenderUtil.WS_ICON;
    }

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public Component createContents() {
        JPanel p = new JPanel();
        GridBagLayout gbl_p = new GridBagLayout();
        gbl_p.columnWeights = new double[] { 1.0 };
        gbl_p.rowWeights = new double[] { 1.0 };
        p.setLayout(gbl_p);

        JLabel lblNewLabel = new JLabel(
                "<html>Workspaces are Maven projects discovered via a file path. <br> For example, plugins currently in development can be added.</html>");
        lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(20, 0, 0, 0);
        gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 0;
        p.add(lblNewLabel, gbc_lblNewLabel);

        return p;
    }

    @Override
    public void onOpen() {

    }

    @Override
    public void onClose() {

    }

}
