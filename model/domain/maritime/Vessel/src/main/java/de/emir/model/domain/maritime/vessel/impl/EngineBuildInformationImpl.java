package de.emir.model.domain.maritime.vessel.impl;

import de.emir.model.domain.maritime.vessel.EngineBuildInformation;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = EngineBuildInformation.class)
public class EngineBuildInformationImpl extends UObjectImpl implements EngineBuildInformation  
{
	
	
	/**
	 *	@generated 
	 */
	private String mModel = "";
	/**
	 *	@generated 
	 */
	private RSIdentifier mManufactor = null;
	/**
	 *	@generated 
	 */
	private Time mBuildYear = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public EngineBuildInformationImpl(){
		super();
		//set the default values and assign them to this instance 
		setManufactor(mManufactor);
		setBuildYear(mBuildYear);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public EngineBuildInformationImpl(final EngineBuildInformation _copy) {
		mModel = _copy.getModel();
		mManufactor = _copy.getManufactor();
		mBuildYear = _copy.getBuildYear();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public EngineBuildInformationImpl(String _model, RSIdentifier _manufactor, Time _buildYear) {
		mModel = _model;
		mManufactor = _manufactor; 
		mBuildYear = _buildYear; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.EngineBuildInformation;
	}

	/**
	 *	@generated 
	 */
	public void setModel(String _model) {
		if (needNotification(VesselPackage.Literals.EngineBuildInformation_model)){
			String _oldValue = mModel;
			mModel = _model;
			notify(_oldValue, mModel, VesselPackage.Literals.EngineBuildInformation_model, NotificationType.SET);
		}else{
			mModel = _model;
		}
	}

	/**
	 *	@generated 
	 */
	public String getModel() {
		return mModel;
	}

	/**
	 *	@generated 
	 */
	public void setManufactor(RSIdentifier _manufactor) {
		Notification<RSIdentifier> notification = basicSet(mManufactor, _manufactor, VesselPackage.Literals.EngineBuildInformation_manufactor);
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
	public void setBuildYear(Time _buildYear) {
		Notification<Time> notification = basicSet(mBuildYear, _buildYear, VesselPackage.Literals.EngineBuildInformation_buildYear);
		mBuildYear = _buildYear;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Time getBuildYear() {
		return mBuildYear;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "EngineBuildInformationImpl{" +
		" model = " + getModel() + 
		"}";
	}
}
