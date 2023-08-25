package de.emir.rcp.pluginmanager.statusbar;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JToolBar;

import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.pluginmanager.ids.PMBasics;
import de.emir.rcp.statusbar.AbstractStatusBarElement;

public class PluginManagerStatusBarElement extends AbstractStatusBarElement {

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public Component createContents() {
        JPanel p = new JPanel();
        GridBagLayout gbl_p = new GridBagLayout();
        gbl_p.columnWidths = new int[] { 0, 0 };
        gbl_p.rowHeights = new int[] { 0, 0 };
        gbl_p.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
        gbl_p.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
        p.setLayout(gbl_p);

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        GridBagConstraints gbc_toolBar = new GridBagConstraints();
        gbc_toolBar.gridx = 0;
        gbc_toolBar.gridy = 0;
        p.add(toolBar, gbc_toolBar);

        PlatformUtil.getMenuManager().fillToolbar(toolBar, PMBasics.STATUS_BAR_TOOLBAR_ID);

        return p;
    }

}
