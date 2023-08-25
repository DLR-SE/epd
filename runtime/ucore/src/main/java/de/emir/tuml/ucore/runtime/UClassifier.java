package de.emir.tuml.ucore.runtime;

import java.util.List;

import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 * @generated
 */
@UMLClass(isAbstract = true, parent = UType.class)
public interface UClassifier extends UType {

    /**
     * @generated
     */
    @UMLProperty(name = "interfaces", associationType = AssociationType.SHARED)
    public List<UClassifier> getInterfaces();

    /**
     * @generated
     */
    @UMLProperty(name = "operations", associationType = AssociationType.COMPOSITE)
    public List<UOperation> getOperations();

    /**
     * 
     * adds a new superType / parent type to this classifier
     * 
     * @precondition isFrozen() == false
     * @generated
     */
    void addParent(final UClassifier cl);

    /**
     * 
     * adds a new operation to this type
     * 
     * @precondition isFrozen() == false
     * @generated
     */
    UOperation createOperation(final String name);

    /**
     * returns all structural features of this classifier, this includes the owned features as well as all features of
     * its parents
     * 
     * @generated
     */
    List<UStructuralFeature> getAllStructuralFeatures();

    /**
     * 
     * returns all structural features, that have a primitive type (int, long, double, ...) as type; e.g.
     * feature.isAttribute() == true
     * 
     * @generated
     */
    List<UStructuralFeature> getAllAttributes();

    /**
     * 
     * returns all structural features that have a complex type; e.g. feature.isReference() == true
     * 
     * @generated
     */
    List<UStructuralFeature> getAllReferences();

    /**
     * 
     * returns structural features. (references)
     * 
     * @param type
     *            minimum filter, only features, with a more rigid association type will be added into the iterator,
     *            following the order: - COMPOSITION : only composite features - ASSOCIATION: association and
     *            composition - AGGREGATION: aggregation, association and compositon - PROPERTY: all complex features
     * @return
     * @generated
     */
    List<UStructuralFeature> getAllStructuralFeatures(final UAssociationType type);

    /**
     * @generated
     */
    UStructuralFeature getFeature(final String name);

    /**
     * @generated
     */
    UOperation getOperation(final String name);

    /**
     * @generated
     */
    List<UClassifier> getAllParents();

    public List<UOperation> getAllOperations();

    /**
     * Checks if the given feature is part of the getAllFeatures collection
     * 
     * @param feature
     * @return
     */
    default boolean hasFeature(UStructuralFeature feature) {
        return getAllStructuralFeatures().contains(feature);
    }
}
