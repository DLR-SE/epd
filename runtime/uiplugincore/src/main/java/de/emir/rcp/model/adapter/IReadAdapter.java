package de.emir.rcp.model.adapter;

import java.util.Optional;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

/**
 * An adapter that is used to read and provide model data
 * 
 * @author Florian
 *
 */
public interface IReadAdapter<T> extends IAdapter {

    public Disposable subscribeChanged(Consumer<Optional<T>> c);

}
