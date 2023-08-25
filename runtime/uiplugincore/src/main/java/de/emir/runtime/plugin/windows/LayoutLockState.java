package de.emir.runtime.plugin.windows;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class LayoutLockState {

    private boolean viewsLocked = true;

    private PublishSubject<Boolean> viewsLockedSubject = PublishSubject.create();

    public LayoutLockState() {

    }

    public void setViewsLocked(boolean locked) {
        viewsLocked = locked;
        viewsLockedSubject.onNext(viewsLocked);
    }

    public boolean isViewsLocked() {
        return viewsLocked;
    }

    public Disposable subscribeLockState(Consumer<Boolean> c) {
        return viewsLockedSubject.subscribe(c);
    }
}
