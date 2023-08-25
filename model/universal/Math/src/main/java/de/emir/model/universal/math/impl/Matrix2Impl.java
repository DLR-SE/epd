package de.emir.model.universal.math.impl;

import de.emir.model.universal.math.MathPackage;
import de.emir.model.universal.math.Matrix2;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Matrix2.class)
public class Matrix2Impl extends UObjectImpl implements Matrix2  
{
	
	
	/**
	 *	@generated 
	 */
	private double mM11 = 1.0;
	/**
	 *	@generated 
	 */
	private double mM12 = 0.0;
	/**
	 *	@generated 
	 */
	private double mM21 = 0.0;
	/**
	 *	@generated 
	 */
	private double mM22 = 1.0;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public Matrix2Impl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public Matrix2Impl(final Matrix2 _copy) {
		mM11 = _copy.getM11();
		mM12 = _copy.getM12();
		mM21 = _copy.getM21();
		mM22 = _copy.getM22();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public Matrix2Impl(double _m11, double _m12, double _m21, double _m22) {
		mM11 = _m11;
		mM12 = _m12;
		mM21 = _m21;
		mM22 = _m22;
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return MathPackage.Literals.Matrix2;
	}

	/**
	 *	@generated 
	 */
	public void setM11(double _m11) {
		if (needNotification(MathPackage.Literals.Matrix2_m11)){
			double _oldValue = mM11;
			mM11 = _m11;
			notify(_oldValue, mM11, MathPackage.Literals.Matrix2_m11, NotificationType.SET);
		}else{
			mM11 = _m11;
		}
	}

	/**
	 *	@generated 
	 */
	public double getM11() {
		return mM11;
	}

	/**
	 *	@generated 
	 */
	public void setM12(double _m12) {
		if (needNotification(MathPackage.Literals.Matrix2_m12)){
			double _oldValue = mM12;
			mM12 = _m12;
			notify(_oldValue, mM12, MathPackage.Literals.Matrix2_m12, NotificationType.SET);
		}else{
			mM12 = _m12;
		}
	}

	/**
	 *	@generated 
	 */
	public double getM12() {
		return mM12;
	}

	/**
	 *	@generated 
	 */
	public void setM21(double _m21) {
		if (needNotification(MathPackage.Literals.Matrix2_m21)){
			double _oldValue = mM21;
			mM21 = _m21;
			notify(_oldValue, mM21, MathPackage.Literals.Matrix2_m21, NotificationType.SET);
		}else{
			mM21 = _m21;
		}
	}

	/**
	 *	@generated 
	 */
	public double getM21() {
		return mM21;
	}

	/**
	 *	@generated 
	 */
	public void setM22(double _m22) {
		if (needNotification(MathPackage.Literals.Matrix2_m22)){
			double _oldValue = mM22;
			mM22 = _m22;
			notify(_oldValue, mM22, MathPackage.Literals.Matrix2_m22, NotificationType.SET);
		}else{
			mM22 = _m22;
		}
	}

	/**
	 *	@generated 
	 */
	public double getM22() {
		return mM22;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "Matrix2Impl{" +
		" m11 = " + getM11() + 
		" m12 = " + getM12() + 
		" m21 = " + getM21() + 
		" m22 = " + getM22() + 
		"}";
	}
}
