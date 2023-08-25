package de.emir.rcp.settings.basics;

import java.awt.Component;

import javax.swing.JPanel;

import de.emir.rcp.settings.AbstractSettingsPage;

public class EmptySettingsPage extends AbstractSettingsPage {

    @Override
    public Component fillPage() {
        return new JPanel();

    }

    @Override
    public boolean isDirty() {
        return false;
    }

    @Override
    public void finish() {

    }

}
