package de.emir.tuml.ucore.runtime.utils;

import java.util.List;

import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.utils.QualifiedNameProvider;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 * extends the QualifiedNameProvider for the capability to use different features to get the name
 * 
 * @generated
 */
@UMLClass(parent = QualifiedNameProvider.class)
public interface QualifiedNameProviderExt extends QualifiedNameProvider {

    /**
     * the QualifiedNameProviderExt checks if the UObject contains one of the nameFeatures, and uses this to create the
     * name
     * 
     * @note if more than one feature is available, the first matching feature fires
     * @generated
     */
    @UMLProperty(name = "nameFeatures", associationType = AssociationType.COMPOSITE)
    public List<UStructuralFeature> getNameFeatures();

}
