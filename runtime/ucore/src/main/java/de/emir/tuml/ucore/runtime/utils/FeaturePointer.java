package de.emir.tuml.ucore.runtime.utils;

import de.emir.tuml.ucore.runtime.IStructuralElement;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;
import de.emir.tuml.ucore.runtime.utils.Pointer;

/**
 * @generated
 */
@UMLClass
public interface FeaturePointer extends Pointer {

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

    /**
     * @generated
     */
    @UMLProperty(name = "feature", associationType = AssociationType.SHARED)
    public void setFeature(IStructuralElement _feature);

    /**
     * @generated
     */
    @UMLProperty(name = "feature", associationType = AssociationType.SHARED)
    public IStructuralElement getFeature();

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
