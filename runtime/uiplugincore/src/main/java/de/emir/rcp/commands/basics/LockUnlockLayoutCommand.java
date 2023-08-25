package de.emir.rcp.commands.basics;

import de.emir.rcp.commands.AbstractCheckableCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.runtime.plugin.windows.LayoutLockState;

/**
 * This command toggles the layout lock state of the application
 * 
 * @author fklein
 *
 */
public class LockUnlockLayoutCommand extends AbstractCheckableCommand {

    public LockUnlockLayoutCommand() {
        LayoutLockState lls = PlatformUtil.getWindowManager().getMainWindow().getLayoutLockState();
        lls.subscribeLockState(c -> {

            boolean isLocked = c.booleanValue();

            if (isChecked() == isLocked) {
                setChecked(!isLocked);
            }

        });
    }

    @Override
    public void execute() {

        LayoutLockState lls = PlatformUtil.getWindowManager().getMainWindow().getLayoutLockState();
        boolean isLocked = lls.isViewsLocked();
        lls.setViewsLocked(!isLocked);

    }

}
