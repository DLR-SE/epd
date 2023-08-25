package de.emir.model.domain.maritime.vessel.impl;

import de.emir.model.domain.maritime.vessel.VesselBuildInformation;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.units.Time;
import de.emir.model.domain.maritime.vessel.impl.VesselCharacteristicImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = VesselBuildInformation.class)
public class VesselBuildInformationImpl extends VesselCharacteristicImpl implements VesselBuildInformation  
{
	
	
	/**
	 *	@generated 
	 */
	private String mHullNumber = "";
	/**
	 *	@generated 
	 */
	private RSIdentifier mManufactor = null;
	/**
	 *	@generated 
	 */
	private Time mBuildTime = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public VesselBuildInformationImpl(){
		super();
		//set the default values and assign them to this instance 
		setManufactor(mManufactor);
		setBuildTime(mBuildTime);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public VesselBuildInformationImpl(final VesselBuildInformation _copy) {
		super(_copy);
		mHullNumber = _copy.getHullNumber();
		mManufactor = _copy.getManufactor();
		mBuildTime = _copy.getBuildTime();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public VesselBuildInformationImpl(String _hullNumber, RSIdentifier _manufactor, Time _buildTime) {
		super();
		mHullNumber = _hullNumber;
		mManufactor = _manufactor; 
		mBuildTime = _buildTime; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.VesselBuildInformation;
	}

	/**
	 *	@generated 
	 */
	public void setHullNumber(String _hullNumber) {
		if (needNotification(VesselPackage.Literals.VesselBuildInformation_hullNumber)){
			String _oldValue = mHullNumber;
			mHullNumber = _hullNumber;
			notify(_oldValue, mHullNumber, VesselPackage.Literals.VesselBuildInformation_hullNumber, NotificationType.SET);
		}else{
			mHullNumber = _hullNumber;
		}
	}

	/**
	 *	@generated 
	 */
	public String getHullNumber() {
		return mHullNumber;
	}

	/**
	 *	@generated 
	 */
	public void setManufactor(RSIdentifier _manufactor) {
		Notification<RSIdentifier> notification = basicSet(mManufactor, _manufactor, VesselPackage.Literals.VesselBuildInformation_manufactor);
		mManufactor = _manufactor;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public RSIdentifier getManufactor() {
		return mManufactor;
	}

	/**
	 *	@generated 
	 */
	public void setBuildTime(Time _buildTime) {
		Notification<Time> notification = basicSet(mBuildTime, _buildTime, VesselPackage.Literals.VesselBuildInformation_buildTime);
		mBuildTime = _buildTime;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Time getBuildTime() {
		return mBuildTime;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "VesselBuildInformationImpl{" +
		" hullNumber = " + getHullNumber() + 
		"}";
	}
}
