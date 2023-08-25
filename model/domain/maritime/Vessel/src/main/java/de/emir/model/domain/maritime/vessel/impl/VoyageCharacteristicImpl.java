package de.emir.model.domain.maritime.vessel.impl;

import java.util.List;

import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.domain.maritime.vessel.VoyageCharacteristic;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.model.domain.maritime.vessel.impl.VesselCharacteristicImpl;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;


/**
 *	@generated 
 */
@UMLImplementation(classifier = VoyageCharacteristic.class)
public class VoyageCharacteristicImpl extends VesselCharacteristicImpl implements VoyageCharacteristic  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public VoyageCharacteristicImpl(){
		super();
		//set the default values and assign them to this instance 
		setActiveRoute(mActiveRoute);
	}



	/**
	 current active route or null, if no route is active. 
	 * @note this route shall point to a route inside the routes[*] list
	 * @generated 
	 */
	private Route mActiveRoute = null;



	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public VoyageCharacteristicImpl(final VoyageCharacteristic _copy) {
		super(_copy);
		mRoutes = _copy.getRoutes();
		mActiveRoute = _copy.getActiveRoute();
	}



	/**
	 all routes assigned to this Voyage, each route may have a name and a schedule 
	 * @generated 
	 */
	private List<Route> mRoutes = null;



	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public VoyageCharacteristicImpl(List<Route> _routes, Route _activeRoute) {
		super();
		mRoutes = _routes; 
		mActiveRoute = _activeRoute; 
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.VoyageCharacteristic;
	}

	/**
	 current active route or null, if no route is active. 
	 * @note this route shall point to a route inside the routes[*] list
	 * @generated 
	 */
	public void setActiveRoute(Route _activeRoute) {
		Notification<Route> notification = basicSet(mActiveRoute, _activeRoute, VesselPackage.Literals.VoyageCharacteristic_activeRoute);
		mActiveRoute = _activeRoute;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 current active route or null, if no route is active. 
	 * @note this route shall point to a route inside the routes[*] list
	 * @generated 
	 */
	public Route getActiveRoute() {
		return mActiveRoute;
	}

	/**
	 all routes assigned to this Voyage, each route may have a name and a schedule 
	 * @generated 
	 */
	public List<Route> getRoutes() {
		if (mRoutes == null) {
			mRoutes = new UContainmentList<Route>(this, VesselPackage.theInstance.getVoyageCharacteristic_routes()); 
		}
		return mRoutes;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "VoyageCharacteristicImpl{" +
		"}";
	}
}
