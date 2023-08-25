package de.emir.tuml.ucore.runtime;

import de.emir.tuml.ucore.runtime.UNamedElement;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;

/**
 * @generated
 */
@UMLClass(isAbstract = true, parent = UNamedElement.class)
public interface UType extends UNamedElement {
    /**
     * checks if the query type inherits from this type
     * 
     * @generated
     */
    boolean inherits(final UType query);

    public Class<?> getRepresentingClass();
}
