package de.emir.tuml.ucore.runtime;

import de.emir.tuml.ucore.runtime.UDirectionType;
import de.emir.tuml.ucore.runtime.UTypedElement;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 * @generated
 */
@UMLClass(isAbstract = true, parent = UTypedElement.class)
public interface UParameter extends UTypedElement {

    /**
     * @generated
     */
    @UMLProperty(name = "direction", associationType = AssociationType.PROPERTY)
    public void setDirection(UDirectionType _direction);

    /**
     * @generated
     */
    @UMLProperty(name = "direction", associationType = AssociationType.PROPERTY)
    public UDirectionType getDirection();

}
