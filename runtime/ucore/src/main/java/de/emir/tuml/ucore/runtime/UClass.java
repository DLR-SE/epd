package de.emir.tuml.ucore.runtime;

import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import java.util.List;

import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 * @generated
 */
@UMLClass(parent = UClassifier.class)
public interface UClass extends UClassifier {

    /**
     * @generated
     */
    @UMLProperty(name = "superType", associationType = AssociationType.SHARED)
    public UClassifier getSuperType();

    /**
     * @generated
     */
    @UMLProperty(name = "structuralFeatures", associationType = AssociationType.COMPOSITE)
    public List<UStructuralFeature> getStructuralFeatures();

    /**
     * @generated
     */
    @UMLProperty(name = "superType", associationType = AssociationType.SHARED)
    public void setSuperType(UClassifier _superType);

    /**
     * @generated
     */
    @UMLProperty(name = "abstract", associationType = AssociationType.PROPERTY)
    public void setAbstract(boolean _abstract);

    /**
     * @generated
     */
    @UMLProperty(name = "abstract", associationType = AssociationType.PROPERTY)
    public boolean getAbstract();

    /**
     * 
     * creates a new StructuralFeature for this class
     * 
     * @precondition isFrozen() == false
     * @generated
     */
    UStructuralFeature createFeature(final String name);

    /**
     * 
     * Creates a new instance of the corresponding type
     * 
     * @generated
     */
    UObject createNewInstance();

    boolean isStruct();

}
