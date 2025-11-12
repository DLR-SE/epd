package de.emir.model.application.sensor.ais.impl;

import java.util.List;

import de.emir.model.application.sense.impl.EmitterImpl;
import de.emir.model.application.sensor.ais.AISEmitter;
import de.emir.model.application.sensor.ais.AisPackage;
import de.emir.model.application.sensor.ais.AISEmitterClass;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.physics.Capability;
import de.emir.model.universal.physics.ICapability;
import de.emir.model.universal.physics.ICharacteristic;
import de.emir.model.universal.physics.Characteristic;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.physics.RelativeEngineering2D;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.units.Time;
import de.emir.model.universal.units.TimeUnit;
import de.emir.model.universal.units.impl.TimeImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.model.universal.spatial.Pose;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**

 * An AISEmitter is used to emit / send AISMessages. 
 * @note we distinguish between sensor (AISSensor) and emitter since not all available sensors are able to send own messages, e.g. small vessels like sailing yacht
 * (in fact this would lead to more traffic as the AIS network could handle)
 * @generated 
 */
@UMLImplementation(classifier = AISEmitter.class)
public class AISEmitterImpl extends EmitterImpl implements AISEmitter  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public AISEmitterImpl(){
		super();
		//set the default values and assign them to this instance 
		setFixedAISInvterval(mFixedAISInvterval);
		setAisEmitterClass(mAisEmitterClass);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public AISEmitterImpl(final AISEmitter _copy) {
		super(_copy);
		mFixedAISInvterval = _copy.getFixedAISInvterval();
		mSendInFixedInterval = _copy.getSendInFixedInterval();
		mAisEmitterClass = _copy.getAisEmitterClass();
	}

	/**
	 *	@generated 
	 */
	private AISEmitterClass mAisEmitterClass = null;

	/**
	 *	@generated_not
	 */
	private Time mFixedAISInvterval = new TimeImpl(1, TimeUnit.SECOND);

	/**
	 *	@generated 
	 */
	private boolean mSendInFixedInterval = false;

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return AisPackage.Literals.AISEmitter;
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public AISEmitterImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, RelativeEngineering2D _ownedCoordinateSystem, Pose _pose, List<ICharacteristic> _characteristics, List<ICapability> _capabilities, List<PhysicalObject> _children, Geometry _areaOfInfluence, Time _fixedAISInvterval, boolean _sendInFixedInterval, AISEmitterClass _aisEmitterClass) {
		super(_name,_allias,_remarks,_observers,_identifier,_ownedCoordinateSystem,_pose,_characteristics,_capabilities,_children,_areaOfInfluence);
		mFixedAISInvterval = _fixedAISInvterval; 
		mSendInFixedInterval = _sendInFixedInterval; 
		mAisEmitterClass = _aisEmitterClass; 
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "AISEmitterImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}

	/**
	 *	@generated 
	 */
	public Time getFixedAISInvterval() {
		return mFixedAISInvterval;
	}

	/**
	 *	@generated 
	 */
	public void setFixedAISInvterval(Time _fixedAISInvterval) {
		Notification<Time> notification = basicSet(mFixedAISInvterval, _fixedAISInvterval, AisPackage.Literals.AISEmitter_fixedAISInvterval);
		mFixedAISInvterval = _fixedAISInvterval;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public void setAisEmitterClass(AISEmitterClass _aisEmitterClass) {
		if (needNotification(AisPackage.Literals.AISEmitter_aisEmitterClass)){
			AISEmitterClass _oldValue = mAisEmitterClass;
			mAisEmitterClass = _aisEmitterClass;
			notify(_oldValue, mAisEmitterClass, AisPackage.Literals.AISEmitter_aisEmitterClass, NotificationType.SET);
		}else{
			mAisEmitterClass = _aisEmitterClass;
		}
	}

	/**
	 *	@generated 
	 */
	public AISEmitterClass getAisEmitterClass() {
		return mAisEmitterClass;
	}

	/**
	 *	@generated 
	 */
	public void setSendInFixedInterval(boolean _sendInFixedInterval) {
		if (needNotification(AisPackage.Literals.AISEmitter_sendInFixedInterval)){
			boolean _oldValue = mSendInFixedInterval;
			mSendInFixedInterval = _sendInFixedInterval;
			notify(_oldValue, mSendInFixedInterval, AisPackage.Literals.AISEmitter_sendInFixedInterval, NotificationType.SET);
		}else{
			mSendInFixedInterval = _sendInFixedInterval;
		}
	}

	/**
	 *	@generated 
	 */
	public boolean getSendInFixedInterval() {
		return mSendInFixedInterval;
	}
}
