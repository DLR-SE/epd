package de.emir.tuml.ucore.runtime;

import de.emir.tuml.ucore.runtime.annotations.UMLInterface;
import de.emir.tuml.ucore.runtime.UMultiplicity;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UType;

/**
 * @generated
 */
@UMLInterface(name = "IStructuralElement")
public interface IStructuralElement extends UObject {

    /**
     * @generated
     */
    String getName();

    /**
     * @generated
     */
    String getDocumentation();

    /**
     * @generated
     */
    UType getType();

    /**
     * @generated
     */
    UMultiplicity getMultiplicity();

    /**
     * returns true, if the more than one element can be stored within this feature ( upperBound != 1)
     * 
     * @generated
     */
    boolean isMany();

    Object invoke(UObject instance, Object parameter);
}
