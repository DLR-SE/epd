package de.emir.tuml.ucore.runtime;

import de.emir.tuml.ucore.runtime.annotations.UMLInterface;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UObject;

/**
 * @generated not
 */
@UMLInterface
public interface IValueChangeListener<T> {
    /**
     * 
     * This method is called, when a feature has changed
     * 
     * @warn normally the new value has not been applied when this method is called. To get access to the new value, use
     *       notification.getNewValue()
     * @generated not
     */
    void onValueChange(final Notification<T> notification);

}
