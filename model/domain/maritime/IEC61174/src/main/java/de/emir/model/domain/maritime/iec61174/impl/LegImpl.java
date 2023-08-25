package de.emir.model.domain.maritime.iec61174.impl;

import de.emir.model.domain.maritime.iec61174.Iec61174Package;
import de.emir.model.domain.maritime.iec61174.Leg;
import de.emir.model.domain.maritime.iec61174.LegGeometryType;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.Speed;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Leg.class)
public class LegImpl extends UObjectImpl implements Leg  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public LegImpl(){
		super();
		//set the default values and assign them to this instance 
		setStarboardXTD(mStarboardXTD);
		setPortsideXTD(mPortsideXTD);
		setSafetyContour(mSafetyContour);
		setSafetyDepth(mSafetyDepth);
		setGeometryType(mGeometryType);
		setPlanSpeedMin(mPlanSpeedMin);
		setPlanSpeedMax(mPlanSpeedMax);
		setDraughtForward(mDraughtForward);
		setDraughtAft(mDraughtAft);
	}


	/**
	 *	@generated 
	 */
	private Distance mPortsideXTD = null;
	/**
	 *	@generated 
	 */
	private Distance mSafetyContour = null;
	/**
	 *	@generated 
	 */
	private LegGeometryType mGeometryType = null;
	/**
	 *	@generated 
	 */
	private Distance mSafetyDepth = null;
	/**
	 *	@generated 
	 */
	private Speed mPlanSpeedMin = null;


	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public LegImpl(final Leg _copy) {
		mLegInfo = _copy.getLegInfo();
		mLegNote1 = _copy.getLegNote1();
		mLegNote2 = _copy.getLegNote2();
		mStarboardXTD = _copy.getStarboardXTD();
		mPortsideXTD = _copy.getPortsideXTD();
		mSafetyContour = _copy.getSafetyContour();
		mSafetyDepth = _copy.getSafetyDepth();
		mGeometryType = _copy.getGeometryType();
		mPlanSpeedMin = _copy.getPlanSpeedMin();
		mPlanSpeedMax = _copy.getPlanSpeedMax();
		mDraughtForward = _copy.getDraughtForward();
		mDraughtAft = _copy.getDraughtAft();
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return Iec61174Package.Literals.Leg;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "LegImpl{" +
		" legInfo = " + getLegInfo() + 
		" legNote1 = " + getLegNote1() + 
		" legNote2 = " + getLegNote2() + 
		"}";
	}


	/**
	 *	@generated 
	 */
	private Distance mDraughtAft = null;
	/**
	 *	@generated 
	 */
	private String mLegNote1 = "";
	/**
	 *	@generated 
	 */
	private String mLegNote2 = "";


	/**
	 *	@generated 
	 */
	public void setStarboardXTD(Distance _starboardXTD) {
		Notification<Distance> notification = basicSet(mStarboardXTD, _starboardXTD, Iec61174Package.Literals.Leg_starboardXTD);
		mStarboardXTD = _starboardXTD;
		if (notification != null){
			dispatchNotification(notification);
		}
	}


	/**
	 *	@generated 
	 */
	private String mLegInfo = "";


	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public LegImpl(Distance _starboardXTD, Distance _portsideXTD, Distance _safetyContour, Distance _safetyDepth, LegGeometryType _geometryType, Speed _planSpeedMin, Speed _planSpeedMax, Distance _draughtForward, Distance _draughtAft, String _legInfo, String _legNote1, String _legNote2) {
		mLegInfo = _legInfo;
		mLegNote1 = _legNote1;
		mLegNote2 = _legNote2;
		mStarboardXTD = _starboardXTD; 
		mPortsideXTD = _portsideXTD; 
		mSafetyContour = _safetyContour; 
		mSafetyDepth = _safetyDepth; 
		mGeometryType = _geometryType; 
		mPlanSpeedMin = _planSpeedMin; 
		mPlanSpeedMax = _planSpeedMax; 
		mDraughtForward = _draughtForward; 
		mDraughtAft = _draughtAft; 
	}

	/**
	 *	@generated 
	 */
	private Distance mStarboardXTD = null;
	/**
	 *	@generated 
	 */
	private Distance mDraughtForward = null;


	/**
	 *	@generated 
	 */
	private Speed mPlanSpeedMax = null;


	/**
	 *	@generated 
	 */
	public String getLegInfo() {
		return mLegInfo;
	}

	/**
	 *	@generated 
	 */
	public Distance getDraughtAft() {
		return mDraughtAft;
	}

	/**
	 *	@generated 
	 */
	public LegGeometryType getGeometryType() {
		return mGeometryType;
	}

	/**
	 *	@generated 
	 */
	public Speed getPlanSpeedMin() {
		return mPlanSpeedMin;
	}

	/**
	 *	@generated 
	 */
	public Distance getStarboardXTD() {
		return mStarboardXTD;
	}

	/**
	 *	@generated 
	 */
	public Distance getSafetyDepth() {
		return mSafetyDepth;
	}

	/**
	 *	@generated 
	 */
	public void setPortsideXTD(Distance _portsideXTD) {
		Notification<Distance> notification = basicSet(mPortsideXTD, _portsideXTD, Iec61174Package.Literals.Leg_portsideXTD);
		mPortsideXTD = _portsideXTD;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Distance getPortsideXTD() {
		return mPortsideXTD;
	}

	/**
	 *	@generated 
	 */
	public Distance getSafetyContour() {
		return mSafetyContour;
	}

	/**
	 *	@generated 
	 */
	public void setSafetyDepth(Distance _safetyDepth) {
		Notification<Distance> notification = basicSet(mSafetyDepth, _safetyDepth, Iec61174Package.Literals.Leg_safetyDepth);
		mSafetyDepth = _safetyDepth;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public void setGeometryType(LegGeometryType _geometryType) {
		if (needNotification(Iec61174Package.Literals.Leg_geometryType)){
			LegGeometryType _oldValue = mGeometryType;
			mGeometryType = _geometryType;
			notify(_oldValue, mGeometryType, Iec61174Package.Literals.Leg_geometryType, NotificationType.SET);
		}else{
			mGeometryType = _geometryType;
		}
	}

	/**
	 *	@generated 
	 */
	public void setPlanSpeedMax(Speed _planSpeedMax) {
		Notification<Speed> notification = basicSet(mPlanSpeedMax, _planSpeedMax, Iec61174Package.Literals.Leg_planSpeedMax);
		mPlanSpeedMax = _planSpeedMax;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Speed getPlanSpeedMax() {
		return mPlanSpeedMax;
	}

	/**
	 *	@generated 
	 */
	public void setDraughtAft(Distance _draughtAft) {
		Notification<Distance> notification = basicSet(mDraughtAft, _draughtAft, Iec61174Package.Literals.Leg_draughtAft);
		mDraughtAft = _draughtAft;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public void setLegNote1(String _legNote1) {
		if (needNotification(Iec61174Package.Literals.Leg_legNote1)){
			String _oldValue = mLegNote1;
			mLegNote1 = _legNote1;
			notify(_oldValue, mLegNote1, Iec61174Package.Literals.Leg_legNote1, NotificationType.SET);
		}else{
			mLegNote1 = _legNote1;
		}
	}

	/**
	 *	@generated 
	 */
	public String getLegNote1() {
		return mLegNote1;
	}

	/**
	 *	@generated 
	 */
	public void setLegNote2(String _legNote2) {
		if (needNotification(Iec61174Package.Literals.Leg_legNote2)){
			String _oldValue = mLegNote2;
			mLegNote2 = _legNote2;
			notify(_oldValue, mLegNote2, Iec61174Package.Literals.Leg_legNote2, NotificationType.SET);
		}else{
			mLegNote2 = _legNote2;
		}
	}

	/**
	 *	@generated 
	 */
	public void setSafetyContour(Distance _safetyContour) {
		Notification<Distance> notification = basicSet(mSafetyContour, _safetyContour, Iec61174Package.Literals.Leg_safetyContour);
		mSafetyContour = _safetyContour;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public void setPlanSpeedMin(Speed _planSpeedMin) {
		Notification<Speed> notification = basicSet(mPlanSpeedMin, _planSpeedMin, Iec61174Package.Literals.Leg_planSpeedMin);
		mPlanSpeedMin = _planSpeedMin;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public String getLegNote2() {
		return mLegNote2;
	}

	/**
	 *	@generated 
	 */
	public void setLegInfo(String _legInfo) {
		if (needNotification(Iec61174Package.Literals.Leg_legInfo)){
			String _oldValue = mLegInfo;
			mLegInfo = _legInfo;
			notify(_oldValue, mLegInfo, Iec61174Package.Literals.Leg_legInfo, NotificationType.SET);
		}else{
			mLegInfo = _legInfo;
		}
	}

	/**
	 *	@generated 
	 */
	public Distance getDraughtForward() {
		return mDraughtForward;
	}

	/**
	 *	@generated 
	 */
	public void setDraughtForward(Distance _draughtForward) {
		Notification<Distance> notification = basicSet(mDraughtForward, _draughtForward, Iec61174Package.Literals.Leg_draughtForward);
		mDraughtForward = _draughtForward;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
}
