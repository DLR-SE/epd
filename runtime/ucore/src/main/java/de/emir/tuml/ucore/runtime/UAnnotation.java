package de.emir.tuml.ucore.runtime;

import java.util.List;

import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 * @generated
 */
@UMLClass
public interface UAnnotation extends UObject {

    /**
     * @generated
     */
    @UMLProperty(name = "details", associationType = AssociationType.COMPOSITE)
    public List<UAnnotationDetail> getDetails();

    /**
     * @generated
     */
    @UMLProperty(name = "name", associationType = AssociationType.PROPERTY, lowerBound = 1)
    public void setName(String _name);

    /**
     * @generated
     */
    @UMLProperty(name = "name", associationType = AssociationType.PROPERTY, lowerBound = 1)
    public String getName();

    /**
     * @generated
     */
    UAnnotationDetail getDetail(final String key);

    /**
     * @generated
     */
    void addDetail(final String key, final String value);

}
