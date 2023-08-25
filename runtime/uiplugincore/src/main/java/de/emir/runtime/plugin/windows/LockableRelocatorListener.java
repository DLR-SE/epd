package de.emir.runtime.plugin.windows;

import bibliothek.gui.dock.control.relocator.DockRelocatorEvent;
import bibliothek.gui.dock.control.relocator.VetoableDockRelocatorListener;

public class LockableRelocatorListener implements VetoableDockRelocatorListener {

    private MainWindow mw;

    public LockableRelocatorListener(MainWindow mw) {
        this.mw = mw;
    }

    @Override
    public void grabbing(DockRelocatorEvent event) {

        LayoutLockState lls = mw.getLayoutLockState();

        if (lls.isViewsLocked() == true) {
            event.cancel();
        }

    }

    @Override
    public void grabbed(DockRelocatorEvent event) {
        // TODO
    }

    @Override
    public void searched(DockRelocatorEvent event) {
        // TODO
    }

    @Override
    public void dropping(DockRelocatorEvent event) {
        // TODO
    }

    @Override
    public void dragging(DockRelocatorEvent event) {
        // TODO
    }

    @Override
    public void dragged(DockRelocatorEvent event) {
        // TODO
    }

    @Override
    public void dropped(DockRelocatorEvent event) {
        // TODO
    }

    @Override
    public void canceled(DockRelocatorEvent event) {
        // TODO
    }

}
