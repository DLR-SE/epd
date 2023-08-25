package de.emir.epd.clock.view.settings;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;

import de.emir.rcp.settings.AbstractSettingsPage;

public class ClockSettingsPage extends AbstractSettingsPage {
    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public Component fillPage() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 0));

        return panel;
    }

    @Override
    public boolean isDirty() {
        return false;
    }

    @Override
    public void finish() {
    }

}
