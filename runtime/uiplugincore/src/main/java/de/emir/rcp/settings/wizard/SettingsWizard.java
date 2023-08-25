package de.emir.rcp.settings.wizard;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import de.emir.rcp.ui.wizard.AbstractWizard;
import de.emir.rcp.ui.wizard.AbstractWizardPage;

public class SettingsWizard extends AbstractWizard {

    public SettingsWizard(JFrame parent) {
        super(parent, "Settings");
    }

    @Override
    public List<AbstractWizardPage> getInitialPages() {
        List<AbstractWizardPage> pages = new ArrayList<>();

        pages.add(new SettingsWizardPage(this));
        return pages;
    }

    @Override
    public void onFinish() {
        close();
    }

}
