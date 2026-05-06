package de.emir.model.domain.maritime.vessel.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.domain.maritime.vessel.AngularDistance;
import de.emir.model.domain.maritime.vessel.ReferencedDistance;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;
import de.emir.tuml.ucore.runtime.Notification;
import java.util.List;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**

    * Represents a measured distance from a vessel to detected objects surrounding the vessel.
    * The ReferencedDistance stores the distance from the point of reference (i.e. the position of
    * the detection sensor in reference to the vessels coordinate system) to a
    * specific range which is defined by the minimum opening angle and maximum opening angle
    * which is measured based on the ships relative northern position.
 * @generated 
 */
@UMLImplementation(classifier = ReferencedDistance.class)
public class ReferencedDistanceImpl extends UObjectImpl implements ReferencedDistance  
{
	
	
	/**
	 The reference point of measurement. This is the position of the detection sensor in reference to the vessels coordinate system. 
	 * @generated 
	 */
	private PhysicalObject mReference = null;
	/**
	 *	@generated 
	 */
	private List<AngularDistance> mAngularDistances = null;
	/**
	 Identifier of the sensor which detected the distance. 
	 * @generated 
	 */
	private String mSource = "";

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public ReferencedDistanceImpl(PhysicalObject _reference, List<AngularDistance> _angularDistances, String _source) {
		mReference = _reference; 
		mAngularDistances = _angularDistances; 
		mSource = _source; 
	}
	/**
	 *	Default constructor
	 *	@generated
	 */
	public ReferencedDistanceImpl(){
		super();
		//set the default values and assign them to this instance 
		setReference(mReference);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public ReferencedDistanceImpl(final ReferencedDistance _copy) {
		mReference = _copy.getReference();
		mAngularDistances = _copy.getAngularDistances();
		mSource = _copy.getSource();
	}

	/**
	 *	@generated 
	 */
	public List<AngularDistance> getAngularDistances() {
		if (mAngularDistances == null) {
			mAngularDistances = new UContainmentList<AngularDistance>(this, VesselPackage.theInstance.getReferencedDistance_angularDistances()); 
		}
		return mAngularDistances;
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.ReferencedDistance;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 The reference point of measurement. This is the position of the detection sensor in reference to the vessels coordinate system. 
	 * @generated 
	 */
	public void setReference(PhysicalObject _reference) {
		Notification<PhysicalObject> notification = basicSet(mReference, _reference, VesselPackage.Literals.ReferencedDistance_reference);
		mReference = _reference;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 The reference point of measurement. This is the position of the detection sensor in reference to the vessels coordinate system. 
	 * @generated 
	 */
	public PhysicalObject getReference() {
		return mReference;
	}
	/**
	 Identifier of the sensor which detected the distance. 
	 * @generated 
	 */
	public void setSource(String _source) {
		if (needNotification(VesselPackage.Literals.ReferencedDistance_source)){
			String _oldValue = mSource;
			mSource = _source;
			notify(_oldValue, _source, VesselPackage.Literals.ReferencedDistance_source, NotificationType.SET);
		}else{
			mSource = _source;
		}
	}
	/**
	 Identifier of the sensor which detected the distance. 
	 * @generated 
	 */
	public String getSource() {
		return mSource;
	}
	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "ReferencedDistanceImpl{" +
		"}";
	}
}
