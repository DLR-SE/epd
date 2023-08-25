package de.emir.tuml.ucore.runtime.impl;

import java.util.List;

import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UAnnotationDetail;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;

/**
 * @generated
 */
@UMLImplementation(classifier = UAnnotation.class)
public class UAnnotationImpl extends UObjectImpl implements UAnnotation {
    /**
     * @generated
     */
    private String mName = "";
    /**
     * @generated
     */
    private List<UAnnotationDetail> mDetails = null;

    /**
     * Default constructor
     * 
     * @generated
     */
    public UAnnotationImpl() {
        super();
    }

    /**
     * Default copy constructor
     * 
     * @generated
     */
    public UAnnotationImpl(final UAnnotation _copy) {
        mName = _copy.getName();
        mDetails = _copy.getDetails();
    }

    /**
     * Default attribute constructor
     * 
     * @generated
     */
    public UAnnotationImpl(String _name, List<UAnnotationDetail> _details) {
        mName = _name;
        mDetails = _details;
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        return RuntimePackage.Literals.UAnnotation;
    }

    /**
     * @generated
     */
    public void setName(String _name) {
        if (needNotification(RuntimePackage.Literals.UAnnotation_name)) {
            String _oldValue = mName;
            mName = _name;
            notify(_oldValue, mName, RuntimePackage.Literals.UAnnotation_name, NotificationType.SET);
        } else {
            mName = _name;
        }
    }

    /**
     * @generated
     */
    public String getName() {
        return mName;
    }

    /**
     * @generated
     */
    public List<UAnnotationDetail> getDetails() {
        if (mDetails == null) {
            mDetails = new UContainmentList<UAnnotationDetail>(this,
                    RuntimePackage.theInstance.getUAnnotation_details());
        }
        return mDetails;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public void addDetail(final String _key, final String _value) {
        if (getDetail(_key) != null)
            getDetail(_key).setValue(_value);
        getDetails().add(new UAnnotationDetailImpl(_key, _value));
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public UAnnotationDetail getDetail(final String _key) {
        for (UAnnotationDetail d : getDetails())
            if (d.getKey().equals(_key))
                return d;
        return null;
    }

    /**
     * @generated
     */
    @Override
    public String toString() {
        return "UAnnotationImpl{" + " name = " + getName() + "}";
    }
}
