package de.emir.model.domain.maritime.vessel.impl;

import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.domain.maritime.vessel.WatercraftHull;
import de.emir.model.universal.units.Length;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = WatercraftHull.class)
public class WatercraftHullImpl extends UObjectImpl implements WatercraftHull  
{
	
	
	/**
	
	 * Beam or breadth (B) is the width of the hull. (ex: BWL is the maximum beam at the waterline)
	 * @generated 
	 */
	private Length mBeam = null;
	/**
	
	 * (LWL) is the length from the forwardmost point of the waterline measured in profile to the stern-most point of the waterline.
	 * @generated 
	 */
	private Length mLengthAtWaterline = null;
	/**
	
	 * (LOA) is the extreme length from one end to the other
	 * @generated 
	 */
	private Length mOverAllLength = null;
	/**
	
	 * Draft (d) or (T) is the vertical distance from the bottom of the keel to the waterline.
	 * @generated 
	 */
	private Length mDraft = null;
	/**
	
	 * (D) is the vertical distance measured from the top of the keel to the underside of the upper deck at side
	 * @generated 
	 */
	private Length mMouldedDepth = null;
	/**
	
	 * Freeboard (FB) is depth plus the height of the keel structure minus draft.
	 * @generated 
	 */
	private Length mFreeboard = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public WatercraftHullImpl(){
		super();
		//set the default values and assign them to this instance 
		setBeam(mBeam);
		setLengthAtWaterline(mLengthAtWaterline);
		setOverAllLength(mOverAllLength);
		setDraft(mDraft);
		setMouldedDepth(mMouldedDepth);
		setFreeboard(mFreeboard);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public WatercraftHullImpl(final WatercraftHull _copy) {
		mBeam = _copy.getBeam();
		mLengthAtWaterline = _copy.getLengthAtWaterline();
		mOverAllLength = _copy.getOverAllLength();
		mDraft = _copy.getDraft();
		mMouldedDepth = _copy.getMouldedDepth();
		mFreeboard = _copy.getFreeboard();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public WatercraftHullImpl(Length _beam, Length _lengthAtWaterline, Length _overAllLength, Length _draft, Length _mouldedDepth, Length _freeboard) {
		mBeam = _beam; 
		mLengthAtWaterline = _lengthAtWaterline; 
		mOverAllLength = _overAllLength; 
		mDraft = _draft; 
		mMouldedDepth = _mouldedDepth; 
		mFreeboard = _freeboard; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.WatercraftHull;
	}

	/**
	
	 * Beam or breadth (B) is the width of the hull. (ex: BWL is the maximum beam at the waterline)
	 * @generated 
	 */
	public void setBeam(Length _beam) {
		Notification<Length> notification = basicSet(mBeam, _beam, VesselPackage.Literals.WatercraftHull_beam);
		mBeam = _beam;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	
	 * Beam or breadth (B) is the width of the hull. (ex: BWL is the maximum beam at the waterline)
	 * @generated 
	 */
	public Length getBeam() {
		return mBeam;
	}

	/**
	
	 * (LWL) is the length from the forwardmost point of the waterline measured in profile to the stern-most point of the waterline.
	 * @generated 
	 */
	public void setLengthAtWaterline(Length _lengthAtWaterline) {
		Notification<Length> notification = basicSet(mLengthAtWaterline, _lengthAtWaterline, VesselPackage.Literals.WatercraftHull_lengthAtWaterline);
		mLengthAtWaterline = _lengthAtWaterline;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	
	 * (LWL) is the length from the forwardmost point of the waterline measured in profile to the stern-most point of the waterline.
	 * @generated 
	 */
	public Length getLengthAtWaterline() {
		return mLengthAtWaterline;
	}

	/**
	
	 * (LOA) is the extreme length from one end to the other
	 * @generated 
	 */
	public void setOverAllLength(Length _overAllLength) {
		Notification<Length> notification = basicSet(mOverAllLength, _overAllLength, VesselPackage.Literals.WatercraftHull_overAllLength);
		mOverAllLength = _overAllLength;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	
	 * (LOA) is the extreme length from one end to the other
	 * @generated 
	 */
	public Length getOverAllLength() {
		return mOverAllLength;
	}

	/**
	
	 * Draft (d) or (T) is the vertical distance from the bottom of the keel to the waterline.
	 * @generated 
	 */
	public void setDraft(Length _draft) {
		Notification<Length> notification = basicSet(mDraft, _draft, VesselPackage.Literals.WatercraftHull_draft);
		mDraft = _draft;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	
	 * Draft (d) or (T) is the vertical distance from the bottom of the keel to the waterline.
	 * @generated 
	 */
	public Length getDraft() {
		return mDraft;
	}

	/**
	
	 * (D) is the vertical distance measured from the top of the keel to the underside of the upper deck at side
	 * @generated 
	 */
	public void setMouldedDepth(Length _mouldedDepth) {
		Notification<Length> notification = basicSet(mMouldedDepth, _mouldedDepth, VesselPackage.Literals.WatercraftHull_mouldedDepth);
		mMouldedDepth = _mouldedDepth;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	
	 * (D) is the vertical distance measured from the top of the keel to the underside of the upper deck at side
	 * @generated 
	 */
	public Length getMouldedDepth() {
		return mMouldedDepth;
	}

	/**
	
	 * Freeboard (FB) is depth plus the height of the keel structure minus draft.
	 * @generated 
	 */
	public void setFreeboard(Length _freeboard) {
		Notification<Length> notification = basicSet(mFreeboard, _freeboard, VesselPackage.Literals.WatercraftHull_freeboard);
		mFreeboard = _freeboard;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	
	 * Freeboard (FB) is depth plus the height of the keel structure minus draft.
	 * @generated 
	 */
	public Length getFreeboard() {
		return mFreeboard;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "WatercraftHullImpl{" +
		"}";
	}
}
