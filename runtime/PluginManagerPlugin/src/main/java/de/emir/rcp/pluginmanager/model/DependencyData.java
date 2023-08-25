package de.emir.rcp.pluginmanager.model;

import de.emir.tuml.runtime.epf.ObservableDependency;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class DependencyData {

    private ObservableDependency dependency;
    private DependencyState state = DependencyState.UNKNOWN;

    private PublishSubject<DependencyState> stateSubject = PublishSubject.create();

    public DependencyData(ObservableDependency dependency) {
        this.dependency = dependency;
    }

    public ObservableDependency getDependency() {
        return dependency;
    }

    public DependencyState getState() {
        return state;
    }

    public void setState(DependencyState state) {
        this.state = state;
        stateSubject.onNext(state);
    }

    public Disposable subscribeState(Consumer<DependencyState> c) {
        return stateSubject.subscribe(c);
    }

}
