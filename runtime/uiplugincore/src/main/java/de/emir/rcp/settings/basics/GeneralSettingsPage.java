package de.emir.rcp.settings.basics;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.emir.rcp.settings.AbstractSettingsPage;

public class GeneralSettingsPage extends AbstractSettingsPage {

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public Component fillPage() {

        JPanel p = new JPanel();
        GridBagLayout gbl_p = new GridBagLayout();
        gbl_p.columnWidths = new int[] { 0, 0 };
        gbl_p.rowHeights = new int[] { 0, 0 };
        gbl_p.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
        gbl_p.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
        p.setLayout(gbl_p);

        JLabel lblSeeSubpagesFor = new JLabel("See subpages for settings");
        GridBagConstraints gbc_lblSeeSubpagesFor = new GridBagConstraints();
        gbc_lblSeeSubpagesFor.gridx = 0;
        gbc_lblSeeSubpagesFor.gridy = 0;
        p.add(lblSeeSubpagesFor, gbc_lblSeeSubpagesFor);
        return p;

    }

    @Override
    public boolean isDirty() {
        return false;
    }

    @Override
    public void finish() {

    }

}
