package de.emir.tuml.ucore.runtime;

import de.emir.tuml.ucore.runtime.UMultiplicity;
import de.emir.tuml.ucore.runtime.UNamedElement;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 * @generated
 */
@UMLClass(isAbstract = true, parent = UNamedElement.class)
public interface UTypedElement extends UNamedElement {

    /**
     * @generated
     */
    @UMLProperty(name = "type", associationType = AssociationType.SHARED)
    public void setType(UType _type);

    /**
     * @generated
     */
    @UMLProperty(name = "type", associationType = AssociationType.SHARED)
    public UType getType();

    /**
     * @generated
     */
    @UMLProperty(name = "multiplicity", associationType = AssociationType.COMPOSITE)
    public void setMultiplicity(UMultiplicity _multiplicity);

    /**
     * @generated
     */
    @UMLProperty(name = "multiplicity", associationType = AssociationType.COMPOSITE)
    public UMultiplicity getMultiplicity();

    /**
     * returns true, if the type (getType()) references to an UPrimitiveType
     * 
     * @generated
     */
    boolean isAttribute();

    /**
     * returns true if the type is a complex type, e.g. isAttribute() == false
     * 
     * @generated
     */
    boolean isReference();

    /**
     * returns true, if the more than one element can be stored within this feature ( upperBound != 1)
     * 
     * @generated
     */
    boolean isMany();

}
