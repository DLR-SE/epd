package de.emir.model.universal.physics.impl;

import java.util.List;

import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.CoordinateSystem;
import de.emir.model.universal.crs.LocalCRS;
import de.emir.model.universal.crs.impl.Engineering2DImpl;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.physics.LocatableObject;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.model.universal.physics.RelativeEngineering2D;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.Euler;
import de.emir.model.universal.units.impl.AngleImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.utils.UCoreUtils;


/**
 Convinience CRS that follows the referenced LocatableObject and thus creates
 * a stable coordinate reference system on top of the referenced object. 
 * @generated 
 */
@UMLImplementation(classifier = RelativeEngineering2D.class)
public class RelativeEngineering2DImpl extends Engineering2DImpl implements RelativeEngineering2D  
{
	
	
	/**
	 locatable object, used to resolve the origin and orientation offset for this local coordinate reference system 
	 * @generated 
	 */
	private LocatableObject mReference = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public RelativeEngineering2DImpl(){
		super();
		//set the default values and assign them to this instance 
		setReference(mReference);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public RelativeEngineering2DImpl(final RelativeEngineering2D _copy) {
		super(_copy);
		mReference = _copy.getReference();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public RelativeEngineering2DImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, CoordinateSystem _cs, Vector _origin, List<Double> _orientationOffset, LocatableObject _reference) {
		super(_name,_allias,_remarks,_observers,_identifier,_cs,_origin,_orientationOffset);
		mReference = _reference; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return PhysicsPackage.Literals.RelativeEngineering2D;
	}

	/**
	 locatable object, used to resolve the origin and orientation offset for this local coordinate reference system 
	 * @generated 
	 */
	public void setReference(LocatableObject _reference) {
		Notification<LocatableObject> notification = basicSet(mReference, _reference, PhysicsPackage.Literals.RelativeEngineering2D_reference);
		mReference = _reference;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 locatable object, used to resolve the origin and orientation offset for this local coordinate reference system 
	 * @generated not
	 */
	@Override
	public LocatableObject getReference() {
		if (mReference == null){
			setReference(UCoreUtils.getFirstParent(this, LocatableObject.class));
		}
		return mReference;
	}
	
	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "RelativeEngineering2DImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		" orientationOffset = " + getOrientationOffset() + 
		"}";
	}
	
	@Override
	public List<Double> getOrientationOffset() {
		List<Double> ori = super.getOrientationOffset();
		if (getReference() == null ){
			return ori;
		}
		Pose refPose = getReference().getPose();
		if (refPose == null)
			return ori;
		double offsetRadian = 0;
		//first get the orientation offset of the CRS used by the referenced pose
		Coordinate refCoord = refPose.getCoordinate();
		if (refCoord != null){
			CoordinateReferenceSystem refCRS = refCoord.getCrs();
			if (refCRS != null && refCRS instanceof LocalCRS)
				offsetRadian = ((LocalCRS)refCRS).getOrientationOffset().get(0);
		}
		//if the object is rotated itself, this will superposition the offset
		if (refPose.getOrientation() != null){
			//TODO: is that true for all CRS?
			Euler e = refPose.getOrientation().toEuler();
			if (e.getZ() != null)
				offsetRadian += e.getZ().getAs(AngleUnit.RADIAN);
		}
		while(offsetRadian < 0) offsetRadian += AngleImpl.PI_2; //do the normalisation
		while(offsetRadian > AngleImpl.PI_2) offsetRadian -= AngleImpl.PI_2; 
		ori.set(0, offsetRadian); //e.getZ().getAs(AngleUnit.RADIAN));
		return ori;
	}
	
	
	@Override
	public Vector getOrigin() {
		if (getReference() == null ){
			return super.getOrigin();
		}
		Pose refPose = getReference().getPose();
		if (refPose == null)
			return super.getOrigin();
		
		Coordinate c = refPose.getCoordinate();
		if (c == null){
			return super.getOrigin();
		}
		
		if (c.getCrs() == this)
			return null; //avoid stackoverflow
		
		Vector2D ori = (Vector2D) super.getOrigin();
		
		if (ori.getX() != c.getLatitude())
			ori.setX(c.getLatitude());
		if (ori.getY() != c.getLongitude())
			ori.setY(c.getLongitude());
		return ori;
	}
}
