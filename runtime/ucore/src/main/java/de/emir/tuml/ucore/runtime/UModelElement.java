package de.emir.tuml.ucore.runtime;

import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UAnnotationDetail;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UPackage;
import java.util.List;

import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 * @generated
 */
@UMLClass(isAbstract = true)
public interface UModelElement extends UObject {

    /**
     * @generated
     */
    @UMLProperty(name = "annotations", associationType = AssociationType.COMPOSITE)
    public List<UAnnotation> getAnnotations();

    /**
     * @generated
     */
    @UMLProperty(name = "package", associationType = AssociationType.SHARED)
    public void setPackage(UPackage _package);

    /**
     * @generated
     */
    @UMLProperty(name = "package", associationType = AssociationType.SHARED)
    public UPackage getPackage();

    /**
     * @generated
     */
    @UMLProperty(name = "documentation", associationType = AssociationType.PROPERTY)
    public void setDocumentation(String _documentation);

    /**
     * @generated
     */
    @UMLProperty(name = "documentation", associationType = AssociationType.PROPERTY)
    public String getDocumentation();

    /**
     * mark the meta data to be freezed, in this case, it can not be changed anymore
     * 
     * @generated
     */
    void freeze();

    /**
     * check if the meta data has been freezed
     * 
     * @generated
     */
    boolean isFrozen();

    /**
     * @generated
     */
    UAnnotation createAnnotation(final String name);

    public UAnnotationDetail createAnnotationDetail(String source, String key, String value);

    /**
     * @generated
     */
    UAnnotation getAnnotation(final String name);

    /**
     * @generated
     */
    UAnnotationDetail getAnnotationDetail(final String name, final String key);

    /**
     * 
     * initializes the model element, e.g. create private member for reflection access
     * 
     * @generated
     */
    void build();

}
