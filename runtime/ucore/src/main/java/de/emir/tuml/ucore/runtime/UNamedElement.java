package de.emir.tuml.ucore.runtime;

import de.emir.tuml.ucore.runtime.UModelElement;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;
import de.emir.tuml.ucore.runtime.utils.QualifiedName;

/**
 * @generated
 */
@UMLClass(isAbstract = true, parent = UModelElement.class)
public interface UNamedElement extends UModelElement {

    /**
     * @generated
     */
    @UMLProperty(name = "name", associationType = AssociationType.PROPERTY)
    public void setName(String _name);

    /**
     * @generated
     */
    @UMLProperty(name = "name", associationType = AssociationType.PROPERTY)
    public String getName();

    /**
     * @generated
     */
    QualifiedName getQualifiedName();

}
