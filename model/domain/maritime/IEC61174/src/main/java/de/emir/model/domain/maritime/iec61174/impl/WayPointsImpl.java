package de.emir.model.domain.maritime.iec61174.impl;

import java.util.List;

import de.emir.model.domain.maritime.iec61174.DefaultWayPoint;
import de.emir.model.domain.maritime.iec61174.Extension;
import de.emir.model.domain.maritime.iec61174.Iec61174Package;
import de.emir.model.domain.maritime.iec61174.WayPoints;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.model.domain.maritime.iec61174.impl.IECElementWithExtensionImpl;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;


/**
 *	@generated 
 */
@UMLImplementation(classifier = WayPoints.class)
public class WayPointsImpl extends IECElementWithExtensionImpl implements WayPoints  
{
	
	
	/**
	 *	@generated 
	 */
	private DefaultWayPoint mDefaultWaypoint = null;
	/**
	 *	@generated 
	 */
	private List<Waypoint> mWaypoints = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public WayPointsImpl(){
		super();
		//set the default values and assign them to this instance 
		setDefaultWaypoint(mDefaultWaypoint);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public WayPointsImpl(final WayPoints _copy) {
		super(_copy);
		mDefaultWaypoint = _copy.getDefaultWaypoint();
		mWaypoints = _copy.getWaypoints();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public WayPointsImpl(List<Extension> _extensions, DefaultWayPoint _defaultWaypoint, List<Waypoint> _waypoints) {
		super(_extensions);
		mDefaultWaypoint = _defaultWaypoint; 
		mWaypoints = _waypoints; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return Iec61174Package.Literals.WayPoints;
	}

	/**
	 *	@generated 
	 */
	public void setDefaultWaypoint(DefaultWayPoint _defaultWaypoint) {
		Notification<DefaultWayPoint> notification = basicSet(mDefaultWaypoint, _defaultWaypoint, Iec61174Package.Literals.WayPoints_defaultWaypoint);
		mDefaultWaypoint = _defaultWaypoint;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public DefaultWayPoint getDefaultWaypoint() {
		return mDefaultWaypoint;
	}

	/**
	 *	@generated 
	 */
	public List<Waypoint> getWaypoints() {
		if (mWaypoints == null) {
			mWaypoints = new UContainmentList<Waypoint>(this, Iec61174Package.theInstance.getWayPoints_waypoints()); 
		}
		return mWaypoints;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "WayPointsImpl{" +
		"}";
	}
}
