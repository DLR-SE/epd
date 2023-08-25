package de.emir.tuml.ucore.runtime;

import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 * @generated
 */
@UMLClass(parent = UType.class)
public interface UException extends UType {

    /**
     * @generated
     */
    @UMLProperty(name = "superType", associationType = AssociationType.SHARED)
    public void setSuperType(UException _superType);

    /**
     * @generated
     */
    @UMLProperty(name = "superType", associationType = AssociationType.SHARED)
    public UException getSuperType();

    /**
     * @generated
     */
    @UMLProperty(name = "standardMessage", associationType = AssociationType.PROPERTY)
    public void setStandardMessage(String _standardMessage);

    /**
     * @generated
     */
    @UMLProperty(name = "standardMessage", associationType = AssociationType.PROPERTY)
    public String getStandardMessage();

}
