package de.emir.model.domain.maritime.vessel.impl;

import java.util.List;

import de.emir.model.application.vehicle.impl.WatercraftImpl;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.domain.maritime.vessel.VesselType;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.physics.Capability;
import de.emir.model.universal.physics.ICapability;
import de.emir.model.universal.physics.ICharacteristic;
import de.emir.model.universal.physics.Characteristic;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.physics.RelativeEngineering2D;
import de.emir.model.universal.spatial.Pose;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**

 *  a nautical term for all kinds of craft designed for transportation on water, such as ships or boats.
 * @generated 
 */
@UMLImplementation(classifier = Vessel.class)
public class VesselImpl extends WatercraftImpl implements Vessel  
{
	
	
	/**
	 *	@generated 
	 */
	private long mMmsi = 0;
	/**
	 *	@generated 
	 */
	private long mImo = 0;
	/**
	 *	@generated 
	 */
	private String mCallSign = "";
	/**
	 *	@generated 
	 */
	private VesselType mType = null;

	/**
	 *	Default constructor
	 *	@generated
	 */
	public VesselImpl(){
		super();
		//set the default values and assign them to this instance 
		setType(mType);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public VesselImpl(final Vessel _copy) {
		super(_copy);
		mMmsi = _copy.getMmsi();
		mImo = _copy.getImo();
		mCallSign = _copy.getCallSign();
		mType = _copy.getType();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public VesselImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, RelativeEngineering2D _ownedCoordinateSystem, Pose _pose, List<ICharacteristic> _characteristics, List<ICapability> _capabilities, List<PhysicalObject> _children, long _mmsi, long _imo, String _callSign, VesselType _type) {
		super(_name,_allias,_remarks,_observers,_identifier,_ownedCoordinateSystem,_pose,_characteristics,_capabilities,_children);
		mMmsi = _mmsi;
		mImo = _imo;
		mCallSign = _callSign;
		mType = _type; 
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.Vessel;
	}

	/**
	 *	@generated 
	 */
	public void setMmsi(long _mmsi) {
		if (needNotification(VesselPackage.Literals.Vessel_mmsi)){
			long _oldValue = mMmsi;
			mMmsi = _mmsi;
			notify(_oldValue, mMmsi, VesselPackage.Literals.Vessel_mmsi, NotificationType.SET);
		}else{
			mMmsi = _mmsi;
		}
	}

	/**
	 *	@generated 
	 */
	public long getMmsi() {
		return mMmsi;
	}

	/**
	 *	@generated 
	 */
	public void setImo(long _imo) {
		if (needNotification(VesselPackage.Literals.Vessel_imo)){
			long _oldValue = mImo;
			mImo = _imo;
			notify(_oldValue, mImo, VesselPackage.Literals.Vessel_imo, NotificationType.SET);
		}else{
			mImo = _imo;
		}
	}

	/**
	 *	@generated 
	 */
	public long getImo() {
		return mImo;
	}

	/**
	 *	@generated 
	 */
	public void setCallSign(String _callSign) {
		if (needNotification(VesselPackage.Literals.Vessel_callSign)){
			String _oldValue = mCallSign;
			mCallSign = _callSign;
			notify(_oldValue, mCallSign, VesselPackage.Literals.Vessel_callSign, NotificationType.SET);
		}else{
			mCallSign = _callSign;
		}
	}

	/**
	 *	@generated 
	 */
	public String getCallSign() {
		return mCallSign;
	}

	/**
	 *	@generated 
	 */
	public void setType(VesselType _type) {
		if (needNotification(VesselPackage.Literals.Vessel_type)){
			VesselType _oldValue = mType;
			mType = _type;
			notify(_oldValue, mType, VesselPackage.Literals.Vessel_type, NotificationType.SET);
		}else{
			mType = _type;
		}
	}

	/**
	 *	@generated 
	 */
	public VesselType getType() {
		return mType;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "VesselImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		" mmsi = " + getMmsi() + 
		" imo = " + getImo() + 
		" callSign = " + getCallSign() + 
		"}";
	}
}
