package de.emir.tuml.ucore.runtime.utils;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.utils.QualifiedName;

/**
 * 
 * creates a qualified name for an UObject therefore the QualifiedNameProvider checks the objects's class if it contains
 * a name feature
 * 
 * @generated
 */
@UMLClass
public interface QualifiedNameProvider extends UObject {

    /**
     * creates a qualified name for the given object, by appending the value of the "name" feature of the object itself
     * and all of its parents
     * 
     * @generated
     */
    QualifiedName get(final UObject obj);

    /**
     * 
     * creates a qualified name for the given object, that has a limited length
     * 
     * @generated
     */
    QualifiedName get(final UObject obj, final int depth);

}
