package de.emir.rcp.commands.basics;

import javax.swing.JFrame;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.settings.wizard.SettingsWizard;

public class OpenSettingsDialogCommand extends AbstractCommand {

    @Override
    public void execute() {

        JFrame mw = PlatformUtil.getWindowManager().getMainWindow();

        SettingsWizard sw = new SettingsWizard(mw);

        sw.initialize();

    }

}
