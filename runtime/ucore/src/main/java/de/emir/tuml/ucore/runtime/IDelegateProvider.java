package de.emir.tuml.ucore.runtime;

public interface IDelegateProvider<T> {

    public T provideDelegate(UClassifier cl, UObject obj);

}
