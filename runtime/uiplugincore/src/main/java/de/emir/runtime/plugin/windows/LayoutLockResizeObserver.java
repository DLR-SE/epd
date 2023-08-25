package de.emir.runtime.plugin.windows;

public class LayoutLockResizeObserver {

    private MainWindow mw;

    public LayoutLockResizeObserver(MainWindow mw) {
        this.mw = mw;
        setState();
        mw.getLayoutLockState().subscribeLockState(c -> setState());

    }

    public void setState() {

        mw.getMainControl().getContentArea().getCenter()
                .setResizingEnabled(mw.getLayoutLockState().isViewsLocked() == false);

    }

}
