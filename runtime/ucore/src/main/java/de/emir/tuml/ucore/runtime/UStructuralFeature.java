package de.emir.tuml.ucore.runtime;

import de.emir.tuml.ucore.runtime.IStructuralElement;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.UTypedElement;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 * @generated
 */
@UMLClass(parent = UTypedElement.class)
public interface UStructuralFeature extends UTypedElement, IStructuralElement {

    /**
     * @generated
     */
    @UMLProperty(name = "aggregation", associationType = AssociationType.PROPERTY)
    public void setAggregation(UAssociationType _aggregation);

    /**
     * @generated
     */
    @UMLProperty(name = "aggregation", associationType = AssociationType.PROPERTY)
    public UAssociationType getAggregation();

    /**
     * @generated
     */
    UClass getOwner();

    /**
     * 
     * returns true if the feature can not be written, though the reflection system
     * 
     * @note a feature may be read only even if a setter is implemented within the implementation class, but not
     *       definied within the interface
     * @generated
     */
    boolean isReadOnly();

    public void set(UObject instance, final Object _value);

    public Object get(UObject instance);

    public void setReadOnly(boolean b);

}
