package de.emir.tuml.ucore.runtime.impl;

import java.util.List;

import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UModel;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.impl.UModelElementImpl;
import de.emir.tuml.ucore.runtime.impl.UPackageImpl;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;

/**
 * @generated
 */
@UMLImplementation(classifier = UModel.class)
public class UModelImpl extends UModelElementImpl implements UModel {
    /**
     * @generated
     */
    private String mModelName = "";
    /**
     * @generated
     */
    private String mFieldOfApplication = "";
    /**
     * @generated
     */
    private String mScope = "";
    /**
     * @generated
     */
    private String mVersionDate = "";
    /**
     * @generated
     */
    private UPackage mRootPackage = new UPackageImpl();

    /**
     * Default constructor
     * 
     * @generated
     */
    public UModelImpl() {
        super();
        // set the default values and assign them to this instance
        setRootPackage(mRootPackage);
    }

    /**
     * Default copy constructor
     * 
     * @generated
     */
    public UModelImpl(final UModel _copy) {
        super(_copy);
        mModelName = _copy.getModelName();
        mFieldOfApplication = _copy.getFieldOfApplication();
        mScope = _copy.getScope();
        mVersionDate = _copy.getVersionDate();
        mRootPackage = _copy.getRootPackage();
    }

    /**
     * Default attribute constructor
     * 
     * @generated
     */
    public UModelImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package, String _modelName,
            String _fieldOfApplication, String _scope, String _versionDate, UPackage _rootPackage) {
        super(_documentation, _annotations, _package);
        mModelName = _modelName;
        mFieldOfApplication = _fieldOfApplication;
        mScope = _scope;
        mVersionDate = _versionDate;
        mRootPackage = _rootPackage;
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        return RuntimePackage.Literals.UModel;
    }

    /**
     * @generated
     */
    public void setModelName(String _modelName) {
        if (needNotification(RuntimePackage.Literals.UModel_modelName)) {
            String _oldValue = mModelName;
            mModelName = _modelName;
            notify(_oldValue, mModelName, RuntimePackage.Literals.UModel_modelName, NotificationType.SET);
        } else {
            mModelName = _modelName;
        }
    }

    /**
     * @generated
     */
    public String getModelName() {
        return mModelName;
    }

    /**
     * @generated
     */
    public void setFieldOfApplication(String _fieldOfApplication) {
        if (needNotification(RuntimePackage.Literals.UModel_fieldOfApplication)) {
            String _oldValue = mFieldOfApplication;
            mFieldOfApplication = _fieldOfApplication;
            notify(_oldValue, mFieldOfApplication, RuntimePackage.Literals.UModel_fieldOfApplication,
                    NotificationType.SET);
        } else {
            mFieldOfApplication = _fieldOfApplication;
        }
    }

    /**
     * @generated
     */
    public String getFieldOfApplication() {
        return mFieldOfApplication;
    }

    /**
     * @generated
     */
    public void setScope(String _scope) {
        if (needNotification(RuntimePackage.Literals.UModel_scope)) {
            String _oldValue = mScope;
            mScope = _scope;
            notify(_oldValue, mScope, RuntimePackage.Literals.UModel_scope, NotificationType.SET);
        } else {
            mScope = _scope;
        }
    }

    /**
     * @generated
     */
    public String getScope() {
        return mScope;
    }

    /**
     * @generated
     */
    public void setVersionDate(String _versionDate) {
        if (needNotification(RuntimePackage.Literals.UModel_versionDate)) {
            String _oldValue = mVersionDate;
            mVersionDate = _versionDate;
            notify(_oldValue, mVersionDate, RuntimePackage.Literals.UModel_versionDate, NotificationType.SET);
        } else {
            mVersionDate = _versionDate;
        }
    }

    /**
     * @generated
     */
    public String getVersionDate() {
        return mVersionDate;
    }

    /**
     * @generated
     */
    public void setRootPackage(UPackage _rootPackage) {
        Notification<UPackage> notification = basicSet(mRootPackage, _rootPackage,
                RuntimePackage.Literals.UModel_rootPackage);
        mRootPackage = _rootPackage;
        if (notification != null) {
            dispatchNotification(notification);
        }
    }

    /**
     * @generated
     */
    public UPackage getRootPackage() {
        return mRootPackage;
    }

    /**
     * @inheritDoc
     * @generated
     */
    public void build() {
        // TODO:
        //
        // * initializes the model element, e.g. create private member for reflection access
        //
        throw new UnsupportedOperationException("build not yet implemented");
    }

    /**
     * @generated
     */
    @Override
    public String toString() {
        return "UModelImpl{" + " documentation = " + getDocumentation() + " modelName = " + getModelName()
                + " fieldOfApplication = " + getFieldOfApplication() + " scope = " + getScope() + " versionDate = "
                + getVersionDate() + "}";
    }
}
