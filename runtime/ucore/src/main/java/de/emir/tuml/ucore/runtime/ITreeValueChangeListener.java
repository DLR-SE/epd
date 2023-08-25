package de.emir.tuml.ucore.runtime;

/**
 * An ITreeValueChangeListener is used to observe the whole subtree of an UObject, e.g. every attribute within the
 * object fires an notification
 * 
 * @author sschweigert
 *
 */
public interface ITreeValueChangeListener {

    public void onValueChange(Notification<Object> notification);
}
