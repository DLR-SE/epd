package de.emir.rcp.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolTip;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.commands.ep.CommandDescriptor;
import de.emir.rcp.manager.CommandManager;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.resources.IconManager;

public class CustomDevTooltip extends JToolTip {

    private static final long serialVersionUID = 608753796709093396L;
    private JPanel m_panel;
    private JLabel lblTooltiplabel;
    private JLabel lblDevTools;
    private JLabel iconLabel;
    private JLabel lblPluginName;
    private JLabel lblFullpath;
    private JLabel lblProvidedBy;
    private JPanel panel;
    private JLabel lblMenuPath;
    private JLabel lblCommand;
    private JLabel lblCommandlabel;
    private JLabel lblDescription;
    private JSeparator separator;

    public CustomDevTooltip(String fullPath, AbstractUIPlugin provider) {
        this(fullPath, provider, null);

    }

    /**
     * @wbp.parser.constructor
     */
    public CustomDevTooltip(String fullPath, AbstractUIPlugin provider, AbstractCommand cmd) {
        super();

        m_panel = new JPanel();
        GridBagLayout gbl_m_panel = new GridBagLayout();
        gbl_m_panel.columnWeights = new double[] { 1.0 };
        gbl_m_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
        m_panel.setLayout(gbl_m_panel);
        setLayout(new BorderLayout());
        add(m_panel);

        lblTooltiplabel = new JLabel("");
        GridBagConstraints gbc_lblTooltiplabel = new GridBagConstraints();
        gbc_lblTooltiplabel.anchor = GridBagConstraints.NORTHWEST;
        gbc_lblTooltiplabel.insets = new Insets(5, 5, 5, 0);
        gbc_lblTooltiplabel.gridx = 0;
        gbc_lblTooltiplabel.gridy = 0;
        m_panel.add(lblTooltiplabel, gbc_lblTooltiplabel);

        separator = new JSeparator();
        GridBagConstraints gbc_separator = new GridBagConstraints();
        gbc_separator.fill = GridBagConstraints.HORIZONTAL;
        gbc_separator.insets = new Insets(0, 0, 5, 0);
        gbc_separator.gridx = 0;
        gbc_separator.gridy = 1;
        m_panel.add(separator, gbc_separator);

        lblDevTools = new JLabel("Dev Tools");
        lblDevTools.setFont(new Font("Tahoma", Font.BOLD, 11));
        GridBagConstraints gbc_lblDevTools = new GridBagConstraints();
        gbc_lblDevTools.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblDevTools.insets = new Insets(0, 5, 5, 5);
        gbc_lblDevTools.gridx = 0;
        gbc_lblDevTools.gridy = 2;
        m_panel.add(lblDevTools, gbc_lblDevTools);

        lblProvidedBy = new JLabel("Provided by:");
        GridBagConstraints gbc_lblProvidedBy = new GridBagConstraints();
        gbc_lblProvidedBy.anchor = GridBagConstraints.WEST;
        gbc_lblProvidedBy.insets = new Insets(0, 5, 5, 5);
        gbc_lblProvidedBy.gridx = 0;
        gbc_lblProvidedBy.gridy = 3;
        m_panel.add(lblProvidedBy, gbc_lblProvidedBy);

        String providerName = "";

        if (provider != null) {
            providerName = provider.getClass().getSimpleName();
        }

        panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 10, 5, 5);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 4;
        m_panel.add(panel, gbc_panel);
        GridBagLayout gbl_panel = new GridBagLayout();

        gbl_panel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[] { 0.0 };
        panel.setLayout(gbl_panel);

        iconLabel = new JLabel("");
        GridBagConstraints gbc_iconLabel = new GridBagConstraints();
        gbc_iconLabel.insets = new Insets(0, 0, 5, 5);
        gbc_iconLabel.gridx = 0;
        gbc_iconLabel.gridy = 0;
        panel.add(iconLabel, gbc_iconLabel);

        iconLabel.setIcon(IconManager.getIcon(this, "icons/emiricons/32/custom_extension.png",
                IconManager.preferedSmallIconSize()));

        lblPluginName = new JLabel(providerName);
        GridBagConstraints gbc_lblPluginName = new GridBagConstraints();
        gbc_lblPluginName.anchor = GridBagConstraints.WEST;
        gbc_lblPluginName.insets = new Insets(0, 0, 5, 5);
        gbc_lblPluginName.gridx = 1;
        gbc_lblPluginName.gridy = 0;
        panel.add(lblPluginName, gbc_lblPluginName);

        lblMenuPath = new JLabel("Menu Path:");
        GridBagConstraints gbc_lblMenuPath = new GridBagConstraints();
        gbc_lblMenuPath.anchor = GridBagConstraints.WEST;
        gbc_lblMenuPath.insets = new Insets(0, 5, 5, 5);
        gbc_lblMenuPath.gridx = 0;
        gbc_lblMenuPath.gridy = 5;
        m_panel.add(lblMenuPath, gbc_lblMenuPath);

        lblFullpath = new JLabel(fullPath);
        GridBagConstraints gbc_lblFullpath = new GridBagConstraints();
        gbc_lblFullpath.anchor = GridBagConstraints.WEST;
        gbc_lblFullpath.insets = new Insets(0, 10, 5, 5);
        gbc_lblFullpath.gridx = 0;
        gbc_lblFullpath.gridy = 6;
        m_panel.add(lblFullpath, gbc_lblFullpath);

        if (cmd == null) {
            return;
        }

        CommandManager mgr = ServiceManager.get(CommandManager.class);
        CommandDescriptor cmdDescriptor = mgr.getCommandDescriptor(cmd);

        if (cmdDescriptor == null) {
            return;
        }

        lblCommand = new JLabel("Command:");
        GridBagConstraints gbc_lblCommand = new GridBagConstraints();
        gbc_lblCommand.anchor = GridBagConstraints.WEST;
        gbc_lblCommand.insets = new Insets(0, 5, 5, 5);
        gbc_lblCommand.gridx = 0;
        gbc_lblCommand.gridy = 7;
        m_panel.add(lblCommand, gbc_lblCommand);

        lblCommandlabel = new JLabel(cmdDescriptor.getId());
        GridBagConstraints gbc_lblCommandlabel = new GridBagConstraints();
        gbc_lblCommandlabel.anchor = GridBagConstraints.WEST;
        gbc_lblCommandlabel.insets = new Insets(0, 10, 10, 5);
        gbc_lblCommandlabel.gridx = 0;
        gbc_lblCommandlabel.gridy = 8;
        m_panel.add(lblCommandlabel, gbc_lblCommandlabel);

    }

    @Override
    public Dimension getPreferredSize() {
        if (getLayout() != null) {
            return getLayout().preferredLayoutSize(this);
        }
        return super.getPreferredSize();
    }

    @Override
    public void setTipText(String tipText) {
        lblTooltiplabel.setText(tipText);
        m_panel.setSize(getPreferredSize());
    }
}
