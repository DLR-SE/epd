package de.emir.tuml.ucore.runtime.utils;

import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;
import de.emir.tuml.ucore.runtime.utils.FeaturePointer;
import de.emir.tuml.ucore.runtime.utils.Pointer;

/**
 * @generated
 */
@UMLClass(parent = FeaturePointer.class)
public interface PointerChain extends FeaturePointer {

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

}
