package de.emir.tuml.ucore.runtime.utils;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;
import de.emir.tuml.ucore.runtime.utils.Pointer;

/**
 * @generated not
 */
@UMLClass
public interface ObjectPointer extends Pointer {

    /**
     * @generated
     */
    @UMLProperty(name = "theInstance", associationType = AssociationType.PROPERTY)
    public void setTheInstance(UObject _theInstance);

    /**
     * @generated
     */
    @UMLProperty(name = "theInstance", associationType = AssociationType.PROPERTY)
    public UObject getTheInstance();

}
