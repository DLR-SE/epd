package de.emir.tuml.ucore.runtime.utils;

import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;
import de.emir.tuml.ucore.runtime.utils.Pointer;

/**
 * The TypedPointer is dynamic pointer type, that search for the listIndex .th instance of a given type inside an
 * UObject (provided by the parent pointer). Thereby the result may change between two calls to getValue(), if in the
 * meantime a new instance has been added.
 * 
 * To find the concrete value, the TypePointer iterates (within each call) through all UStructuralFeatures and checks if
 * the result type inherits the specified type. If that is the case and the value is not null the index value is
 * decremented until the index is <= 0. if the index is <= 0 the result is returned.
 * 
 * With regard to list features the TypePointer iterates through all instances of the list (always decrement the index
 * if the type match) before going to the next feature.
 * 
 * @note the TypePointer does not iterate through operations, as they may have side effects
 * @generated
 */
@UMLClass
public interface TypePointer extends Pointer {
    /**
     * @generated
     */
    @UMLProperty(name = "parent", associationType = AssociationType.PROPERTY)
    public void setParent(Pointer _parent);

    /**
     * @generated
     */
    @UMLProperty(name = "parent", associationType = AssociationType.PROPERTY)
    public Pointer getParent();

    /**
     * @generated
     */
    @UMLProperty(name = "type", associationType = AssociationType.PROPERTY)
    public void setType(UType _type);

    /**
     * @generated
     */
    @UMLProperty(name = "type", associationType = AssociationType.PROPERTY)
    public UType getType();

    /**
     * @generated
     */
    @UMLProperty(name = "listIndex", associationType = AssociationType.PROPERTY)
    public void setListIndex(int _listIndex);

    /**
     * @generated
     */
    @UMLProperty(name = "listIndex", associationType = AssociationType.PROPERTY)
    public int getListIndex();

}
