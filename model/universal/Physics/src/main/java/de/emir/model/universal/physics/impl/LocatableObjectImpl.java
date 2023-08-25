package de.emir.model.universal.physics.impl;

import java.util.List;

import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.core.impl.IdentifiedObjectImpl;
import de.emir.model.universal.physics.LocatableObject;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.model.universal.physics.RelativeEngineering2D;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.physics.impl.RelativeEngineering2DImpl;
import de.emir.model.universal.spatial.impl.PoseImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 Each object that is located somehow shall extend this object
 * this has been separated from the PhysicalObject, since also non physical Objects 
 * like EnvironmentFactors or Information could be located somehow and may be handled the same
 * way (regarding their location operations) as a physical object. 
 * @generated 
 */
@UMLImplementation(classifier = LocatableObject.class)
abstract public class LocatableObjectImpl extends IdentifiedObjectImpl implements LocatableObject  
{
	
	
	/**
	 Each locatable objects builds its own coordinate system, whereas the objects center is also 
	 * the center of the reference system.
	 *
	 * @generated 
	 */
	private RelativeEngineering2D mOwnedCoordinateSystem = new RelativeEngineering2DImpl();
	/**
	
	 * the pose is set to readonly to indicate, that an object that has an location 
	 * not necessarily can move, e.g. change its pose. For example a building does not move but is locatable
	 * @generated 
	 */
	private Pose mPose = new PoseImpl();

	/**
	 *	Default constructor
	 *	@generated
	 */
	public LocatableObjectImpl(){
		super();
		//set the default values and assign them to this instance 
		setOwnedCoordinateSystem(mOwnedCoordinateSystem);
		setPose(mPose);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public LocatableObjectImpl(final LocatableObject _copy) {
		super(_copy);
		mOwnedCoordinateSystem = _copy.getOwnedCoordinateSystem();
		mPose = _copy.getPose();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public LocatableObjectImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, RelativeEngineering2D _ownedCoordinateSystem, Pose _pose) {
		super(_name,_allias,_remarks,_observers,_identifier);
		mOwnedCoordinateSystem = _ownedCoordinateSystem; 
		mPose = _pose; 
	}

	/**
	 Each locatable objects builds its own coordinate system, whereas the objects center is also 
	 * the center of the reference system.
	 *
	 * @generated 
	 */
	public void setOwnedCoordinateSystem(RelativeEngineering2D _ownedCoordinateSystem) {
		Notification<RelativeEngineering2D> notification = basicSet(mOwnedCoordinateSystem, _ownedCoordinateSystem, PhysicsPackage.Literals.LocatableObject_ownedCoordinateSystem);
		mOwnedCoordinateSystem = _ownedCoordinateSystem;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return PhysicsPackage.Literals.LocatableObject;
	}

	/**
	 Each locatable objects builds its own coordinate system, whereas the objects center is also 
	 * the center of the reference system.
	 *
	 * @generated 
	 */
	public RelativeEngineering2D getOwnedCoordinateSystem() {
		return mOwnedCoordinateSystem;
	}

	/**
	
	 * the pose is set to readonly to indicate, that an object that has an location 
	 * not necessarily can move, e.g. change its pose. For example a building does not move but is locatable
	 * @generated 
	 */
	public void setPose(Pose _pose) {
		Notification<Pose> notification = basicSet(mPose, _pose, PhysicsPackage.Literals.LocatableObject_pose);
		mPose = _pose;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	
	 * the pose is set to readonly to indicate, that an object that has an location 
	 * not necessarily can move, e.g. change its pose. For example a building does not move but is locatable
	 * @generated 
	 */
	public Pose getPose() {
		return mPose;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "LocatableObjectImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
