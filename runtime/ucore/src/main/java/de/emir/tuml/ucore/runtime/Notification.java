package de.emir.tuml.ucore.runtime;

import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 * @generated not
 */
@UMLClass
public interface Notification<T> extends UObject {

    /**
     * @generated
     */
    @UMLProperty(name = "instance", associationType = AssociationType.SHARED)
    public void setInstance(UObject _instance);

    /**
     * @generated
     */
    @UMLProperty(name = "instance", associationType = AssociationType.SHARED)
    public UObject getInstance();

    /**
     * @generated
     */
    @UMLProperty(name = "feature", associationType = AssociationType.SHARED)
    public void setFeature(UStructuralFeature _feature);

    /**
     * @generated
     */
    @UMLProperty(name = "feature", associationType = AssociationType.SHARED)
    public UStructuralFeature getFeature();

    /**
     * @generated
     */
    @UMLProperty(name = "type", associationType = AssociationType.PROPERTY, lowerBound = 1)
    public void setType(NotificationType _type);

    /**
     * @generated
     */
    @UMLProperty(name = "type", associationType = AssociationType.PROPERTY, lowerBound = 1)
    public NotificationType getType();

    public T getNewValue();

    public T getOldValue();

}
