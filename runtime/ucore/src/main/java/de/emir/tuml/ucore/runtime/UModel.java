package de.emir.tuml.ucore.runtime;

import de.emir.tuml.ucore.runtime.UModelElement;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 * @generated
 */
@UMLClass(parent = UModelElement.class)
public interface UModel extends UModelElement {

    /**
     * @generated
     */
    @UMLProperty(name = "rootPackage", associationType = AssociationType.COMPOSITE, lowerBound = 1)
    public void setRootPackage(UPackage _rootPackage);

    /**
     * @generated
     */
    @UMLProperty(name = "rootPackage", associationType = AssociationType.COMPOSITE, lowerBound = 1)
    public UPackage getRootPackage();

    /**
     * @generated
     */
    @UMLProperty(name = "modelName", associationType = AssociationType.PROPERTY, lowerBound = 1)
    public void setModelName(String _modelName);

    /**
     * @generated
     */
    @UMLProperty(name = "modelName", associationType = AssociationType.PROPERTY, lowerBound = 1)
    public String getModelName();

    /**
     * @generated
     */
    @UMLProperty(name = "fieldOfApplication", associationType = AssociationType.PROPERTY)
    public void setFieldOfApplication(String _fieldOfApplication);

    /**
     * @generated
     */
    @UMLProperty(name = "fieldOfApplication", associationType = AssociationType.PROPERTY)
    public String getFieldOfApplication();

    /**
     * @generated
     */
    @UMLProperty(name = "scope", associationType = AssociationType.PROPERTY)
    public void setScope(String _scope);

    /**
     * @generated
     */
    @UMLProperty(name = "scope", associationType = AssociationType.PROPERTY)
    public String getScope();

    /**
     * @generated
     */
    @UMLProperty(name = "versionDate", associationType = AssociationType.PROPERTY)
    public void setVersionDate(String _versionDate);

    /**
     * @generated
     */
    @UMLProperty(name = "versionDate", associationType = AssociationType.PROPERTY)
    public String getVersionDate();

}
