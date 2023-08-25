package de.emir.model.domain.maritime.vessel.impl;

import de.emir.model.domain.maritime.vessel.NavigationInformation;
import de.emir.model.domain.maritime.vessel.NavigationStatus;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.domain.maritime.vessel.impl.VesselCharacteristicImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = NavigationInformation.class)
public class NavigationInformationImpl extends VesselCharacteristicImpl implements NavigationInformation  
{
	
	
	/**
	 *	@generated 
	 */
	private NavigationStatus mStatus = null;

	/**
	 *	Default constructor
	 *	@generated
	 */
	public NavigationInformationImpl(){
		super();
		//set the default values and assign them to this instance 
		setStatus(mStatus);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public NavigationInformationImpl(final NavigationInformation _copy) {
		super(_copy);
		mStatus = _copy.getStatus();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public NavigationInformationImpl(NavigationStatus _status) {
		super();
		mStatus = _status; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.NavigationInformation;
	}

	/**
	 *	@generated 
	 */
	public void setStatus(NavigationStatus _status) {
		if (needNotification(VesselPackage.Literals.NavigationInformation_status)){
			NavigationStatus _oldValue = mStatus;
			mStatus = _status;
			notify(_oldValue, mStatus, VesselPackage.Literals.NavigationInformation_status, NotificationType.SET);
		}else{
			mStatus = _status;
		}
	}

	/**
	 *	@generated 
	 */
	public NavigationStatus getStatus() {
		return mStatus;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "NavigationInformationImpl{" +
		"}";
	}
}
