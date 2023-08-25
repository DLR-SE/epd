package de.emir.model.universal.units.impl;

import java.awt.Color;

import de.emir.model.universal.units.RGBColor;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = RGBColor.class)
public class RGBColorImpl extends UObjectImpl implements RGBColor  
{
	
	
	/**
	 *	@generated 
	 */
	private float mR = 0.0f;
	/**
	 *	@generated 
	 */
	private float mG = 0.0f;
	/**
	 *	@generated 
	 */
	private float mB = 0.0f;
	/**
	 Alpha or transparency value a == 1 no transparency; a == 0 : full transparency
	 * @generated 
	 */
	private float mA = 1.0f;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public RGBColorImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public RGBColorImpl(final RGBColor _copy) {
		mR = _copy.getR();
		mG = _copy.getG();
		mB = _copy.getB();
		mA = _copy.getA();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public RGBColorImpl(float _r, float _g, float _b, float _a) {
		mR = _r;
		mG = _g;
		mB = _b;
		mA = _a;
	}
	
	public RGBColorImpl(int r, int g, int b, int a) {
		this(r/255.0f, g/255.0f, b/255.0f, a/255.0f);
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return UnitsPackage.Literals.RGBColor;
	}

	/**
	 *	@generated 
	 */
	public void setR(float _r) {
		if (needNotification(UnitsPackage.Literals.RGBColor_r)){
			float _oldValue = mR;
			mR = _r;
			notify(_oldValue, mR, UnitsPackage.Literals.RGBColor_r, NotificationType.SET);
		}else{
			mR = _r;
		}
	}

	/**
	 *	@generated 
	 */
	public float getR() {
		return mR;
	}

	/**
	 *	@generated 
	 */
	public void setG(float _g) {
		if (needNotification(UnitsPackage.Literals.RGBColor_g)){
			float _oldValue = mG;
			mG = _g;
			notify(_oldValue, mG, UnitsPackage.Literals.RGBColor_g, NotificationType.SET);
		}else{
			mG = _g;
		}
	}

	/**
	 *	@generated 
	 */
	public float getG() {
		return mG;
	}

	/**
	 *	@generated 
	 */
	public void setB(float _b) {
		if (needNotification(UnitsPackage.Literals.RGBColor_b)){
			float _oldValue = mB;
			mB = _b;
			notify(_oldValue, mB, UnitsPackage.Literals.RGBColor_b, NotificationType.SET);
		}else{
			mB = _b;
		}
	}

	/**
	 *	@generated 
	 */
	public float getB() {
		return mB;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "RGBColorImpl{" +
		" r = " + getR() + 
		" g = " + getG() + 
		" b = " + getB() + 
		" a = " + getA() + 
		"}";
	}

	/**
	 Alpha or transparency value a == 1 no transparency; a == 0 : full transparency
	 * @generated 
	 */
	public float getA() {
		return mA;
	}

	/**
	 Alpha or transparency value a == 1 no transparency; a == 0 : full transparency
	 * @generated 
	 */
	public void setA(float _a) {
		if (needNotification(UnitsPackage.Literals.RGBColor_a)){
			float _oldValue = mA;
			mA = _a;
			notify(_oldValue, mA, UnitsPackage.Literals.RGBColor_a, NotificationType.SET);
		}else{
			mA = _a;
		}
	}

	@Override
	public Color getNative() {
		return new Color(getR(), getG(), getB(), getA());
	}
	
	public de.emir.model.universal.units.RGBColor setTransparency(float alpha){
		setA(alpha);
		return this;
	}
}
