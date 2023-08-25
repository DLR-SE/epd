package de.emir.model.universal.math.impl;

import de.emir.model.universal.math.MathPackage;
import de.emir.model.universal.math.Matrix4;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Matrix4.class)
public class Matrix4Impl extends UObjectImpl implements Matrix4  
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
	private double mM13 = 0.0;
	/**
	 *	@generated 
	 */
	private double mM14 = 0.0;
	/**
	 *	@generated 
	 */
	private double mM21 = 0.0;
	/**
	 *	@generated 
	 */
	private double mM22 = 1.0;
	/**
	 *	@generated 
	 */
	private double mM23 = 0.0;
	/**
	 *	@generated 
	 */
	private double mM24 = 0.0;
	/**
	 *	@generated 
	 */
	private double mM31 = 0.0;
	/**
	 *	@generated 
	 */
	private double mM32 = 0.0;
	/**
	 *	@generated 
	 */
	private double mM33 = 1.0;
	/**
	 *	@generated 
	 */
	private double mM34 = 0.0;
	/**
	 *	@generated 
	 */
	private double mM41 = 0.0;
	/**
	 *	@generated 
	 */
	private double mM42 = 0.0;
	/**
	 *	@generated 
	 */
	private double mM43 = 0.0;
	/**
	 *	@generated 
	 */
	private double mM44 = 1.0;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public Matrix4Impl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public Matrix4Impl(final Matrix4 _copy) {
		mM11 = _copy.getM11();
		mM12 = _copy.getM12();
		mM13 = _copy.getM13();
		mM14 = _copy.getM14();
		mM21 = _copy.getM21();
		mM22 = _copy.getM22();
		mM23 = _copy.getM23();
		mM24 = _copy.getM24();
		mM31 = _copy.getM31();
		mM32 = _copy.getM32();
		mM33 = _copy.getM33();
		mM34 = _copy.getM34();
		mM41 = _copy.getM41();
		mM42 = _copy.getM42();
		mM43 = _copy.getM43();
		mM44 = _copy.getM44();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public Matrix4Impl(double _m11, double _m12, double _m13, double _m14, double _m21, double _m22, double _m23, double _m24, double _m31, double _m32, double _m33, double _m34, double _m41, double _m42, double _m43, double _m44) {
		mM11 = _m11;
		mM12 = _m12;
		mM13 = _m13;
		mM14 = _m14;
		mM21 = _m21;
		mM22 = _m22;
		mM23 = _m23;
		mM24 = _m24;
		mM31 = _m31;
		mM32 = _m32;
		mM33 = _m33;
		mM34 = _m34;
		mM41 = _m41;
		mM42 = _m42;
		mM43 = _m43;
		mM44 = _m44;
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return MathPackage.Literals.Matrix4;
	}

	/**
	 *	@generated 
	 */
	public void setM11(double _m11) {
		if (needNotification(MathPackage.Literals.Matrix4_m11)){
			double _oldValue = mM11;
			mM11 = _m11;
			notify(_oldValue, mM11, MathPackage.Literals.Matrix4_m11, NotificationType.SET);
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
		if (needNotification(MathPackage.Literals.Matrix4_m12)){
			double _oldValue = mM12;
			mM12 = _m12;
			notify(_oldValue, mM12, MathPackage.Literals.Matrix4_m12, NotificationType.SET);
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
	public void setM13(double _m13) {
		if (needNotification(MathPackage.Literals.Matrix4_m13)){
			double _oldValue = mM13;
			mM13 = _m13;
			notify(_oldValue, mM13, MathPackage.Literals.Matrix4_m13, NotificationType.SET);
		}else{
			mM13 = _m13;
		}
	}

	/**
	 *	@generated 
	 */
	public double getM13() {
		return mM13;
	}

	/**
	 *	@generated 
	 */
	public void setM14(double _m14) {
		if (needNotification(MathPackage.Literals.Matrix4_m14)){
			double _oldValue = mM14;
			mM14 = _m14;
			notify(_oldValue, mM14, MathPackage.Literals.Matrix4_m14, NotificationType.SET);
		}else{
			mM14 = _m14;
		}
	}

	/**
	 *	@generated 
	 */
	public double getM14() {
		return mM14;
	}

	/**
	 *	@generated 
	 */
	public void setM21(double _m21) {
		if (needNotification(MathPackage.Literals.Matrix4_m21)){
			double _oldValue = mM21;
			mM21 = _m21;
			notify(_oldValue, mM21, MathPackage.Literals.Matrix4_m21, NotificationType.SET);
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
		if (needNotification(MathPackage.Literals.Matrix4_m22)){
			double _oldValue = mM22;
			mM22 = _m22;
			notify(_oldValue, mM22, MathPackage.Literals.Matrix4_m22, NotificationType.SET);
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
	 *	@generated 
	 */
	public void setM23(double _m23) {
		if (needNotification(MathPackage.Literals.Matrix4_m23)){
			double _oldValue = mM23;
			mM23 = _m23;
			notify(_oldValue, mM23, MathPackage.Literals.Matrix4_m23, NotificationType.SET);
		}else{
			mM23 = _m23;
		}
	}

	/**
	 *	@generated 
	 */
	public double getM23() {
		return mM23;
	}

	/**
	 *	@generated 
	 */
	public void setM24(double _m24) {
		if (needNotification(MathPackage.Literals.Matrix4_m24)){
			double _oldValue = mM24;
			mM24 = _m24;
			notify(_oldValue, mM24, MathPackage.Literals.Matrix4_m24, NotificationType.SET);
		}else{
			mM24 = _m24;
		}
	}

	/**
	 *	@generated 
	 */
	public double getM24() {
		return mM24;
	}

	/**
	 *	@generated 
	 */
	public void setM31(double _m31) {
		if (needNotification(MathPackage.Literals.Matrix4_m31)){
			double _oldValue = mM31;
			mM31 = _m31;
			notify(_oldValue, mM31, MathPackage.Literals.Matrix4_m31, NotificationType.SET);
		}else{
			mM31 = _m31;
		}
	}

	/**
	 *	@generated 
	 */
	public double getM31() {
		return mM31;
	}

	/**
	 *	@generated 
	 */
	public void setM32(double _m32) {
		if (needNotification(MathPackage.Literals.Matrix4_m32)){
			double _oldValue = mM32;
			mM32 = _m32;
			notify(_oldValue, mM32, MathPackage.Literals.Matrix4_m32, NotificationType.SET);
		}else{
			mM32 = _m32;
		}
	}

	/**
	 *	@generated 
	 */
	public double getM32() {
		return mM32;
	}

	/**
	 *	@generated 
	 */
	public void setM33(double _m33) {
		if (needNotification(MathPackage.Literals.Matrix4_m33)){
			double _oldValue = mM33;
			mM33 = _m33;
			notify(_oldValue, mM33, MathPackage.Literals.Matrix4_m33, NotificationType.SET);
		}else{
			mM33 = _m33;
		}
	}

	/**
	 *	@generated 
	 */
	public double getM33() {
		return mM33;
	}

	/**
	 *	@generated 
	 */
	public void setM34(double _m34) {
		if (needNotification(MathPackage.Literals.Matrix4_m34)){
			double _oldValue = mM34;
			mM34 = _m34;
			notify(_oldValue, mM34, MathPackage.Literals.Matrix4_m34, NotificationType.SET);
		}else{
			mM34 = _m34;
		}
	}

	/**
	 *	@generated 
	 */
	public double getM34() {
		return mM34;
	}

	/**
	 *	@generated 
	 */
	public void setM41(double _m41) {
		if (needNotification(MathPackage.Literals.Matrix4_m41)){
			double _oldValue = mM41;
			mM41 = _m41;
			notify(_oldValue, mM41, MathPackage.Literals.Matrix4_m41, NotificationType.SET);
		}else{
			mM41 = _m41;
		}
	}

	/**
	 *	@generated 
	 */
	public double getM41() {
		return mM41;
	}

	/**
	 *	@generated 
	 */
	public void setM42(double _m42) {
		if (needNotification(MathPackage.Literals.Matrix4_m42)){
			double _oldValue = mM42;
			mM42 = _m42;
			notify(_oldValue, mM42, MathPackage.Literals.Matrix4_m42, NotificationType.SET);
		}else{
			mM42 = _m42;
		}
	}

	/**
	 *	@generated 
	 */
	public double getM42() {
		return mM42;
	}

	/**
	 *	@generated 
	 */
	public void setM43(double _m43) {
		if (needNotification(MathPackage.Literals.Matrix4_m43)){
			double _oldValue = mM43;
			mM43 = _m43;
			notify(_oldValue, mM43, MathPackage.Literals.Matrix4_m43, NotificationType.SET);
		}else{
			mM43 = _m43;
		}
	}

	/**
	 *	@generated 
	 */
	public double getM43() {
		return mM43;
	}

	/**
	 *	@generated 
	 */
	public void setM44(double _m44) {
		if (needNotification(MathPackage.Literals.Matrix4_m44)){
			double _oldValue = mM44;
			mM44 = _m44;
			notify(_oldValue, mM44, MathPackage.Literals.Matrix4_m44, NotificationType.SET);
		}else{
			mM44 = _m44;
		}
	}

	/**
	 *	@generated 
	 */
	public double getM44() {
		return mM44;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "Matrix4Impl{" +
		" m11 = " + getM11() + 
		" m12 = " + getM12() + 
		" m13 = " + getM13() + 
		" m14 = " + getM14() + 
		" m21 = " + getM21() + 
		" m22 = " + getM22() + 
		" m23 = " + getM23() + 
		" m24 = " + getM24() + 
		" m31 = " + getM31() + 
		" m32 = " + getM32() + 
		" m33 = " + getM33() + 
		" m34 = " + getM34() + 
		" m41 = " + getM41() + 
		" m42 = " + getM42() + 
		" m43 = " + getM43() + 
		" m44 = " + getM44() + 
		"}";
	}
}
