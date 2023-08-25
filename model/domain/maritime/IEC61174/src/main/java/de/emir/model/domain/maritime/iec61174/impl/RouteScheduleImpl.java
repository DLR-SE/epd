package de.emir.model.domain.maritime.iec61174.impl;

import de.emir.model.domain.maritime.iec61174.Iec61174Package;
import de.emir.model.domain.maritime.iec61174.RouteSchedule;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = RouteSchedule.class)
public class RouteScheduleImpl extends UObjectImpl implements RouteSchedule  
{
	
	
	/**
	 *	@generated 
	 */
	private Time mEstimatedTimeOfDepature = null;
	/**
	 *	@generated 
	 */
	private Time mEstimatedTimeOfArrival = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public RouteScheduleImpl(){
		super();
		//set the default values and assign them to this instance 
		setEstimatedTimeOfDepature(mEstimatedTimeOfDepature);
		setEstimatedTimeOfArrival(mEstimatedTimeOfArrival);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public RouteScheduleImpl(final RouteSchedule _copy) {
		mEstimatedTimeOfDepature = _copy.getEstimatedTimeOfDepature();
		mEstimatedTimeOfArrival = _copy.getEstimatedTimeOfArrival();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public RouteScheduleImpl(Time _estimatedTimeOfDepature, Time _estimatedTimeOfArrival) {
		mEstimatedTimeOfDepature = _estimatedTimeOfDepature; 
		mEstimatedTimeOfArrival = _estimatedTimeOfArrival; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return Iec61174Package.Literals.RouteSchedule;
	}

	/**
	 *	@generated 
	 */
	public void setEstimatedTimeOfDepature(Time _estimatedTimeOfDepature) {
		Notification<Time> notification = basicSet(mEstimatedTimeOfDepature, _estimatedTimeOfDepature, Iec61174Package.Literals.RouteSchedule_estimatedTimeOfDepature);
		mEstimatedTimeOfDepature = _estimatedTimeOfDepature;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Time getEstimatedTimeOfDepature() {
		return mEstimatedTimeOfDepature;
	}

	/**
	 *	@generated 
	 */
	public void setEstimatedTimeOfArrival(Time _estimatedTimeOfArrival) {
		Notification<Time> notification = basicSet(mEstimatedTimeOfArrival, _estimatedTimeOfArrival, Iec61174Package.Literals.RouteSchedule_estimatedTimeOfArrival);
		mEstimatedTimeOfArrival = _estimatedTimeOfArrival;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Time getEstimatedTimeOfArrival() {
		return mEstimatedTimeOfArrival;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "RouteScheduleImpl{" +
		"}";
	}
}
