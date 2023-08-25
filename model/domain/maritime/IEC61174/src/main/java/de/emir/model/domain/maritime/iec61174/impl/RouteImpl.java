package de.emir.model.domain.maritime.iec61174.impl;

import java.util.List;

import de.emir.model.domain.maritime.iec61174.Extension;
import de.emir.model.domain.maritime.iec61174.Iec61174Package;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.iec61174.RouteSchedule;
import de.emir.model.domain.maritime.iec61174.WayPoints;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.model.domain.maritime.iec61174.impl.IECElementWithExtensionImpl;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.spatial.impl.EnvelopeImpl;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.Length;
import de.emir.model.universal.units.impl.LengthImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Route.class)
public class RouteImpl extends IECElementWithExtensionImpl implements Route  
{
	
	
	/**
	 *	@generated 
	 */
	private String mVersion = "";
	/**
	 *	@generated 
	 */
	private WayPoints mWaypoints = null;
	/**
	 *	@generated 
	 */
	private String mName = "";
	/**
	 *	@generated 
	 */
	private RouteSchedule mSchedule = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public RouteImpl(){
		super();
		//set the default values and assign them to this instance 
		setWaypoints(mWaypoints);
		setSchedule(mSchedule);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public RouteImpl(final Route _copy) {
		super(_copy);
		mName = _copy.getName();
		mVersion = _copy.getVersion();
		mWaypoints = _copy.getWaypoints();
		mSchedule = _copy.getSchedule();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public RouteImpl(List<Extension> _extensions, String _name, String _version, WayPoints _waypoints, RouteSchedule _schedule) {
		super(_extensions);
		mName = _name;
		mVersion = _version;
		mWaypoints = _waypoints; 
		mSchedule = _schedule; 
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return Iec61174Package.Literals.Route;
	}

	/**
	 *	@generated 
	 */
	public String getName() {
		return mName;
	}

	/**
	 *	@generated 
	 */
	public void setVersion(String _version) {
		if (needNotification(Iec61174Package.Literals.Route_version)){
			String _oldValue = mVersion;
			mVersion = _version;
			notify(_oldValue, mVersion, Iec61174Package.Literals.Route_version, NotificationType.SET);
		}else{
			mVersion = _version;
		}
	}

	/**
	 *	@generated 
	 */
	public void setName(String _name) {
		if (needNotification(Iec61174Package.Literals.Route_name)){
			String _oldValue = mName;
			mName = _name;
			notify(_oldValue, mName, Iec61174Package.Literals.Route_name, NotificationType.SET);
		}else{
			mName = _name;
		}
	}

	/**
	 *	@generated 
	 */
	public String getVersion() {
		return mVersion;
	}

	/**
	 *	@generated 
	 */
	public void setWaypoints(WayPoints _waypoints) {
		Notification<WayPoints> notification = basicSet(mWaypoints, _waypoints, Iec61174Package.Literals.Route_waypoints);
		mWaypoints = _waypoints;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public WayPoints getWaypoints() {
		return mWaypoints;
	}

	/**
	 *	@generated 
	 */
	public void setSchedule(RouteSchedule _schedule) {
		Notification<RouteSchedule> notification = basicSet(mSchedule, _schedule, Iec61174Package.Literals.Route_schedule);
		mSchedule = _schedule;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public RouteSchedule getSchedule() {
		return mSchedule;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Length getLength()
	{
		return getLength(this, 0, getWaypoints().getWaypoints().size());
	}

	public Length getLength(Route self, int start, int end) {
		List<Waypoint> list = self.getWaypoints().getWaypoints();
		Length length = new LengthImpl();
		for (int i = start; i < end-1; i++){
			length.addLocal(list.get(i).getPosition().getDistance(list.get(i+1).getPosition()));			
		}
		return length;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Envelope getEnvelope()
	{
		if (getWaypoints() == null || getWaypoints().getWaypoints().isEmpty())
			return null;
		Coordinate first = getWaypoints().getWaypoints().get(0).getPosition();
		if (first == null) 
			return null;
		CoordinateReferenceSystem crs = first.getCrs();
		Envelope result = new EnvelopeImpl(first);
		for (int i = 1; i < getWaypoints().getWaypoints().size(); i++){
			if (getWaypoints().getWaypoints().get(i) == null)
				continue;
			Coordinate c = getWaypoints().getWaypoints().get(i).getPosition();
			if (c != null)
				result.expandLocal(c.get(crs));
		}
		return result;
	}
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Length getLength(final Coordinate coord)
	{
		List<Waypoint> list = getWaypoints().getWaypoints();
		//first need to find the nearest point on the route
		double minDist = Double.MAX_VALUE;
		int minIdx = 0;
		for (int i = 0; i < list.size(); i++){
			double d = coord.getDistance(list.get(i).getPosition()).getAs(DistanceUnit.METER);
			if (d < minDist){
				minIdx = i; minDist = d;
			}
		}
		if (minIdx == list.size()-1)
			return new LengthImpl(minDist, DistanceUnit.METER);
		return getLength(this, minIdx, list.size());
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "RouteImpl{" +
		" name = " + getName() + 
		" version = " + getVersion() + 
		"}";
	}
}
