package de.emir.tuml.ucore.runtime;

import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.tuml.ucore.runtime.UNamedElement;
import de.emir.tuml.ucore.runtime.UModelElement;
import java.util.List;

import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;
import de.emir.tuml.ucore.runtime.utils.QualifiedName;

/**
 * @generated
 */
@UMLClass(parent = UNamedElement.class)
public interface UPackage extends UNamedElement {

    /**
     * @generated
     */
    @UMLProperty(name = "content", associationType = AssociationType.COMPOSITE)
    public List<UModelElement> getContent();

    /**
     * 
     * creates a new sub package of this package
     * 
     * @note if the package allready exists (getSubpackage(name, true)) the existing package is returned
     * @generated
     */
    UPackage createPackage(final String name);

    /**
     * 
     * creates a new interface within this package
     * 
     * @precondition isFrozen() == false
     * @generated
     */
    UInterface createInterface(final String name);

    /**
     * 
     * creates a new class within this package
     * 
     * @precondition isFrozen() == false
     * @generated
     */
    UClass createClass(final String name);

    /**
     * 
     * creates a new enumeration in this package
     * 
     * @precondition isFrozen() == false
     * @generated
     */
    UEnum createEnumeration(final String name);

    /**
     * 
     * returns the subpackage with the given namen, if it exists
     * 
     * @param name
     *            the name of the subpackage
     * @param ownedOnly
     *            if set to true, only this package is checked for the subpackage, otherwise all owned subpackages
     *            (getContent().filter(typeof(UPackage))) will be checked too.
     * @generated
     */
    UPackage getSubpackage(final String name, final boolean ownedOnly);

    /**
     * @generated
     */
    UClassifier getClassifier(final String name, final boolean ownedOnly);

    /**
     * @generated
     */
    UClassifier getClassifier(final QualifiedName qn, final boolean ownedOnly);

}
