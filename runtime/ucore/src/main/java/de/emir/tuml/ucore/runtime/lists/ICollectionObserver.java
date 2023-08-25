package de.emir.tuml.ucore.runtime.lists;

public interface ICollectionObserver<T> {

    public static enum CHANGE_TYPE {
        ADD, REMOVE, ADD_MANY, REMOVE_MANY, CLEAR
    }

    public void onCollectionChanged(Object source, CHANGE_TYPE type, Object oldValue, Object newValue);
}
