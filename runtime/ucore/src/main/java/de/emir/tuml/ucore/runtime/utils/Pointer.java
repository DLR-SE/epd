package de.emir.tuml.ucore.runtime.utils;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.annotations.UMLInterface;

/**
 * 
 * A abstract pointer to an element into an object tree. In contrast to pointer in C++ or other languages, this pointer
 * is not pointing to a specific object, but an position inside an object hierarchy, thus the underlying object may
 * change, even if the pointer remains the same.
 * 
 * @generated
 */
@UMLInterface
public interface Pointer extends UObject, Comparable<Pointer> {
    /**
     * @returns the object, this pointer currently points on or null if not valid
     * @generated
     */
    Object getValue();

    /**
     * sets the value of the pointed instance the setValue behavior depends on the kind of pointer - if the pointer is a
     * FeaturePointer: 
     * 	-- if the feature is a list and the listIndex is invalid (<0) the value (if not null) is added to the list 
     * 	-- if the feature is a list and the listIndex is valid, the value is set to the corresponding index in the list 
     * 	-- if the feature is not a list and the value is not null, the feature value will be set (corresponds to eSet) 
     * 	-- if the feature is not a list and the value is null, eUnSet is called. This not necessarily results in
     * 		setting the value to null, but to its default value - if the pointer is a ObjectPointer, the instance is changed
     * 
     * @generated
     */
    void setValue(final Object value);

    /**
     * 
     * Assign differs from setValue, in that case that the directly pointed instance is not changed, but all feature
     * values from incoming value will be copied to the pointed instance. Features that are not part of the pointed
     * instance, but can be found in the value, will be ignored Therefore this method may be used, if the incoming value
     * is a superclass of the pointed instance (e.g. pointer(Type: PhysicalObject)->assign(Type: Vessel))
     * 
     * @param copyContainments
     *            if set to true, the value of a containment feature is copied otherwise it is simply set.
     * @generated
     */
    void assign(final Object value, final boolean copyContainments);

    /**
     * @returns true if the pointer is currently pointing to an existing object
     * @generated
     */
    boolean isValid();

    /**
     * returns the type of the object, this pointer currently points on or null if not valid
     * 
     * @generated not
     */
    UType getType();

    /**
     * 
     * returns the expected type of the pointed object. This type may differ from <code>getType</code> in terms that it
     * may is more abstract.
     * 
     * @generated
     */
    UType getExpectedType();

    /** convinience method, that checks the <code>getValue</code> method, if it returns an UObject */
    UObject getUObject();

    /**
     * returns the container of the pointed object
     * 
     * @generated
     */
    UObject getPointedContainer();

    /**
     * returns the feature that is used to access the pointed value
     * 
     * @generated
     */
    UStructuralFeature getPointedFeature();

    /**
     * 
     * Changes the anchor of the pointer, e.g. the one and only fixed position within this pointer. <br>
     * - In case of a ObjectPointer, the instance is changed - In case of an FeaturePointer the instance is changed, but
     * feature and list index remain the same - In case of an PointerChain the very first parent pointer is changed,
     * that is not an PointerChain.
     * 
     * Use this method to realocate a pointer to another tree with the same structure as the old tree
     * 
     * @generated
     */
    void changeAnchor(final UObject root);

    /**
     * creates a copy of this pointer that points to the same instance
     * 
     * @generated
     */
    Pointer copy();

    /**
     * Convinience method that casts the object into the requested type
     * 
     * @return
     */
    <T> T get();
}
