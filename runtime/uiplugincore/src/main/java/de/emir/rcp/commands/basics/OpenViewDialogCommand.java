package de.emir.rcp.commands.basics;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.views.OpenViewDialog;
import de.emir.runtime.plugin.windows.MainWindow;

/**
 * This command opens the view dialog used to open a view
 * 
 * @author fklein
 *
 */
public class OpenViewDialogCommand extends AbstractCommand {

    @Override
    public void execute() {
        MainWindow mw = PlatformUtil.getWindowManager().getMainWindow();

        OpenViewDialog dialog = new OpenViewDialog(mw);
        dialog.setVisible(true);

    }

}
