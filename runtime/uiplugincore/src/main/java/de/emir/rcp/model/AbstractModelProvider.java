package de.emir.rcp.model;

import java.util.Optional;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public abstract class AbstractModelProvider {

    private Object lastEmittedModel;

    private BehaviorSubject<Optional<Object>> modelPublisher = BehaviorSubject
            .createDefault(Optional.ofNullable(getModel()));

    private ModelTransactionStack lastEmittedStack;

    private BehaviorSubject<Optional<ModelTransactionStack>> stackPublisher = BehaviorSubject
            .createDefault(Optional.ofNullable(getTransactionStack()));

    /**
     * Unique identifier of the model, may be the file, if the model is stored in a file
     * 
     * @return
     */
    public abstract String getModelIdentifier();

    /**
     * Returns the currently active model of the application. The model can be a fixed backend, but also the model of an
     * editor. This behavior is determined by the ModelProvider.
     * 
     * @return
     */
    public Object getModel() {
        return lastEmittedModel;
    }

    /**
     * Sets the currently active model. Subclasses should call this method to propagate this information
     * 
     * @param model
     */
    protected void setModel(Object model) {
        lastEmittedModel = model;
        modelPublisher.onNext(Optional.ofNullable(model));
    }

    /**
     * Returns the transaction stack of the currently active model. Use this Stack to execute undoable transactions on
     * the model
     * 
     * @return
     */
    public ModelTransactionStack getTransactionStack() {
        return lastEmittedStack;
    }

    /**
     * Sets the currently active transaction stack. Subclasses should call this method to propagate this information
     * 
     * @param stack
     */
    protected void setTransactionStack(ModelTransactionStack stack) {
        lastEmittedStack = stack;
        stackPublisher.onNext(Optional.ofNullable(stack));
    }

    /**
     * Subscribes to changes OF the currently active model
     * 
     * @param obs
     * @return
     */
    public Disposable subscribeModel(Consumer<Optional<Object>> obs) {
        return modelPublisher.subscribe(obs);
    }

    /**
     * Subscribe to changes OF the currently active transaction stack
     * 
     * @param obs
     */
    public Disposable subscribeTransactionStack(Consumer<Optional<ModelTransactionStack>> obs) {
        return stackPublisher.subscribe(obs);
    }

    /**
     * Called by components if they have changed the source of the current model. e.g. if an editor model is provided,
     * the editor should reload his model and this "new" model should be passed to "setModel()"
     */
    public abstract void reloadActiveModel();

}
