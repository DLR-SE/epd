package de.emir.model.universal.plugincore.var.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.plugincore.impl.ConfigVariableImpl;
import de.emir.model.universal.plugincore.var.ConfigColor;
import de.emir.model.universal.plugincore.var.VarPackage;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UPackage;
import java.util.List;


/**
 *	@generated 
 */
@UMLImplementation(classifier = ConfigColor.class)
public class ConfigColorImpl extends ConfigVariableImpl implements ConfigColor  
{
	
	
	/**
	 *	@generated 
	 */
	private int mRed = 0;
	/**
	 *	@generated 
	 */
	private int mGreen = 0;
	/**
	 *	@generated 
	 */
	private int mBlue = 0;
	/**
	 *	@generated 
	 */
	private int mAlpha = 255;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public ConfigColorImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public ConfigColorImpl(final ConfigColor _copy) {
		super(_copy);
		mRed = _copy.getRed();
		mGreen = _copy.getGreen();
		mBlue = _copy.getBlue();
		mAlpha = _copy.getAlpha();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public ConfigColorImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package, String _name, int _red, int _green, int _blue, int _alpha) {
		super(_documentation,_annotations,_package,_name);
		mRed = _red;
		mGreen = _green;
		mBlue = _blue;
		mAlpha = _alpha;
	}
    
    /**
	 *	Default attribute constructor
	 *	@generated_not
	 */
	public ConfigColorImpl(int _red, int _green, int _blue, int _alpha) {
		super();
		mRed = _red;
		mGreen = _green;
		mBlue = _blue;
		mAlpha = _alpha;
	}
    
    /**
	 *	@generated_not
	 */
	public ConfigColorImpl(java.awt.Color awtColor) {
		super();
		mRed = awtColor.getRed();
		mGreen = awtColor.getGreen();
		mBlue = awtColor.getBlue();
		mAlpha = awtColor.getAlpha();
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VarPackage.Literals.ConfigColor;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	public void setRed(int _red) {
		if (needNotification(VarPackage.Literals.ConfigColor_red)){
			int _oldValue = mRed;
			mRed = _red;
			notify(_oldValue, _red, VarPackage.Literals.ConfigColor_red, NotificationType.SET);
		}else{
			mRed = _red;
		}
	}
	/**
	 *	@generated 
	 */
	public int getRed() {
		return mRed;
	}
	/**
	 *	@generated 
	 */
	public void setGreen(int _green) {
		if (needNotification(VarPackage.Literals.ConfigColor_green)){
			int _oldValue = mGreen;
			mGreen = _green;
			notify(_oldValue, _green, VarPackage.Literals.ConfigColor_green, NotificationType.SET);
		}else{
			mGreen = _green;
		}
	}
	/**
	 *	@generated 
	 */
	public int getGreen() {
		return mGreen;
	}
	/**
	 *	@generated 
	 */
	public void setBlue(int _blue) {
		if (needNotification(VarPackage.Literals.ConfigColor_blue)){
			int _oldValue = mBlue;
			mBlue = _blue;
			notify(_oldValue, _blue, VarPackage.Literals.ConfigColor_blue, NotificationType.SET);
		}else{
			mBlue = _blue;
		}
	}
	/**
	 *	@generated 
	 */
	public int getBlue() {
		return mBlue;
	}
	/**
	 *	@generated 
	 */
	public void setAlpha(int _alpha) {
		if (needNotification(VarPackage.Literals.ConfigColor_alpha)){
			int _oldValue = mAlpha;
			mAlpha = _alpha;
			notify(_oldValue, _alpha, VarPackage.Literals.ConfigColor_alpha, NotificationType.SET);
		}else{
			mAlpha = _alpha;
		}
	}
	/**
	 *	@generated 
	 */
	public int getAlpha() {
		return mAlpha;
	}
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated
	 */
	public void build()
	{
		//TODO: 
		// 
		//  * initializes the model element, e.g. create private member for reflection access
		//  
		throw new UnsupportedOperationException("build not yet implemented");
	}



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "ConfigColorImpl{" +
		" documentation = " + getDocumentation() + 
		" name = " + getName() + 
		" red = " + getRed() + 
		" green = " + getGreen() + 
		" blue = " + getBlue() + 
		" alpha = " + getAlpha() + 
		"}";
	}
    
    /**
	* @generated_not
	*/
	public java.awt.Color getAWTColor() {
		return new java.awt.Color(mRed, mGreen, mBlue, mAlpha);
	}
	
    /**
	* @generated_not
	*/
	public void setAWTColor(java.awt.Color source) {
		mRed = source.getRed();
		mGreen = source.getGreen();
		mBlue = source.getBlue();
		mAlpha = source.getAlpha();
	}
}
