package de.emir.rcp.commands;


import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.views.AboutDialog;

public class ShowAboutCommand extends AbstractCommand {
    private AboutDialog dialog;

    @Override
    public void execute() {
        if (dialog == null){
            dialog = PlatformUtil.getProductInfoManager().getAboutDialog();
            dialog.setModal(true);
        }

        if (dialog.isVisible() == false){
            dialog.setVisible(true);
        }
    }

}
