package de.emir.rcp.menu;

import java.util.List;

import javax.swing.JMenuItem;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;

public abstract class AbstractDynamicMenuProvider {

    private boolean dirty;

    private PublishSubject<Boolean> dirtySubject = PublishSubject.create();

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
        dirtySubject.onNext(dirty);
    }

    public boolean isDirty() {
        return dirty;
    }

    public Disposable subscribeIsDirty(Consumer<Boolean> c) {
        return dirtySubject.subscribe(c);
    }

    public abstract List<JMenuItem> populate();

}
