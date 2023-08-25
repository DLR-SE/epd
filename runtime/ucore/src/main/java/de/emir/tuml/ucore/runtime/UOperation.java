package de.emir.tuml.ucore.runtime;

import de.emir.tuml.ucore.runtime.IStructuralElement;
import de.emir.tuml.ucore.runtime.UNamedElement;
import de.emir.tuml.ucore.runtime.UParameter;
import java.util.List;

import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 * @generated
 */
@UMLClass(parent = UNamedElement.class)
public interface UOperation extends UNamedElement, IStructuralElement {

    /**
     * @generated
     */
    @UMLProperty(name = "parameters", associationType = AssociationType.COMPOSITE)
    public List<UParameter> getParameters();

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
     * @generated
     */
    @UMLProperty(name = "const", associationType = AssociationType.PROPERTY)
    public void setConst(boolean _const);

    /**
     * @generated
     */
    @UMLProperty(name = "const", associationType = AssociationType.PROPERTY)
    public boolean getConst();

    /**
     * 
     * @return the first parameter that with directionKind == Return
     */
    public UParameter getReturnParameter();

}
