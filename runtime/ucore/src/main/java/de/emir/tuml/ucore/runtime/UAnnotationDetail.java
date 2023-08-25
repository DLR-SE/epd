package de.emir.tuml.ucore.runtime;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 * @generated
 */
@UMLClass
public interface UAnnotationDetail extends UObject {

    /**
     * @generated
     */
    @UMLProperty(name = "key", associationType = AssociationType.PROPERTY, lowerBound = 1)
    public void setKey(String _key);

    /**
     * @generated
     */
    @UMLProperty(name = "key", associationType = AssociationType.PROPERTY, lowerBound = 1)
    public String getKey();

    /**
     * @generated
     */
    @UMLProperty(name = "value", associationType = AssociationType.PROPERTY, lowerBound = 1)
    public void setValue(String _value);

    /**
     * @generated
     */
    @UMLProperty(name = "value", associationType = AssociationType.PROPERTY, lowerBound = 1)
    public String getValue();

}
