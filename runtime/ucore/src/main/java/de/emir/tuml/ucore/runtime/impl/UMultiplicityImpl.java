package de.emir.tuml.ucore.runtime.impl;

import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UMultiplicity;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;

/**
 * @generated
 */
@UMLImplementation(classifier = UMultiplicity.class)
public class UMultiplicityImpl extends UObjectImpl implements UMultiplicity {
    /**
     * @generated
     */
    private int mLower = 0;
    /**
     * @generated
     */
    private int mUpper = 1;

    /**
     * Default constructor
     * 
     * @generated
     */
    public UMultiplicityImpl() {
        super();
    }

    /**
     * Default copy constructor
     * 
     * @generated
     */
    public UMultiplicityImpl(final UMultiplicity _copy) {
        mLower = _copy.getLower();
        mUpper = _copy.getUpper();
    }

    public UMultiplicityImpl(int lower, int upper) {
        super();
        mLower = lower;
        mUpper = upper;
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        return RuntimePackage.Literals.UMultiplicity;
    }

    /**
     * @generated
     */
    public void setLower(int _lower) {
        if (needNotification(RuntimePackage.Literals.UMultiplicity_lower)) {
            int _oldValue = mLower;
            mLower = _lower;
            notify(_oldValue, mLower, RuntimePackage.Literals.UMultiplicity_lower, NotificationType.SET);
        } else {
            mLower = _lower;
        }
    }

    /**
     * @generated
     */
    public int getLower() {
        return mLower;
    }

    /**
     * @generated
     */
    public void setUpper(int _upper) {
        if (needNotification(RuntimePackage.Literals.UMultiplicity_upper)) {
            int _oldValue = mUpper;
            mUpper = _upper;
            notify(_oldValue, mUpper, RuntimePackage.Literals.UMultiplicity_upper, NotificationType.SET);
        } else {
            mUpper = _upper;
        }
    }

    /**
     * @generated
     */
    public int getUpper() {
        return mUpper;
    }

    /**
     * @generated not
     */
    @Override
    public String toString() {
    	if (getLower() != 0) {
    		if (getUpper() < 0)
    			return "[+]";
    		return "[1]";
    	}else {
    		if (getUpper() != 1) {
    			return "[*]";
    		}
    		return "[0..1]";
    	}
//        return "UMultiplicityImpl{" + " lower = " + getLower() + " upper = " + getUpper() + "}";
    }
}
