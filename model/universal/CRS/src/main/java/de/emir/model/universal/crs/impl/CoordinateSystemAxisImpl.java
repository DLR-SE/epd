package de.emir.model.universal.crs.impl;

import de.emir.model.universal.crs.AxisDirection;
import de.emir.model.universal.crs.CoordinateSystemAxis;
import de.emir.model.universal.crs.CrsPackage;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = CoordinateSystemAxis.class)
public class CoordinateSystemAxisImpl extends UObjectImpl implements CoordinateSystemAxis  
{
	
	
	/**
	 *	@generated 
	 */
	private AxisDirection mDirection = null;
	/**
	 symbolic name of this axis (axisSymbol in S100) 
	 * @generated 
	 */
	private String mName = "";
	/**
	 defines the minimum valid scope of this axis and thus the minimum value of the valid envelope, created by this cs 
	 * @generated 
	 */
	private double mMinimumRange = 0.0;
	/**
	 defines the maximum valid scope of this axis and thus the maximum value of the valid envelope, created by this cs 
	 * @generated 
	 */
	private double mMaximumRange = 0.0;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public CoordinateSystemAxisImpl(){
		super();
		//set the default values and assign them to this instance 
		setDirection(mDirection);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public CoordinateSystemAxisImpl(final CoordinateSystemAxis _copy) {
		mName = _copy.getName();
		mMinimumRange = _copy.getMinimumRange();
		mMaximumRange = _copy.getMaximumRange();
		mDirection = _copy.getDirection();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public CoordinateSystemAxisImpl(AxisDirection _direction, String _name, double _minimumRange, double _maximumRange) {
		mName = _name;
		mMinimumRange = _minimumRange;
		mMaximumRange = _maximumRange;
		mDirection = _direction; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CrsPackage.Literals.CoordinateSystemAxis;
	}

	/**
	 *	@generated 
	 */
	public void setDirection(AxisDirection _direction) {
		if (needNotification(CrsPackage.Literals.CoordinateSystemAxis_direction)){
			AxisDirection _oldValue = mDirection;
			mDirection = _direction;
			notify(_oldValue, mDirection, CrsPackage.Literals.CoordinateSystemAxis_direction, NotificationType.SET);
		}else{
			mDirection = _direction;
		}
	}

	/**
	 *	@generated 
	 */
	public AxisDirection getDirection() {
		return mDirection;
	}

	/**
	 symbolic name of this axis (axisSymbol in S100) 
	 * @generated 
	 */
	public void setName(String _name) {
		if (needNotification(CrsPackage.Literals.CoordinateSystemAxis_name)){
			String _oldValue = mName;
			mName = _name;
			notify(_oldValue, mName, CrsPackage.Literals.CoordinateSystemAxis_name, NotificationType.SET);
		}else{
			mName = _name;
		}
	}

	/**
	 symbolic name of this axis (axisSymbol in S100) 
	 * @generated 
	 */
	public String getName() {
		return mName;
	}

	/**
	 defines the minimum valid scope of this axis and thus the minimum value of the valid envelope, created by this cs 
	 * @generated 
	 */
	public void setMinimumRange(double _minimumRange) {
		if (needNotification(CrsPackage.Literals.CoordinateSystemAxis_minimumRange)){
			double _oldValue = mMinimumRange;
			mMinimumRange = _minimumRange;
			notify(_oldValue, mMinimumRange, CrsPackage.Literals.CoordinateSystemAxis_minimumRange, NotificationType.SET);
		}else{
			mMinimumRange = _minimumRange;
		}
	}

	/**
	 defines the minimum valid scope of this axis and thus the minimum value of the valid envelope, created by this cs 
	 * @generated 
	 */
	public double getMinimumRange() {
		return mMinimumRange;
	}

	/**
	 defines the maximum valid scope of this axis and thus the maximum value of the valid envelope, created by this cs 
	 * @generated 
	 */
	public void setMaximumRange(double _maximumRange) {
		if (needNotification(CrsPackage.Literals.CoordinateSystemAxis_maximumRange)){
			double _oldValue = mMaximumRange;
			mMaximumRange = _maximumRange;
			notify(_oldValue, mMaximumRange, CrsPackage.Literals.CoordinateSystemAxis_maximumRange, NotificationType.SET);
		}else{
			mMaximumRange = _maximumRange;
		}
	}

	/**
	 defines the maximum valid scope of this axis and thus the maximum value of the valid envelope, created by this cs 
	 * @generated 
	 */
	public double getMaximumRange() {
		return mMaximumRange;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "CoordinateSystemAxisImpl{" +
		" name = " + getName() + 
		" minimumRange = " + getMinimumRange() + 
		" maximumRange = " + getMaximumRange() + 
		"}";
	}
}
