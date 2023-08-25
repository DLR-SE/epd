package de.emir.tuml.ucore.runtime;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 * @generated
 */
@UMLClass
public interface UMultiplicity extends UObject {

    /**
     * @generated
     */
    @UMLProperty(name = "lower", associationType = AssociationType.PROPERTY)
    public void setLower(int _lower);

    /**
     * @generated
     */
    @UMLProperty(name = "lower", associationType = AssociationType.PROPERTY)
    public int getLower();

    /**
     * @generated
     */
    @UMLProperty(name = "upper", associationType = AssociationType.PROPERTY)
    public void setUpper(int _upper);

    /**
     * @generated
     */
    @UMLProperty(name = "upper", associationType = AssociationType.PROPERTY)
    public int getUpper();

}
