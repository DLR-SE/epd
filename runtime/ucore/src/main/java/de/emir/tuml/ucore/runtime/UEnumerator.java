package de.emir.tuml.ucore.runtime;

import de.emir.tuml.ucore.runtime.UNamedElement;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 * @generated
 */
@UMLClass(parent = UNamedElement.class)
public interface UEnumerator extends UNamedElement {

    /**
     * @generated
     */
    @UMLProperty(name = "value", associationType = AssociationType.PROPERTY)
    public void setValue(int _value);

    /**
     * @generated
     */
    @UMLProperty(name = "value", associationType = AssociationType.PROPERTY)
    public int getValue();

}
