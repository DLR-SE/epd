package de.emir.model.domain.maritime.iec61174.impl;

import java.util.List;

import de.emir.model.domain.maritime.iec61174.DefaultWayPoint;
import de.emir.model.domain.maritime.iec61174.Extension;
import de.emir.model.domain.maritime.iec61174.Iec61174Package;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.model.domain.maritime.iec61174.impl.IECElementWithExtensionImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = DefaultWayPoint.class)
public class DefaultWayPointImpl extends IECElementWithExtensionImpl implements DefaultWayPoint  
{
	
	
	/**
	 *	@generated 
	 */
	private double mRadius = 0.0;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public DefaultWayPointImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public DefaultWayPointImpl(final DefaultWayPoint _copy) {
		super(_copy);
		mRadius = _copy.getRadius();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public DefaultWayPointImpl(List<Extension> _extensions, double _radius) {
		super(_extensions);
		mRadius = _radius;
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return Iec61174Package.Literals.DefaultWayPoint;
	}

	/**
	 *	@generated 
	 */
	public void setRadius(double _radius) {
		if (needNotification(Iec61174Package.Literals.DefaultWayPoint_radius)){
			double _oldValue = mRadius;
			mRadius = _radius;
			notify(_oldValue, mRadius, Iec61174Package.Literals.DefaultWayPoint_radius, NotificationType.SET);
		}else{
			mRadius = _radius;
		}
	}

	/**
	 *	@generated 
	 */
	public double getRadius() {
		return mRadius;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "DefaultWayPointImpl{" +
		" radius = " + getRadius() + 
		"}";
	}
}
