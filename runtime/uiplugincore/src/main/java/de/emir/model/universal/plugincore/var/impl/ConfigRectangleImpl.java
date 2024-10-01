package de.emir.model.universal.plugincore.var.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.plugincore.impl.ConfigVariableImpl;
import de.emir.model.universal.plugincore.var.ConfigRectangle;
import de.emir.model.universal.plugincore.var.VarPackage;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UPackage;
import java.util.List;


/**
 *	@generated 
 */
@UMLImplementation(classifier = ConfigRectangle.class)
public class ConfigRectangleImpl extends ConfigVariableImpl implements ConfigRectangle  
{
	
	
	/**
	 *	@generated 
	 */
	private int mX = 0;
	/**
	 *	@generated 
	 */
	private int mY = 0;
	/**
	 *	@generated 
	 */
	private int mWidth = 0;
	/**
	 *	@generated 
	 */
	private int mHeight = 0;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public ConfigRectangleImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public ConfigRectangleImpl(final ConfigRectangle _copy) {
		super(_copy);
		mX = _copy.getX();
		mY = _copy.getY();
		mWidth = _copy.getWidth();
		mHeight = _copy.getHeight();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public ConfigRectangleImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package, String _name, int _x, int _y, int _width, int _height) {
		super(_documentation,_annotations,_package,_name);
		mX = _x;
		mY = _y;
		mWidth = _width;
		mHeight = _height;
	}
	
    /**
	 *	Default attribute constructor
	 *	@generated_not
	 */
	public ConfigRectangleImpl(int _x, int _y, int _width, int _height) {
		super();
		mX = _x;
		mY = _y;
		mWidth = _width;
		mHeight = _height;
	}
    
    /**
	 *	Default attribute constructor
	 *	@generated_not
	 */
	public ConfigRectangleImpl(java.awt.Rectangle rectangle) {
        super();
		mX = rectangle.x;
        mY = rectangle.y;
        mWidth = rectangle.width;
        mHeight = rectangle.height;
	}
    
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VarPackage.Literals.ConfigRectangle;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	public void setX(int _x) {
		if (needNotification(VarPackage.Literals.ConfigRectangle_x)){
			int _oldValue = mX;
			mX = _x;
			notify(_oldValue, _x, VarPackage.Literals.ConfigRectangle_x, NotificationType.SET);
		}else{
			mX = _x;
		}
	}
	/**
	 *	@generated 
	 */
	public int getX() {
		return mX;
	}
	/**
	 *	@generated 
	 */
	public void setY(int _y) {
		if (needNotification(VarPackage.Literals.ConfigRectangle_y)){
			int _oldValue = mY;
			mY = _y;
			notify(_oldValue, _y, VarPackage.Literals.ConfigRectangle_y, NotificationType.SET);
		}else{
			mY = _y;
		}
	}
	/**
	 *	@generated 
	 */
	public int getY() {
		return mY;
	}
	/**
	 *	@generated 
	 */
	public void setWidth(int _width) {
		if (needNotification(VarPackage.Literals.ConfigRectangle_width)){
			int _oldValue = mWidth;
			mWidth = _width;
			notify(_oldValue, _width, VarPackage.Literals.ConfigRectangle_width, NotificationType.SET);
		}else{
			mWidth = _width;
		}
	}
	/**
	 *	@generated 
	 */
	public int getWidth() {
		return mWidth;
	}
	/**
	 *	@generated 
	 */
	public void setHeight(int _height) {
		if (needNotification(VarPackage.Literals.ConfigRectangle_height)){
			int _oldValue = mHeight;
			mHeight = _height;
			notify(_oldValue, _height, VarPackage.Literals.ConfigRectangle_height, NotificationType.SET);
		}else{
			mHeight = _height;
		}
	}
	/**
	 *	@generated 
	 */
	public int getHeight() {
		return mHeight;
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
		return "ConfigRectangleImpl{" +
		" documentation = " + getDocumentation() + 
		" name = " + getName() + 
		" x = " + getX() + 
		" y = " + getY() + 
		" width = " + getWidth() + 
		" height = " + getHeight() + 
		"}";
	}
    
    /**
	* @generated_not
	*/
	public java.awt.Rectangle getAWTRectangle() {
		return new java.awt.Rectangle(mX, mY, mWidth, mHeight);
	}

    /**
	* @generated_not
	*/
	public void setAWTRectangle(java.awt.Rectangle source) {
		mX = source.x;
		mY = source.y;
		mWidth = source.width;
		mHeight = source.height;
	}
}
