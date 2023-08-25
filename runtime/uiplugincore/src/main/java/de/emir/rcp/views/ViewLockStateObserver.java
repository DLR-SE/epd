package de.emir.rcp.views;

import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.runtime.plugin.windows.LayoutLockState;

public class ViewLockStateObserver {

    private AbstractView view;

    public ViewLockStateObserver(AbstractView view) {
        this.view = view;

        PlatformUtil.getWindowManager().getMainWindow().getLayoutLockState().subscribeLockState(c -> setState());

        setState();

    }

    public void setState() {

        LayoutLockState lls = PlatformUtil.getWindowManager().getMainWindow().getLayoutLockState();
        view.setTitleShown(lls.isViewsLocked() == false);
    }

}
