package de.emir.tuml.ucore.runtime.utils;

import java.util.List;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 * @generated
 */
@UMLClass
public interface QualifiedName extends UObject, Comparable<QualifiedName> {

    /**
     * @generated
     */
    @UMLProperty(name = "segments", associationType = AssociationType.PROPERTY)
    public List<String> getSegments();

    /**
     * @generated
     */
    String toString(final String delimiter);

    /**
     * @generated
     */
    int numSegments();

    /**
     * @generated
     */
    QualifiedName removeSegmentsFromEnd(final int num);

    /**
     * @generated
     */
    void localRemoveSegmentsFromEnd(final int num);

    /**
     * @generated
     */
    QualifiedName removeSegmentsFromStart(final int num);

    /**
     * @generated
     */
    void localRemoveSegmentsFromStart(final int num);

    /**
     * @generated
     */
    QualifiedName appendFront(final String segment);

    /**
     * @generated
     */
    void localAppendFront(final String segment);

    /**
     * @generated
     */
    QualifiedName appendBack(final String segment);

    /**
     * @generated
     */
    void localAppendBack(final String segment);

    /**
     * @generated
     */
    String lastSegment();

    /**
     * @generated
     */
    String firstSegment();

    /**
     * @generated
     */
    String segment(final int idx);

    /**
     * @generated
     */
    boolean empty();

    /**
     * @generated
     */
    boolean isEmpty();

}
