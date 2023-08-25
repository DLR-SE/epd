package de.emir.model.universal.physics.impl;

import java.util.List;

import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.model.universal.physics.RelativeEngineering2D;
import de.emir.model.universal.physics.Wind;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.physics.impl.EnvironmentFactorImpl;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.units.Velocity;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.NotificationType;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Wind.class)
public class WindImpl extends EnvironmentFactorImpl implements Wind  
{
	
	
	/**
	 *	@generated 
	 */
	private Velocity mVelocity = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public WindImpl(){
		super();
		//set the default values and assign them to this instance 
		setVelocity(mVelocity);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public WindImpl(final Wind _copy) {
		super(_copy);
		mVelocity = _copy.getVelocity();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public WindImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, RelativeEngineering2D _ownedCoordinateSystem, Pose _pose, Geometry _areaOfInfluence, Velocity _velocity) {
		super(_name,_allias,_remarks,_observers,_identifier,_ownedCoordinateSystem,_pose,_areaOfInfluence);
		mVelocity = _velocity; 
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return PhysicsPackage.Literals.Wind;
	}

	/**
	 *	@generated 
	 */
	public void setVelocity(Velocity _velocity) {
		Notification<Velocity> notification = basicSet(mVelocity, _velocity, PhysicsPackage.Literals.Wind_velocity);
		mVelocity = _velocity;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Velocity getVelocity() {
		return mVelocity;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "WindImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
