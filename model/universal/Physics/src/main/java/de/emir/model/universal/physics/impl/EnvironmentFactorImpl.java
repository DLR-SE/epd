package de.emir.model.universal.physics.impl;

import java.util.List;

import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.physics.EnvironmentFactor;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.model.universal.physics.RelativeEngineering2D;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.physics.impl.LocatableObjectImpl;
import de.emir.model.universal.spatial.Pose;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = EnvironmentFactor.class)
abstract public class EnvironmentFactorImpl extends LocatableObjectImpl implements EnvironmentFactor  
{
	
	
	/**
	 *	@generated 
	 */
	private Geometry mAreaOfInfluence = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public EnvironmentFactorImpl(){
		super();
		//set the default values and assign them to this instance 
		setAreaOfInfluence(mAreaOfInfluence);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public EnvironmentFactorImpl(final EnvironmentFactor _copy) {
		super(_copy);
		mAreaOfInfluence = _copy.getAreaOfInfluence();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public EnvironmentFactorImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, RelativeEngineering2D _ownedCoordinateSystem, Pose _pose, Geometry _areaOfInfluence) {
		super(_name,_allias,_remarks,_observers,_identifier,_ownedCoordinateSystem,_pose);
		mAreaOfInfluence = _areaOfInfluence; 
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return PhysicsPackage.Literals.EnvironmentFactor;
	}

	/**
	 *	@generated 
	 */
	public void setAreaOfInfluence(Geometry _areaOfInfluence) {
		Notification<Geometry> notification = basicSet(mAreaOfInfluence, _areaOfInfluence, PhysicsPackage.Literals.EnvironmentFactor_areaOfInfluence);
		mAreaOfInfluence = _areaOfInfluence;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Geometry getAreaOfInfluence() {
		return mAreaOfInfluence;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "EnvironmentFactorImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
