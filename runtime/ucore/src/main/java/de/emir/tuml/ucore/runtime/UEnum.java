package de.emir.tuml.ucore.runtime;

import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UEnumerator;
import java.util.List;

import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 * @generated
 */
@UMLClass(parent = UClassifier.class)
public interface UEnum extends UClassifier {

    /**
     * @generated
     */
    @UMLProperty(name = "literals", associationType = AssociationType.COMPOSITE)
    public List<UEnumerator> getLiterals();

    /**
     * 
     * creates a new Literal
     * 
     * @precondition isFrozen() == false
     * @generated
     */
    UEnumerator createLiteral(final String name, final int value);

    /**
     * @generated
     */
    Object createNewInstance(final String name);

    /**
     * @generated
     */
    Object createNewInstance(final int value);

    /**
     * @generated
     */
    Object createNewInstance(final UEnumerator enumerator);

    /**
     * @generated
     */
    UEnumerator getEnumerator(final String name);

    /**
     * @generated
     */
    UEnumerator getEnumerator(final int value);

    public UEnumerator getLiteral(String name);

}
