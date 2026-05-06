package de.emir.epd.targetview.panels;

import de.emir.rcp.settings.AbstractSettingsPage;

import javax.swing.*;
import java.awt.*;

public class TargetInfoPage extends AbstractSettingsPage {
    private JPanel infoPanel;

    @Override
    public Component fillPage() {
        infoPanel = new JPanel();

        JLabel lblCreateAndModify = new JLabel("Create and modify targets");
        infoPanel.add(lblCreateAndModify);
        return infoPanel;
    }

    @Override
    public boolean isDirty() {
        return false;
    }

    /**
     * Store the changes
     */
    @Override
    public void finish() {

    }
}
